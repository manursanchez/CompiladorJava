package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class SentFor extends NonTerminal {
	
	private SymbolIF simbolo;
	private Expresion exp1;
	private Expresion exp2;
	private Sentencias sentencias;
	
	public SentFor() {
		// TODO Auto-generated constructor stub
	}

	public SentFor(SymbolIF simbolo, Expresion exp1, Expresion exp2, Sentencias sentencias) {
		super();
		this.simbolo = simbolo;
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.sentencias = sentencias;
	}

	public SymbolIF getSimbolo() {
		return simbolo;
	}

	public Expresion getExp1() {
		return exp1;
	}

	public Expresion getExp2() {
		return exp2;
	}

	public Sentencias getSentencias() {
		return sentencias;
	}
}
