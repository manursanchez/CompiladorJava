package compiler.syntax.nonTerminal;

public class IntOBool extends NonTerminal {
	private String tipoVariable;
	
	public IntOBool(String tipoPrimitivo) {
		// TODO Auto-generated constructor stub
		this.tipoVariable=tipoPrimitivo;
	}
	public String getTipoVariable() {
		return tipoVariable;
	}
	public void setTipoVariable(String tipoVariable) {
		this.tipoVariable = tipoVariable;
	}

}
