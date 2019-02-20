
public class Student {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 100) {
			this.age = age;
		}
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Student(String name) {
		this.name = name;
	}

	public Student() {
		test();
	}

	void test() {

	}
	
	
	public static final String testStr = "AAA";
	
	public static void test2() {
		
	}
}
