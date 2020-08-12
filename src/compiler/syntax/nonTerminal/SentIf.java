package compiler.syntax.nonTerminal;

public class SentIf extends NonTerminal {
	private Expresion expresion;
	private Sentencias sentencias;
	private SentElse sentenciaElse;
	
	public SentIf(Expresion expresion, Sentencias sentencias, SentElse sentenciaElse) {
		super();
		this.expresion = expresion;
		this.sentencias = sentencias;
		this.sentenciaElse = sentenciaElse;
	}
	public Expresion getExpresion() {
		return expresion;
	}
	public Sentencias getSentencias() {
		return sentencias;
	}
	public SentElse getSentenciaElse() {
		return sentenciaElse;
	}
	
}
