package data;

public interface DataEventInterface {
	//# Ŭ���� �̸� : DataEvent
	//�Լ� ���� �� DataEvent ��ü �� ó���� �����ϰ� ��� ���ø� �˴ϴ�.(new() �ѹ��� ������ּ���)
	
	/* �Ӽ�
	Database db;	//���� �����ϰ� �ִ� ��Ҹ���Դϴ�.
	ArrayList<ArrayList<Integer>> d_list;	//��ҳ����� ��������Դϴ�.
	ArrayList<String> name_list;	//����̸����� �迭�Դϴ�.
	*/
	//name_list.get(i)�� i��° ��� �̸� �޾ƿ� �� �ֽ��ϴ�.
	//name_list.size() �Ͻø� ���� ��Ͽ� �ִ� ��� ���� Ȯ���� �� �ֽ��ϴ�.
	
	

	//# ��� ��Ͽ� ������ ����
	//���� ��� ����� ��Ҹ� �����մϴ�.
	//��� ����� �ε��� ��ȣ�� parameter�� �޽��ϴ�.	
	public void delete_list(int idx);
		
	//# ��� ��Ͽ� ��� �߰�
	//���� ��� ��Ͽ� ��Ҹ� �߰��ϰ� �����մϴ�.
	//�߰��� ��� �̸��� �̹� �ִ� ��ҵ���� �Ÿ��� ���� �迭�� parameter�� �޽��ϴ�.
	public void add_list(String name, int[] distance);	
	
	//# ���ο� ��� ��� �߰�
	public void add_database(String name);	//���ο� ��� ����� ����ϴ�.
	
	//# ��� ��� �ҷ�����
	//��� ��� �ҷ����� �޼���� �߰��� ����
}

