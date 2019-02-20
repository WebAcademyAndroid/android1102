import aaa.Student2;

public class Main {

	public static void main(String[] args) {

		int a = 0;
		int b = a;
		a = 2;

		Student s = new Student("Ivan", 22);
		s = new Student();

		// Student s3 = null;
		// s3.age = 44;

		//System.out.println(s.age);

		String str = "AAA";

		if (a == 2) {

		}

		if (str.equals("AAA")) {

		}

		Student[] arr = new Student[10];

		Student ss = new Student("Ivan", 220);

		arr[0] = ss;
		arr[1] = ss;

		arr[0] = null;

		int i = 4;
		test(i);

		Student sss = new Student("Ivan", 22);
		test(sss);

		sss.test();

		Student2 s2 = new Student2();
		s2.test2();

		Student.test2();
		//Student.testStr = "123";
		
		//DataBase db = new DataBase();
		
		
		DataBase.getInstance().getData();
	}

	public static void test(int i) {
		i = 40;
	}

	public static void test(Student s) {
		if (s != null) {
			//s.age = 44;
		}
	}

}
