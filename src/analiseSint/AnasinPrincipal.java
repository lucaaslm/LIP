package analiseSint;

public class AnasinPrincipal {
	public static void main(String[] args) {
		String exp1 = "x = (1 ** 1.4);";
		String exp2 = "a = (coisa + 34)) * (abc / 2";
		String exp3 = "abc = 1 + fg ** 4;";
		AnaSin anasin = new AnaSin(exp1);
		anasin.anasint();
	}
	
}
