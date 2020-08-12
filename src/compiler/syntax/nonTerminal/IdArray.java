package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class IdArray extends NonTerminal {
	private TypeIF tipo;
	private Expresion exp;
	public IdArray(TypeIF tipo) {
		// TODO Auto-generated constructor stub
		this.tipo=tipo;
	}
	public IdArray(Expresion exp) {
		super();
		this.exp = exp;
	}
	public Expresion getExp() {
		return exp;
	}
	public void setExp(Expresion exp) {
		this.exp = exp;
	}
	public TypeIF getTipo() {
		return tipo;
	}
}
