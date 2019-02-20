
public class DataBase {

	private static DataBase db;

	private DataBase() {

	}

	public static DataBase getInstance() {
		if (db == null) {
			db = new DataBase();
		}

		return db;
	}

	public void getData() {

	}

}
