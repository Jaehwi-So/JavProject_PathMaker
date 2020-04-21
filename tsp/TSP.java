package tsp;

import java.util.ArrayList;

public class TSP {
	public static final int INF = 1000000;

	int n = 0;	//정점의 개수
	int adj_mat[][];	//인접 행렬
	int lt_mat[]; //소요 시간
	int at_mat[];	//도착 시간 배열(정해지지 않았다면 -1)
	int at_type[]; //도착 타입(정해지지 않음, 시간 맞춰서 업무 시작, 시간 전에만 도착)
	int min_value = Integer.MAX_VALUE;	//최단 거리
	int min_time = Integer.MAX_VALUE;	//최소 소요 시간
	int path[];	//경로
	int arrive[]; //정점에 도착한 시간
	int gone[]; //정점에서 출발한 시간
	int start_time;
	String name[]; //정점의 이름(장소)
	
	

	public TSP(int[][] adj, int[] at_m, int[] at_t, int[] lt, String[] name, int start_t){
		this.n = adj.length;
		this.adj_mat = adj;
		this.path = new int[adj.length + 1];
		this.at_mat = at_m;
		this.at_type = at_t;
		this.lt_mat = lt;
		this.arrive = new int[adj.length + 1];
		this.gone = new int[adj.length + 1];
		this.name = name;
		this.start_time = start_t;
	}
	
	/* 경로가 모두 이어져 있는지 확인 */
	boolean path_promising(Node u) {
		for(int i = 0; i <= n; i++) {
			if(i == n) {
				if(adj_mat[u.path[i]][u.path[0]] == INF) {
					return false;
				}
			}
			else if(adj_mat[u.path[i]][u.path[i+1]] == INF) {
				return false;
			}
		}
		return true;
	}
	
	/* 현재 마디의 유망성 판단 */
	boolean promising(int i, int bound) {

		//n-1번째 노드와 처음 노드는 연결되어 있어야 한다.
		if (n - 1 == i && adj_mat[path[0]][i] == INF) {
			return false;
		}
		//전의 정점과 현재 정점은 연결되어 있어야 한다.
		else if (adj_mat[path[i-1]][path[i]] == INF) {
			return false;
		}
		 //정점은 전에 지나온 정점이 될 수 없다.
		for (int j = 0; j < i; j++) {   
			if (path[i] == path[j]) {
				return false;
			}
		}
		//현재 정점의 기댓값 bound가 현재의 최소 거리보다 짧아야 한다.
		if (bound > min_value) {	
			return false;
		}
		return true;    //네가지 조건을 모두 만족하면 유망하다고 판단
	}

	/* 마디 정보 출력 */
	void print_status(Node u, boolean best) {
		if (best) {
			System.out.print("최적해 마디(최단 거리) optimal tour : ");
			System.out.print("<");
			for (int i = 0; i <= n; i++) {
				if (i == n) {
					System.out.printf("%d", u.path[i]);
				}
				else System.out.printf("%d,", u.path[i]);
			}
			System.out.print(">");
			if(u.complete == false) {
				System.out.println("경로를 찾지 못함");
			}
			System.out.println();
			for (int i = 0; i <= n; i++) {
				if (i == n) {
					System.out.printf("%s(%d) : [도착 : %d 시간소요 %d 출발 %d] ", name[u.path[i]] , u.path[i], u.arrive[i], lt_mat[u.path[i]], u.gone[i]);
				}
				else {
					System.out.printf("%s(%d) : [도착 : %d 시간소요 %d 출발 %d] ", name[u.path[i]], u.path[i], u.arrive[i], lt_mat[u.path[i]], u.gone[i]);
					System.out.printf("-(%d)->", adj_mat[u.path[i]][u.path[i+1]]);
				}
				System.out.println();
			}
			System.out.print("경과 시간 : " + u.time);
			System.out.println();
		}
		else {
			System.out.print("현재 마디 : ");
			System.out.print("<");
			for (int i = 0; i <= u.i; i++) {
				if (i == u.i) {
					System.out.printf("%d", u.path[i]);
				}
				else System.out.printf("%d,", u.path[i]);
			}
			System.out.printf(">");
		}
		if (u.i >= n-2) {
			System.out.printf(" value : %d \n", u.bound);
		}
		else System.out.printf(" bound : %d \n", u.bound);
	}

