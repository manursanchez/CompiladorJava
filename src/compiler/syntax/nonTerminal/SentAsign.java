package compiler.syntax.nonTerminal;

public class SentAsign extends NonTerminal {
	private Variables var;
	private Expresion exp;
	public SentAsign(Variables variable,Expresion expresion) {
		// TODO Auto-generated constructor stub
		super();
		this.var=variable;
		this.exp=expresion;
	}
	public Variables getVar() {
		return var;
	}
	public Expresion getExp() {
		return exp;
	}
}
