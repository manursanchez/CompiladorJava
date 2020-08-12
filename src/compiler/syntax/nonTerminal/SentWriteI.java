package compiler.syntax.nonTerminal;

public class SentWriteI extends NonTerminal {
	private Expresion expresion;
	public SentWriteI(Expresion exp) {
		// TODO Auto-generated constructor stub
		this.expresion=exp;
	}
	public Expresion getExpresion() {
		return expresion;
	}
}
