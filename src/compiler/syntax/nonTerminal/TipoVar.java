package compiler.syntax.nonTerminal;

public class TipoVar extends NonTerminal {
	private String tipo;
	
	public TipoVar(String tipoV) {
		// TODO Auto-generated constructor stub
		this.tipo=tipoV;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
