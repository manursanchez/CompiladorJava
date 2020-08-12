package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExprLogica extends NonTerminal {
	private TypeIF tipo;
	
	public ExprLogica(TypeIF tipo) {
		// TODO Auto-generated constructor stub
		this.tipo=tipo;
	}
	public TypeIF getTipo() {
		return tipo;
	}
	public void setTipo(TypeIF tipo) {
		this.tipo = tipo;
	}
	
}
