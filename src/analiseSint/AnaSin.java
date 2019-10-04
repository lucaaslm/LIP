package analiseSint;

import java.util.List;

import analiseLex.AnaLex;
import analiseLex.Token;
import analiseLex.TokenLexema;

public class AnaSin {

	private List<TokenLexema> tokenLexemaList;
	private int contador = 0;
	
	public AnaSin(String cadeia) {
		AnaLex analex = new AnaLex(cadeia);
		this.tokenLexemaList = analex.analex();
	}
	
	public void anasint() {
		if(this.tokenLexemaList!=null) {
			this.assign();
			if(this.contador!=tokenLexemaList.size()) {
				this.msg("ERRO NO ANALISADOR SINTÁTICO");
			}
		}
		else {
			this.msg("ERRO NO ANALISADOR LÉXICO");
		}
		
	}
	
	private void  assign() {
		this.msg("Entrou assign() teste id, token: " + this.nextToken());
		if(this.nextToken().getToken() == Token.IDENTIFICADOR) {
			this.lex();
			if(this.nextToken().getToken() == Token.OPERADOR_ATRIB) {
				this.lex();
				this.expr();
			}
		}
		this.msg("Saiu assign() teste id, token: " + this.nextToken());
	}
	
	private void expr() {
		this.msg("Entrou expr(), token: " + this.nextToken());
		this.term();
		while(this.nextToken().getToken() == Token.OPERADOR_SOMA ||
			  this.nextToken().getToken() == Token.OPERADOR_SUBT) {
			this.lex();
			this.term();
		}
		this.msg("Saiu expr(), token: " + this.nextToken());
	}
	
	private void term() {
		this.msg("Entrou term(), token: " + this.nextToken());
		this.factor();
		while(this.nextToken().getToken() == Token.OPERADOR_MULT ||
			  this.nextToken().getToken() == Token.OPERADOR_DIVI) {
			this.lex();
			this.factor();
		}
		this.msg("Saiu term(), token: " + this.nextToken());
	}
	
	private void factor() {
		this.msg("Entrou factor(), token: " + this.nextToken());
		if(this.nextToken().getToken() == Token.IDENTIFICADOR) {
			this.lex();
		}else if(this.nextToken().getToken() == Token.LITERAL_INTEIRO) {
			this.lex();
		}else if(this.nextToken().getToken() == Token.PARENTESIS_ESQ) {
			this.lex();
			this.expr();
			if(this.nextToken().getToken() == Token.PARENTESIS_DIR) {
				this.lex();
			}else {
				//ERROR
				this.msg("ERRO: PARENTESIS_DIR esperado");
			}
		}else {
			//ERROR
			this.msg("ERRO: IDENTIFICADOR, LITERAL_INTEIRO, PARENTESIS_ESQ  esperados");
		}
		this.msg("Saiu factor(), token: " + this.nextToken());
	}
	
	private void msg(String cadeia) {
		System.out.println(cadeia);
	}
	
	private void lex() {
		if(this.contador==this.tokenLexemaList.size()) return;
		this.contador++;
	}
	
	private TokenLexema nextToken() {
		if(this.contador==this.tokenLexemaList.size()) return new TokenLexema(Token.FIM, Token.FIM.getValor()+"");
		return this.tokenLexemaList.get(this.contador);
	}	
}
