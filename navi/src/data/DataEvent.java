package data;


import java.util.ArrayList;
import java.util.Random;

import tsp.Node;
import tsp.TSP;

public class DataEvent implements DataEventInterface{
	private Database db;
	private ArrayList<ArrayList<Integer>> d_list = new ArrayList<>();;
	private ArrayList<String> name_list = new ArrayList<>();;

	public Database getDb() {
		return db;
	}
	public ArrayList<ArrayList<Integer>> getD_list() {
		return d_list;
	}
	public ArrayList<String> getName_list() {
		return name_list;
	}
	
	//�쁽�옱 �겢�옒�뒪�뿉 �뜲�씠�꽣踰좎씠�뒪�쓽 諛곗뿴 源딆� 蹂듭궗
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
	/*
	public void load(String path) {
		Database db = get()
		return db;
	}
	*/
	//�뜲�씠�꽣踰좎씠�뒪�쓽 �뜲�씠�꽣瑜� 媛��졇 �샂
	public void load_data(Database database) {
		this.db = database;
		d_list = new ArrayList<>();
		name_list = new ArrayList<>();
		copy_list(database.getPlaceMatrix(), database.getPlaceName());
	}
	/*
	public void save() {
		save();
	}
	*/
	//�뜲�씠�꽣踰좎씠�뒪�뿉 蹂�寃� �궗�빆�쓣 ���옣
	public void save_data() {
		this.db.setPlaceMatrix(d_list);
		this.db.setPlaceName(name_list);
		//save();
	}
	
	//�솗�씤�슜 print
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
	
	//2李⑥썝 ArrayList瑜� 2李⑥썝諛곗뿴濡� 諛붽퓞
	public int[][] conv_arr() {
		int res_arr[][];
		res_arr = new int[d_list.size()][d_list.size()];
		for(int i = 0; i < d_list.get(0).size(); i++) {
			for(int j = 0; j < d_list.size(); j++) {
				res_arr[i][j] = d_list.get(i).get(j);
			}
		}
		return res_arr;
	}
	
	//�옣�냼 紐⑸줉�뿉 �뜲�씠�꽣 �궘�젣
	public void delete_list(int idx) {
		d_list.remove(idx);	//�뻾 �젣嫄�
		name_list.remove(idx);

		for(int i = 0; i < d_list.size(); i++) {
			d_list.get(i).remove(idx);	//�뿴 �젣嫄�
		}
		save_data();
	}
	
	//�씤�젒�뻾�젹 �뜲�씠�꽣 異붽�
	public void add(int idx, int distance, ArrayList<Integer> newPlace) {
		newPlace.add(distance);	
		d_list.get(idx).add(distance);
		if(d_list.get(0).size() == idx + 2) {
			newPlace.add(0);
			d_list.add(newPlace);                                                                                                          
		}
	} 
	
	//�옣�냼 紐⑸줉�뿉 �뜲�씠�꽣 異붽�
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
	
	//�뜲�씠�꽣踰좎씠�뒪 異붽�
	public void add_database(String name) {
		Database db = new Database();
		db.setName(name);
		load_data(db);
		this.db = db;
		save_data();
	}
	
	//�떎�뿕�슜
	public int[] rand(int size){
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) {
			arr[i] = new Random().nextInt(10) + 5;
		}
		return arr;
	}
	
	public void run_tsp() {
		int adj_mat[][] = conv_arr();
		int[] at = new int[adj_mat.length];
		int[] at_type = new int[adj_mat.length];
		int[] lt = new int[adj_mat.length];
		String[] name = new String[adj_mat.length];
		for(int i = 0; i < adj_mat.length; i++) {
			at[i] = -1;
			at_type[i] = 0;
			lt[i] = 0;
			name[i] = name_list.get(i);
		}
		TSP tsp = new TSP(adj_mat, at, at_type, lt, name, 0);
		Node best;
		best = tsp.tsp_func(0, 0);
		tsp.print_status(best, true);	
	}
	
}
