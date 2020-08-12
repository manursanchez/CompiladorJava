/**
 * 
 */
package compiler.syntax.nonTerminal;

/**
 * @author Manuel Rodriguez Sanchez
 *
 */
public class TipoRetorno extends NonTerminal {

	/**
	 * 
	 */
	private String tipoRetorno;
	public TipoRetorno(String tipo) {
		// TODO Auto-generated constructor stub
		this.tipoRetorno=tipo;
	}
	public String getTipoRetorno() {
		return tipoRetorno;
	}
	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
	
}
