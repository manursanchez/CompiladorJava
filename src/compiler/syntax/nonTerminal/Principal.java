package compiler.syntax.nonTerminal;

public class Principal extends Axiom {
	private Cuerpo cuerpo;

	public Principal(Cuerpo cuerpo) {
		super();
		this.cuerpo = cuerpo;
	}

	public Cuerpo getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(Cuerpo cuerpo) {
		this.cuerpo = cuerpo;
	}
	
}
