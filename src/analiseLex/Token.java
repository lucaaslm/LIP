package analiseLex;

public enum Token {
	
	OPERADOR_SOMA('+'),
	OPERADOR_SUBT('-'),
	OPERADOR_MULT('*'),
	OPERADOR_DIVI('/'),
	OPERADOR_ELEV('^'),
	OPERADOR_ATRIB('='),
	
	PARENTESIS_ESQ('('),
	PARENTESIS_DIR(')'),
	
	PONTO_VIRGULA(';'),
	FIM('F'),
	
	ERRO('E'),
	IDENTIFICADOR('I'),
	LITERAL_INTEIRO('L'),
	LITERAL_FLOAT('F');
	
	private char valor;
	
	Token(char valor) {
		this.valor = valor;
	}
	
	public char getValor() {
		return this.valor;
	}

}
