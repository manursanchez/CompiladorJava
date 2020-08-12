package compiler.syntax.nonTerminal;

public class CabProcedure extends NonTerminal {
	private String nombrePrograma;
	public CabProcedure(String nombre) {
		// TODO Auto-generated constructor stub
		this.nombrePrograma=nombre;
	}
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	
}
