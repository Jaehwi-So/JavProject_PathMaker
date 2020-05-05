package tsp;

//# 힙 자료구조

import java.util.ArrayList;
import java.util.Collections;

public class Heap {
	ArrayList<Node> data = new ArrayList<>();
	
	public void insert_heap(Node item) {
		data.add(item);
		Collections.sort(data);    //프로세스들을 AT에 따라 오름차순으로 정렬
	}
	
	public Node delete_heap() {
		Node node = data.get(0);
		data.remove(0);
		return node;
	}

}
