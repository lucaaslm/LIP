package analiseLex;

import java.util.ArrayList;
import java.util.List;

public class AnaLex {
	
	private List<TokenLexema> tokenLexemaList;
	private String cadeia;
	private int i;
	private char[] cadeiaArray;

	public AnaLex(String cadeia) {
		this.cadeia = cadeia;
		this.tokenLexemaList = new ArrayList<TokenLexema>();
	}

	public List<TokenLexema> analex() {
		if (this.cadeia == null)
			return null;
		 
		cadeiaArray = this.cadeia.toCharArray();
		
		for (i = 0; i < cadeiaArray.length; i++) {
			if (!this.ehParentesis(cadeiaArray[i]))
			if (!this.ehOperador(cadeiaArray[i]))
			if (!this.ehPontoVirgula(cadeiaArray[i]))
			if (!this.ehLiteralInteiro(cadeiaArray[i]))
			if (!this.ehIdentificador(cadeiaArray[i]));
			//if (!Character.isWhitespace(cadeiaArray[i])) {
	        //    tokenLexemaList.add(new TokenLexema(Token.ERRO,
	        //            String.valueOf(cadeiaArray[i])));
	        //    return null;
			//}
		}
		return this.tokenLexemaList;
	}

	public boolean ehIdentificador(char c) {
		if (Character.isLetter(c)) {
			String identificador = "";
			do {
				identificador += cadeiaArray[i];
				i = i + 1;
			} while (i != cadeiaArray.length && (Character.isLetter(cadeiaArray[i]) || Character.isDigit(cadeiaArray[i])));
				this.tokenLexemaList.add(new TokenLexema(Token.IDENTIFICADOR, identificador));

				if (i != cadeiaArray.length) {
					i = i - 1;
				}
				return true;
		}
		return false;
	}
	
	private boolean ehOperador(char c) {
		if (c == Token.OPERADOR_ATRIB.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_ATRIB, "" + Token.OPERADOR_ATRIB.getValor()));
			return true;
		}else if(c == Token.OPERADOR_MULT.getValor()){
            StringBuilder sequencia = new StringBuilder();
            	do {
            		sequencia.append(cadeiaArray[i]).append("");
	                i += 1;
	            } while (i != cadeiaArray.length &&
	        		cadeiaArray[i] == Token.OPERADOR_MULT.getValor()
	            );
					if(!math(sequencia.toString()) && sequencia.toString().length() == 1){
						tokenLexemaList.add(new TokenLexema(Token.OPERADOR_MULT, "" + Token.OPERADOR_MULT.getValor()));
					}else{
						if(sequencia.toString().length() > 2){
							tokenLexemaList.add(new TokenLexema(Token.ERRO,
									sequencia.toString()));
						}
					}
          	return true;
		} else if (c == Token.OPERADOR_SOMA.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_SOMA, "" + Token.OPERADOR_SOMA.getValor()));
			return true;
		} else if (c == Token.OPERADOR_SUBT.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_SUBT, "" + Token.OPERADOR_SUBT.getValor()));
			return true;
		} else if (c == Token.OPERADOR_DIVI.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_DIVI, "" + Token.OPERADOR_DIVI.getValor()));
			return true;
		} 
		return false;
	}

	private boolean ehParentesis(char c) {
		if (c == Token.PARENTESIS_ESQ.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PARENTESIS_ESQ, "" + Token.PARENTESIS_ESQ.getValor()));
			return true;
		} else if (c == Token.PARENTESIS_DIR.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PARENTESIS_DIR, "" + Token.PARENTESIS_DIR.getValor()));
			return true;
		}
		return false;
	}
		
	public boolean ehLiteralInteiro(char c) {
		if (Character.isDigit(c)) {
            StringBuilder digito = new StringBuilder();
			do {
				digito.append(cadeiaArray[i]).append("");
				i = i + 1;
			} while (i != cadeiaArray.length && Character.isDigit(cadeiaArray[i]) || cadeiaArray[i] == '.');

            int temPonto = digito.toString().length() - digito.toString().replace(".", "").length();

            if(temPonto == 0){
                tokenLexemaList.add(new TokenLexema(Token.LITERAL_INTEIRO, digito.toString()));
                return true;
            }else{
                return !ehFloat(digito.toString(), temPonto);
            }
		}

		return false;
	}
	
	private boolean ehFloat(String digito, int qtdPontos){
        if(qtdPontos == 1){
            tokenLexemaList.add(new TokenLexema(Token.LITERAL_FLOAT, digito));
            return true;
        }
        tokenLexemaList.add(new TokenLexema(Token.ERRO, digito.toString()));
        return false;
    }
	
    private boolean math(String elev){
        if(elev.toString().length() == 2){
            tokenLexemaList.add(new TokenLexema(Token.OPERADOR_ELEV, elev));
            return true;
        }
        return false;
    }
	
	public boolean ehPontoVirgula(char c) {
		if (c == Token.PONTO_VIRGULA.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PONTO_VIRGULA, "" + Token.PONTO_VIRGULA.getValor()));
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String res = "";
		for (TokenLexema tl : this.tokenLexemaList) {
			res += tl;
			res += "\n";
		}
		return res;
	}

}
