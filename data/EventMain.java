package data;

public class EventMain {
	public static void main(String[] args) {
		DataEvent e = new DataEvent();
		e.ex = new Database();
		e.load_data(e.ex);
		e.add_list("학교", e.rand(0));
		e.add_list("집", e.rand(1));
		e.add_list("신촌", e.rand(2));
		e.run();
	}
}
