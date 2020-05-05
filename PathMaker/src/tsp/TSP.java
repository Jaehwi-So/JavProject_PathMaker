package tsp;

// # TSP �˰����� �����ϴ� ���� Ŭ����

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TSP {
	public static final int INF = 1000000;

	int n = 0;	//������ ����
	int adj_mat[][];	//���� ���
	int lt_mat[]; //�ҿ� �ð�
	int at_mat[];	//���� �ð� �迭(�������� �ʾҴٸ� -1). GUI�󿡼� �̱���
	int at_type[]; //���� Ÿ��(�������� ����, �ð� ���缭 ���� ����, �ð� ������ ����). GUI�󿡼� �̱���
	int min_value = Integer.MAX_VALUE;	//�ִ� �Ÿ�
	int min_time = Integer.MAX_VALUE;	//�ּ� �ҿ� �ð�
	int path[];	//���
	int arrive[]; //������ ������ �ð�
	int gone[]; //�������� ����� �ð�
	int start_time;
	String name[]; //������ �̸�(���)
	
	

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
	
	/* ��ΰ� ��� �̾��� �ִ��� Ȯ�� */
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
	
	/* ���� ������ ������ �Ǵ� */
	boolean promising(int i, int bound) {

		//n-1��° ���� ó�� ���� ����Ǿ� �־�� �Ѵ�.
		if (n - 1 == i && adj_mat[path[0]][i] == INF) {
			return false;
		}
		//���� ������ ���� ������ ����Ǿ� �־�� �Ѵ�.
		else if (adj_mat[path[i-1]][path[i]] == INF) {
			return false;
		}
		 //������ ���� ������ ������ �� �� ����.
		for (int j = 0; j < i; j++) {   
			if (path[i] == path[j]) {
				return false;
			}
		}
		//���� ������ ��� bound�� ������ �ּ� �Ÿ����� ª�ƾ� �Ѵ�.
		if (bound > min_value) {	
			return false;
		}
		return true;    //�װ��� ������ ��� �����ϸ� �����ϴٰ� �Ǵ�
	}

	/* ���� ���� ��� */
	public void print_status(Node u, boolean best) {
		if (best) {
			String s = "";
		
			for (int i = 0; i <= n; i++) {
				if (i == n) {
					//System.out.printf("%s(%d) : [���� : %d �ð��ҿ� %d ��� %d] ", name[u.path[i]] , u.path[i], u.arrive[i], lt_mat[u.path[i]], u.gone[i]);
					s += "#" + name[u.path[i]]+" : [���� :" + u.arrive[i] + "�� /STAY : " + lt_mat[u.path[i]] + "�� /��� : " + u.gone[i] + "��]";
				}
				else {
					//System.out.printf("%s(%d) : [���� : %d �ð��ҿ� %d ��� %d] ", name[u.path[i]], u.path[i], u.arrive[i], lt_mat[u.path[i]], u.gone[i]);
					s += "#" + name[u.path[i]]+" : [���� :" + u.arrive[i] + "�� /STAY : " + lt_mat[u.path[i]] + "�� /��� : " + u.gone[i] + "��]";
					//System.out.printf("-(%d)->", adj_mat[u.path[i]][u.path[0]]);
					s += "\n\t-( �̵��ð� :" + adj_mat[u.path[i]][u.path[i+1]] + "��)->";
				}
				//System.out.println();
				s += "\n";
			}
			s += "\n";
			System.out.print("��� �ð� : " + u.time);
			s += "��� �ð� : " + u.time/60+"�ð�"+u.time%60+"��";
			s += "\n";
			System.out.println(s);
			
			if(u.complete == false) {
				System.out.println("��θ� ã�� ����");
			}
			
			Frame f = new Frame("����Դϴ�.");
			Font font = new Font("", Font.BOLD, 20);
			f.setLayout(null);
			f.setBounds(100, 100, 800, 800);
			f.setBackground(Color.lightGray);
			
			Label sub = new Label("==== ������ ��� Ž���� ���ƽ��ϴ� ====");
			
			sub.setBounds(50, 50, 700, 40);
			sub.setBackground(Color.white);
			sub.setFont(font);
			
			
			TextArea fTa = new TextArea("",0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);;
			fTa.setBounds(50, 100, 700, 600);
			fTa.setBackground(Color.white);
			fTa.setFont(font);
			
			f.add(sub);
			fTa.setText(s);
			f.add(fTa);
			f.setVisible(true);
			f.setLayout(null);
			
			
			f.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					f.dispose();
				}
			});
			
		}
		
	

	}

	/* ���� ���𿡼� �湮���� ���� ������ ���� ��ȯ */
	int[] get_differset(int i, int[] path) {
		ArrayList<Integer> arr = new ArrayList<>();
		outer : for (int k = 0; k < n; k++) {
			for (int j = 0; j <= i; j++) {
				if (path[j] == k) {
					continue outer;
				}
			}
			arr.add(k);	//�̹� �����ϴ� ������� �ߺ����� ���� ���� index�� ���տ� �߰�
		}
		int[] a = new int[arr.size()];
		for(int k = 0; k < arr.size(); k++) {
			a[k] = arr.get(k);
		}
		return a;
	}

	
	/* ���� ���𿡼��� bound��(���� ���𿡼��� �ּ� ��ȸ �Ÿ�) ��ȯ */
	int get_bound(int i, int start, Node u) {
		int bound = 0;
		int a[] = get_differset(i, path);	//���� path�� ������ �ʴ� �������� ���� a[]

		//��������� ��λ󿡼� �������� distance�� ��
		for (int k = 0; k < i; k++) {
			if (k == n - 1) {
				bound += adj_mat[path[k]][path[start]];
			}
			bound += adj_mat[path[k]][path[k + 1]];
		}

		//bound += u.time;


		//���� �������� a[]�� ���ϴ� �������� ���� �Ÿ��� �ּҰ�(min_d)
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

		//���� a[]�� ������ �������� a[]�� ���ϴ� ������ Ȥ�� ������������ ���� �ּڰ�(min_v)���� �� sum_v
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
		//System.out.printf("bound ���ϱ� %d + %d + %d\n",bound, min_d, sum_v);
		bound = bound + min_d + sum_v;	//bound ���ϱ�

		return bound;

	}

	/* ���� ������ �ݵ�� �湮�ؾ� �ϴ� ������ �ִ��� üũ */
	int have_arrive(Node v, int i) {		
		int[] a = get_differset(i, v.path);	//�湮���� ���� ������ ���� a[]
		
		outer : for(int j : a) {	//�湮���� ���� ������ �� �湮 �䱸 ǥ�ð� �Ǿ��ִ� ���� j , �湮���� �ʴ� �ٸ� �������� ���� l[]
			if(at_mat[j] != -1 && adj_mat[v.i][j] != INF) {
				for(int l : a) {
					if(adj_mat[j][l] != INF) {
					//�ٸ� ������ ���� j�� ���� ���� �ð��� �����ϴٸ� ���� ���� j�� �ʼ� �湮���� x
						if((at_mat[j] - v.time - adj_mat[path[v.i]][j] >= adj_mat[path[v.i]][l] + adj_mat[l][j]) && j != l) {	
							continue outer;
						}
					}
				}
				return j;	//�湮�ؾ� �� ���� ����
			}			
		}
		return -1;	//�湮�ؾ� �� ���� �������� ����.(���� ���)
	}

	/* Node�� �̿ϼ� ��� �ϼ� (<1,3,4,2> -> <1,3,4,2,5,1>) */
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

	/* TSP �˰��� */
	public Node tsp_func(int i, int start) {
		Heap hp = new Heap();
		Node u = new Node(n);	//������ ������ ����(�������� �õ��� ����)
		Node v = new Node(n);	//�������� ���� ����(Ž���� ����)
		Node r = new Node(n);	//������ ����
		u.i = 0;	//���� �ʱ�ȭ
		path[0] = start;	//���� ���� �ʱ�ȭ
		arrive[0] = start_time;
		u.bound = get_bound(i, start, u);	//��� ���
		u.path[0] = path[0];
		gone[0] = start_time + lt_mat[start];
		u.time = gone[0];
		u.arrive[0] = arrive[0];
		u.gone[0] = gone[0];
		hp.insert_heap(u);	//���� ����

		//�б� ������, �ְ�켱 Ž�� ����
		while (hp.data.size() != 0) {
			v = new Node(n);
			v = hp.delete_heap();	//heap���� ���� ª�� bound(distance)�� ���� ������.
			//print_status(v, false);
			for (int k = 0; k <= v.i; k++) {	//���� ��ο� ������ ���� ������ ����
				path[k] = v.path[k];
				arrive[k] = v.arrive[k];
				gone[k] = v.gone[k];
			}
			//n-2 ���ؿ� �����Ͽ����� ��ΰ� �����Ǿ���. ex) 1-3-4-2 == 1-3-4-2-5-1
			//�� �� min_value���� bound���� ������ �ּ��� ��� ����
			if (v.i == n - 2 && min_value > v.bound) {
				u = new Node(n);
				u = path_end(v, start);	//���� ��� �ϼ�
				int idx;
				//���� ��� �˻�. 
				if(((idx = at_mat[u.path[n - 1]]) >= u.time || idx == -1) && path_promising(u) && min_time > u.time) {
					u.time += adj_mat[u.path[n - 1]][start]; //�ϴ� ����
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
			if (v.i < n - 2) {	//�ְ� ������ ������� �������� �ʾҴٸ�
				int idx;
				//���� �������� �����ؾ� �� ������ �����Ѵٸ� �켱������ �ִ´�.
				if((idx = have_arrive(v, i)) != -1) {
					i = v.i + 1;	//���� ���𿡼� ������ ���� ���� ���� ����.
					path[i] = idx;	//��� ����
					u = new Node(n);
					u.i = i;	//bound�� ���ϰ� u�� ���� ���� ����
					u.time = v.time + adj_mat[path[i-1]][path[i]];
					u.bound = get_bound(i, start, u);	
					arrive[i] = u.time;
					if(!(at_mat[idx] < u.time)) {	//������ �� �ð��� ���� �����ϴٸ� ���� ����
						if(at_type[idx] == 1) {	//�ð��� ���� �����ؾ� �ϴ� ��� �ð� ����
							u.time = at_mat[idx];
						}			
						u.time += lt_mat[idx];		
						gone[i] = u.time;
						for (int l = 0; l <= i; l++) {	//��ο� ������ �����ð� ����
							u.path[l] = path[l];
							u.arrive[l] = arrive[l];
							u.gone[l] = gone[l];
						}		
						hp.insert_heap(u);
					}
				}

				else {
					i = v.i + 1;	//���� ���𿡼� ������ ���� ���� ���� ����.
					for (int k = 0; k < n && i < n; k++) {	//���� ��� k �˻�
						path[i] = k;	//��� ����
						u = new Node(n);
						u.i = i;	//bound�� ���ϰ� u�� ���� ���� ����
						u.time = v.time + adj_mat[path[i-1]][path[i]];
						u.bound = get_bound(i, start, u);	
						arrive[i] = u.time;
						if (promising(i, u.bound) && (!(at_mat[k] < u.time) || at_mat[k] == -1)) {	//���� ���� �����ϴٸ�
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
							hp.insert_heap(u);	//���� ����
						}
					}
				}
			}
		}
		return r;
	}
}