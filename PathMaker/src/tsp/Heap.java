package tsp;

//# �� �ڷᱸ��

import java.util.ArrayList;
import java.util.Collections;

public class Heap {
	ArrayList<Node> data = new ArrayList<>();
	
	public void insert_heap(Node item) {
		data.add(item);
		Collections.sort(data);    //���μ������� AT�� ���� ������������ ����
	}
	
	public Node delete_heap() {
		Node node = data.get(0);
		data.remove(0);
		return node;
	}

}
