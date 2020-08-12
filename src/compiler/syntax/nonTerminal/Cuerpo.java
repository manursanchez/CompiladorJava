package compiler.syntax.nonTerminal;

public class Cuerpo extends NonTerminal {
	private String nombrePrograma;
	public Cuerpo(String nombre) {
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
