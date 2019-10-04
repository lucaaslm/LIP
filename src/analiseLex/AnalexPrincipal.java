package analiseLex;

public class AnalexPrincipal {
	
	public static void main(String[] args) {
		String exp1 = "x = (1 ** 00000.1 ) ;";
		String exp2 = "a = (coisa + 34 ) * (abc / 2 )";
		String exp3 = "abc = ( 1 + fg ** 4.5 ) ;";
		AnaLex analexSimples = new AnaLex(exp3);
		analexSimples.analex();
		System.out.println(analexSimples);
	}
}