	/* 현재 마디에서 방문하지 않은 정점의 집합 반환 */
	int[] get_differset(int i, int[] path) {
		ArrayList<Integer> arr = new ArrayList<>();
		outer : for (int k = 0; k < n; k++) {
			for (int j = 0; j <= i; j++) {
				if (path[j] == k) {
					continue outer;
				}
			}
			arr.add(k);	//이미 존재하는 정점들과 중복되지 않은 정점 index를 집합에 추가
		}
		int[] a = new int[arr.size()];
		for(int k = 0; k < arr.size(); k++) {
			a[k] = arr.get(k);
		}
		return a;
	}

	
	/* 현재 마디에서의 bound값(현재 마디에서의 최소 순회 거리) 반환 */
	int get_bound(int i, int start, Node u) {
		int bound = 0;
		int a[] = get_differset(i, path);	//현재 path에 속하지 않는 정점들의 집합 a[]

		//현재까지의 경로상에서 정점들의 distance의 합
		for (int k = 0; k < i; k++) {
			if (k == n - 1) {
				bound += adj_mat[path[k]][path[start]];
			}
			bound += adj_mat[path[k]][path[k + 1]];
		}

		//bound += u.time;


		//현재 정점에서 a[]에 속하는 정점으로 가는 거리의 최소값(min_d)
		boolean flag = false;
		int min_d = INF;
		for (int j : a) {
			if (min_d > adj_mat[path[i]][j] && path[i] != j) {
				min_d = adj_mat[path[i]][j];
				flag = true;
			}
		}
		if(flag == false) {
			min_d = 0;
		}

		//집합 a[]의 각각의 정점에서 a[]에 속하는 정점들 혹은 시작정점으로 가는 최솟값(min_v)들의 합 sum_v
		int min_v = INF;
		int sum_v = 0;
		for (int j : a) {
			for (int k : a ) {
				if (min_v > adj_mat[j][k] && j != k) {
					min_v = adj_mat[j][k];
				}
			}
			if (min_v > adj_mat[j][start] && j != start) {
				min_v = adj_mat[j][start];
			}
			sum_v += min_v;
			//System.out.printf("%d ", min_v);
			min_v = INF;
		}
		if(a.length == 0) {
			sum_v = 0;
		}
		//System.out.printf("bound 구하기 %d + %d + %d\n",bound, min_d, sum_v);
		bound = bound + min_d + sum_v;	//bound 구하기

		return bound;

	}

	/* 지금 시점에 반드시 방문해야 하는 지점이 있는지 체크 */
	int have_arrive(Node v, int i) {		
		int[] a = get_differset(i, v.path);	//방문하지 않은 정점의 집합 a[]
		
		outer : for(int j : a) {	//방문하지 않은 정점들 중 방문 요구 표시가 되어있는 정점 j , 방문하지 않는 다른 정점들의 집합 l[]
			if(at_mat[j] != -1 && adj_mat[v.i][j] != INF) {
				for(int l : a) {
					if(adj_mat[j][l] != INF) {
					//다른 정점을 거쳐 j로 가는 것이 시간상 가능하다면 현재 정점 j는 필수 방문지점 x
						if((at_mat[j] - v.time - adj_mat[path[v.i]][j] >= adj_mat[path[v.i]][l] + adj_mat[l][j]) && j != l) {	
							continue outer;
						}
					}
				}
				return j;	//방문해야 할 지점 존재
			}			
		}
		return -1;	//방문해야 할 지점 존재하지 않음.(조건 통과)
	}

