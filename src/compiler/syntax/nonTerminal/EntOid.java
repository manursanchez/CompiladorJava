package compiler.syntax.nonTerminal;
import es.uned.lsi.compiler.semantic.symbol.*;
import compiler.semantic.symbol.*;

public class EntOid extends NonTerminal {
	private String numeroEntero;
	private SymbolConstant simbolo;
	
	public EntOid(String numero) {
		// TODO Auto-generated constructor stub
		this.numeroEntero=numero;
	}

	public EntOid(SymbolConstant simbolo) {
		this.simbolo=simbolo;
	}

	public String getNumeroEntero() {
		return numeroEntero;
	}

	public void setNumeroEntero(String numeroEntero) {
		this.numeroEntero = numeroEntero;
	}

	public SymbolConstant getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(SymbolConstant simbolo) {
		this.simbolo = simbolo;
	}
}
