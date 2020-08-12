package compiler.syntax.nonTerminal;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expresion extends NonTerminal {
	private TypeIF tipoExpresion;
	private boolean Array=false;
	
	public Expresion(TypeIF tipoExpresion) {
		// TODO Auto-generated constructor stub
		this.tipoExpresion=tipoExpresion;
	}
	public TypeIF getTipoExpresion() {
		return tipoExpresion;
	}
	/*
	 * Estos los he creado para saber si la expresion forma parte de 
	 * una dirección de array
	 */
	public boolean isArray() {
		return Array;
	}
	public void setArray(boolean array) {
		Array = array;
	}
}
