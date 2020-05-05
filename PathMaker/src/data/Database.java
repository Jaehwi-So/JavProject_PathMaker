package data;

// # 장소 목록의 정보를 저장하고 있는 클래스.
import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {
	private String name = "";	//목록명
	private ArrayList<ArrayList<Integer>> placeMatrix = new ArrayList<>();	//인접행렬
	private ArrayList<String> placeName = new ArrayList<>();	//장소이름 목록
	private String path = "";	//경로
	
	public Database() {
		placeMatrix = new ArrayList<>();
		placeName = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<ArrayList<Integer>> getPlaceMatrix() {
		return placeMatrix;
	}
	public void setPlaceMatrix(ArrayList<ArrayList<Integer>> placeMatrix) {
		this.placeMatrix = placeMatrix;
	}
	public ArrayList<String> getPlaceName() {
		return placeName;
	}
	public void setPlaceName(ArrayList<String> placeName) {
		this.placeName = placeName;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}