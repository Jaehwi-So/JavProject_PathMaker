package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {
	private String name = "";	
	private ArrayList<ArrayList<Integer>> placeMatrix = new ArrayList<>();	
	private ArrayList<String> placeName = new ArrayList<>();	
	private String path = "";	//°æ·Î
	
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