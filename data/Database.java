package data;

import java.util.ArrayList;

public class Database {
	private String name;	//목록명
	private ArrayList<ArrayList<Integer>> placeMatrix;	//인접행렬
	private ArrayList<String> placeName;	//장소들의 이름
	private String path;	//파일 경로
	
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