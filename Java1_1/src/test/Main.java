package test;

public class Main {

	public static int _age;
	
	public static void main(String[] args) {
		/*
		 * int a = 1; boolean b = true; float f = 1.1f; String s = "QWERTY"; char c =
		 * 'c'; c = 'h';
		 * 
		 * int aa = (4 + 5) / 10 + a;
		 * 
		 * System.out.println(aa);
		 * 
		 * double aaa = 5 / 2.0d; System.out.println(aaa);
		 * 
		 * String ss = "Hello" + "World" + (10 + 20 + 30) + aa; System.out.println(ss +
		 * "123");
		 */

		// String s = " abc abc ";

		/*
		 * System.out.println(s.indexOf("b")); System.out.println(s.indexOf("b", 3));
		 * System.out.println(s.indexOf("bbbb"));
		 */

		/*
		 * s = s.replace("ab", "ABCD").toLowerCase().trim().replace(" ", ""); //
		 * System.out.println(); System.out.println(s);
		 * 
		 * System.out.println(s.charAt(2));
		 * 
		 * System.out.println(s.substring(0, 3)); System.out.println(s.substring(3));
		 * 
		 * String email = "vasya@mail.com"; int dog = email.indexOf("@"); String login =
		 * email.substring(0, dog); System.out.println(login);
		 */

		/*
		 * test("q", 5); test("a", 6); test("asdf", 0); test("");
		 * 
		 * String str = test(); System.out.println(str);
		 */

		/*int a = 0;
		a = test(a);
		
		System.out.println(a);*/
		
		String s = String.valueOf(10);
		int i = Integer.parseInt("10");
		float f = Float.parseFloat("100.010,1");
		
	}

	public static int test(int a) {
		return 10;
	}
	
	public static void test() {
		_age = 10;
	}

	/*
	 * public static void test(String str, int a) {
	 * System.out.println("Hello metod!" + str + a); }
	 * 
	 * public static void test(String str) { System.out.println("Hello metod!" +
	 * str); }
	 * 
	 * public static String test() { System.out.println("Hello metod!");
	 * 
	 * return "asdfgsdfg"; }
	 */
}
