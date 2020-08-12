package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ValorBooleano extends NonTerminal {
	private String vBoolean;
	private TypeIF tipo;
	public ValorBooleano(String valor) {
		// TODO Auto-generated constructor stub
		this.vBoolean=valor;
	}
	public String getvBoolean() {
		return vBoolean;
	}
	public void setvBoolean(String vBoolean) {
		this.vBoolean = vBoolean;
	}
	public TypeIF getTipo() {
		return tipo;
	}
	public void setTipo(TypeIF tipo) {
		this.tipo = tipo;
	}
	
}
