package compiler.syntax.nonTerminal;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class SentProcedure extends NonTerminal {
	private SymbolIF simbolo;
	private ArrayList<String> listaPrm;
	
	//Constructor de sentencia procedimiento con parámetros
	public SentProcedure(SymbolIF simbolo, ArrayList<String> listaPrm) {
		super();
		this.simbolo = simbolo;
		this.listaPrm = listaPrm;
	}
	//Constructor de sentencia procedimiento sin parámetros
	public SentProcedure(SymbolIF simbolo) {
		super();
		this.simbolo = simbolo;
	}

	public ArrayList<String> getListaPrm() {
		return listaPrm;
	}

	public SymbolIF getSimbolo() {
		return simbolo;
	}
}
