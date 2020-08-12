package compiler.syntax.nonTerminal;

public class SentElse extends NonTerminal {
	private String instruccion;
	private Sentencias sentencias;
	public SentElse() {
		// TODO Auto-generated constructor stub
	}
	public SentElse(String instruccion, Sentencias sentencias) {
		super();
		this.instruccion = instruccion;
		this.sentencias = sentencias;
	}
	public String getInstruccion() {
		return instruccion;
	}
	public Sentencias getSentencias() {
		return sentencias;
	}
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}
	public void setSentencias(Sentencias sentencias) {
		this.sentencias = sentencias;
	}
	
}
