package data;

// # 프레임에서 장소 데이터를 사용하는 이벤트를 처리하는 클래스
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Random;

import io.DBLoad;
import io.DBSave;
import tsp.Node;
import tsp.TSP;

public class DataEvent implements DataEventInterface{
	public Frame frame;
	private Database db;	//현재 참조하고 있는 목록
	private ArrayList<ArrayList<Integer>> d_list = new ArrayList<>();
	private ArrayList<String> name_list = new ArrayList<>();
	String[] name;
	public int startPoint = 0;

	private ArrayList<ArrayList<Integer>> select_d_list;
	private ArrayList<String> select_name_list;

	public ArrayList<ArrayList<Integer>> getSelect_d_list() {
		return select_d_list;
	}
	public ArrayList<String> getSelect_name_list() {
		return select_name_list;
	}
	public void setStartPoint(int s) {
		this.startPoint = s;
	}
	public DataEvent(Frame f) {
		this.frame = f;
	}
	public Database getDb() {
		return db;
	}
	public ArrayList<ArrayList<Integer>> getD_list() {
		return d_list;
	}
	public ArrayList<String> getName_list() {
		return name_list;
	}

	//선택한 장소들만 ArrayList에 넣는다.
	public void get_select(int[] arr) {
		select_d_list = new ArrayList<>();
		select_name_list = new ArrayList<>();
		if(d_list.size() == 0) {
			return;
		}
		for(int i = 0; i < d_list.size(); i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int j = 0; j < d_list.get(0).size(); j++) {
				tmp.add(d_list.get(i).get(j));
			}
			select_d_list.add(tmp);
			select_name_list.add(name_list.get(i));
		}
		
		int k = 0;
		for(int j = 0; j < arr.length; j++) {
			if(arr[j] == 0) {
				select_d_list.remove(j - k);   //�뻾 �젣嫄�
				select_name_list.remove(j - k);
				for(int i = 0; i < select_d_list.size(); i++) {
					select_d_list.get(i).remove(j - k);   //�뿴 �젣嫄�
				}
				k++;
			}
		}
		//this.startPoint = start;
	}

	// 배열에 깊은 복사
	public void copy_list(ArrayList<ArrayList<Integer>> list, ArrayList<String> name) {
		if(list.size() == 0) {
			return;
		}
		for(int i = 0; i < list.get(0).size(); i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int j = 0; j < list.size(); j++) {
				tmp.add(list.get(i).get(j));
			}
			d_list.add(tmp);
		}
		for(int i = 0; i < name.size(); i++) {
			name_list.add(name.get(i));
		}
	}

	//외부 파일을 받아온다.
	public void load() {
		DBLoad dl = new DBLoad();
		Database newDB = dl.load_db(frame);
		load_data(newDB);
	}

	//내부 속성에 데이터를 불러온다.
	public void load_data(Database database) {
		this.db = database;
		d_list = new ArrayList<>();
		name_list = new ArrayList<>();
		copy_list(database.getPlaceMatrix(), database.getPlaceName());
	}

	//외부에 저장
	public void save() {
		DBSave ds = new DBSave();
		ds.save_db(this.db, frame);
	}

	//데이터를 저장한다.
	public void save_data() {
		this.db.setPlaceMatrix(d_list);
		this.db.setPlaceName(name_list);
		save();
	}

	//테스트용
	public void print() {
		if(name_list.size() == 0) {
			return;
		}
		System.out.println(name_list);
		for(int i = 0; i < d_list.get(0).size(); i++) {
			for(int j = 0; j < d_list.size(); j++) {
				System.out.printf("%3d", d_list.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println("==========================");
	}

	//2차원 ArrayList를 배열로 변환
	public int[][] conv_arr(ArrayList<ArrayList<Integer>> list) {
		int res_arr[][];
		res_arr = new int[list.size()][list.size()];
		for(int i = 0; i < list.get(0).size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				res_arr[i][j] = list.get(i).get(j);
			}
		}
		return res_arr;
	}

	//목록에서 장소 한 군데를 지운다.
	public void delete_list(int idx) {
		d_list.remove(idx);   //�뻾 �젣嫄�
		name_list.remove(idx);

		for(int i = 0; i < d_list.size(); i++) {
			d_list.get(i).remove(idx);   //�뿴 �젣嫄�
		}
		save_data();
	}

	//목록에 장소 한 군데를 추가한다.
	public void add(int idx, int distance, ArrayList<Integer> newPlace) {
		newPlace.add(distance);   
		d_list.get(idx).add(distance);
		if(d_list.get(0).size() == idx + 2) {
			newPlace.add(0);
			d_list.add(newPlace);                                                                                                          
		}
	} 

	//추가할 장소들을 불러들인다.
	public void add_list(String name, int[] distance) {
		name_list.add(name);
		ArrayList<Integer> list_x = new ArrayList<>();
		if(distance.length == 0) {
			list_x.add(0);
			d_list.add(list_x);
		}
		else {
			for(int i = 0; i < distance.length; i++) {
				add(i, distance[i], list_x);
			}
		}
		save_data();
	}

	//데이터베이스를 추가한다.
	public void add_database(String name) {
		Database db = new Database();
		db.setName(name);
		load_data(db);
		this.db = db;
		save_data();
	}

	//테스트용
	public int[] rand(int size){
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) {
			arr[i] = new Random().nextInt(10) + 5;
		}
		return arr;
	}

	//tsp 알고리즘 실행
	public Node run_tsp(int[] ltime) {

		int adj_mat[][] = conv_arr(select_d_list);
		int[] at = new int[adj_mat.length];
		int[] at_type = new int[adj_mat.length];
		int[] lt = ltime;
		name = new String[adj_mat.length];

		for(int i = 0; i < adj_mat.length; i++) {
			at[i] = -1;
			at_type[i] = 0;
			name[i] = select_name_list.get(i);
		}
		lt[startPoint] = 0;

		TSP tsp = new TSP(adj_mat, at, at_type, lt, name, 0);
		Node best;
		best = tsp.tsp_func(0, startPoint);
		tsp.print_status(best, true);
		return best;
	}
}