	/* Node의 미완성 경로 완성 (<1,3,4,2> -> <1,3,4,2,5,1>) */
	Node path_end(Node r, int start) {
		outer : for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (r.path[j] == i) {
					continue outer;
				}
			}
			r.path[n - 1] = i;
			break;
		}
		r.time += adj_mat[r.path[n-2]][r.path[n-1]];
		if(at_type[r.path[n-1]] == 1 && at_mat[r.path[n-1]] >= r.time) {
			r.time = at_mat[r.path[n-1]];
		}
		r.arrive[n-1] = r.time;
		r.time += lt_mat[r.path[n-1]];
		r.gone[n-1] = r.time;
		r.i++;
		return r;
	}

	/* TSP 알고리즘 */
	Node tsp_func(int i, int start) {
		Heap hp = new Heap();
		Node u = new Node(n);	//히프에 삽입할 마디(가지뻗기 시도할 마디)
		Node v = new Node(n);	//히프에서 꺼낼 마디(탐색된 마디)
		Node r = new Node(n);	//최적해 마디
		u.i = 0;	//수준 초기화
		path[0] = start;	//시작 정점 초기화
		arrive[0] = start_time;
		u.time = arrive[0];
		u.bound = get_bound(i, start, u);	//기댓값 계산
		u.path[0] = path[0];
		gone[0] = start_time;
		u.arrive[0] = arrive[0];
		u.gone[0] = gone[0];
		hp.insert_heap(u);	//힙에 넣음

		//분기 한정법, 최고우선 탐색 시작
		while (hp.data.size() != 0) {
			v = new Node(n);
			v = hp.delete_heap();	//heap에서 가장 짧은 bound(distance)의 마디를 꺼낸다.
			print_status(v, false);
			for (int k = 0; k <= v.i; k++) {	//현재 경로에 힙에서 꺼낸 정보를 복사
				path[k] = v.path[k];
				arrive[k] = v.arrive[k];
				gone[k] = v.gone[k];
			}
			//n-2 수준에 도달하였으면 경로가 결정되었음. ex) 1-3-4-2 == 1-3-4-2-5-1
			//이 때 min_value보다 bound값이 작을시 최선의 경로 갱신
			if (v.i == n - 2 && min_value > v.bound) {
				u = new Node(n);
				u = path_end(v, start);	//최종 경로 완성
				int idx;
				//최종 경로 검사. 
				if(((idx = at_mat[u.path[n - 1]]) >= u.time || idx == -1) && path_promising(u) && min_time > u.time) {
					u.time += adj_mat[u.path[n - 1]][0];
					u.arrive[n] = u.time;
					u.gone[n] = u.time;
					u.complete = true;
					u.i++;
					u.path[n] = start;
					r = u;
					min_time = r.time;
					min_value = r.bound;
				}				
			}
			if (v.i < n - 2) {	//최고 수준의 마디까지 도달하지 않았다면
				int idx;
				//현재 시점에서 도착해야 할 정점이 존재한다면 우선적으로 넣는다.
				if((idx = have_arrive(v, i)) != -1) {
					i = v.i + 1;	//현재 마디에서 가지를 뻗기 위해 수준 증가.
					path[i] = idx;	//경로 설정
					u = new Node(n);
					u.i = i;	//bound를 구하고 u에 현재 정보 저장
					u.time = v.time + adj_mat[path[i-1]][path[i]];
					u.bound = get_bound(i, start, u);	
					arrive[i] = u.time;
					if(!(at_mat[idx] < u.time)) {	//정점이 제 시간에 도착 가능하다면 힙에 삽입
						if(at_type[idx] == 1) {	//시간에 맞춰 도착해야 하는 경우 시간 갱신
							u.time = at_mat[idx];
						}			
						u.time += lt_mat[idx];		
						gone[i] = u.time;
						for (int l = 0; l <= i; l++) {	//경로와 정점의 도착시간 저장
							u.path[l] = path[l];
							u.arrive[l] = arrive[l];
							u.gone[l] = gone[l];
						}		
						hp.insert_heap(u);
					}
				}

				else {
					i = v.i + 1;	//현재 마디에서 가지를 뻗기 위해 수준 증가.
					for (int k = 0; k < n && i < n; k++) {	//다음 경로 k 검사
						path[i] = k;	//경로 설정
						u = new Node(n);
						u.i = i;	//bound를 구하고 u에 현재 정보 저장
						u.time = v.time + adj_mat[path[i-1]][path[i]];
						u.bound = get_bound(i, start, u);	
						arrive[i] = u.time;
						if (promising(i, u.bound) && (!(at_mat[k] < u.time) || at_mat[k] == -1)) {	//현재 마디가 유망하다면
							if(at_type[k] == 1) {
								u.time = at_mat[k];
							}
							u.time += lt_mat[k];
							gone[i] = u.time;
							for (int l = 0; l <= i; l++) {
								u.path[l] = path[l];
								u.arrive[l] = arrive[l];
								u.gone[l] = gone[l];
							}
							hp.insert_heap(u);	//힙에 저장
						}
					}
				}
			}
		}
		return r;
	}
}
