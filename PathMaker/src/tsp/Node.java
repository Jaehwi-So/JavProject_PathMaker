package tsp;


public class Node implements Comparable<Object>{
	int bound;	//현재 마디의 bound값
	int i;	//현재 수준
	public int path[];	//경로
	public int arrive[];	//도착 시간
	public int gone[]; //출발 시간
	public int time; //지금까지의 총 소요 시간
	boolean complete = false;
	public Node(int n){
		path = new int[n + 1];
		arrive = new int[n + 1];
		gone = new int[n + 1];
	}
	@Override             //Sort시 bound순 정렬
	public int compareTo(Object o) {
		Node p = (Node)o;
		return Integer.compare(this.bound, p.bound);	
	}	
}
