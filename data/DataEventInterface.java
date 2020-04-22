package data;

public interface DataEventInterface {
	//# 클래스 이름 : DataEvent
	//함수 쓰실 때 DataEvent 객체 맨 처음에 생성하고 계속 쓰시면 됩니다.(new() 한번만 만들어주세요)
	
	/* 속성
	Database db;	//현재 참고하고 있는 장소목록입니다.
	ArrayList<ArrayList<Integer>> d_list;	//장소끼리의 인접행렬입니다.
	ArrayList<String> name_list;	//장소이름들의 배열입니다.
	*/
	//name_list.get(i)로 i번째 장소 이름 받아올 수 있습니다.
	//name_list.size() 하시면 현재 목록에 있는 장소 개수 확인할 수 있습니다.
	
	

	//# 장소 목록에 데이터 삭제
	//현재 장소 목록의 장소를 삭제합니다.
	//장소 목록의 인덱스 번호를 parameter로 받습니다.	
	public void delete_list(int idx);
		
	//# 장소 목록에 장소 추가
	//현재 장소 목록에 장소를 추가하고 저장합니다.
	//추가할 장소 이름과 이미 있던 장소들과의 거리를 모은 배열을 parameter로 받습니다.
	public void add_list(String name, int[] distance);	
	
	//# 새로운 장소 목록 추가
	public void add_database(String name);	//새로운 장소 목록을 만듭니다.
	
	//# 장소 목록 불러오기
	//장소 목록 불러오기 메서드는 추가할 예정
}

