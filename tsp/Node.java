package tsp;


public class Node implements Comparable<Object>{
	int bound;	//현재 마디의 bound값
	int i;	//현재 마디의 수준(방문한 정점의 갯수)
	int path[];	//현재 마디의 경로
	int arrive[];	//정점의 도착 시간
	int gone[]; //정점에서의 출발 시간
	int time; // 현재 진행 시간
	boolean complete = false;
	public Node(int n){
		path = new int[n + 1];
		arrive = new int[n + 1];
		gone = new int[n + 1];
	}
	@Override             //Sort시 AT가 작은 순서로 정렬
	public int compareTo(Object o) {
		Node p = (Node)o;
		return Integer.compare(this.bound, p.bound);	
	}	
}
