/**
 * 
 */
package compiler.syntax.nonTerminal;

import java.util.ArrayList;

/**
 * @author Cyberchorbo
 *
 */
public class ProcParam extends NonTerminal {

	/**
	 * 
	 */
	private ArrayList<String> listaParametros;
	private String tipoParametros;
	public ProcParam(ArrayList<String> lista,String tipo) {
		// TODO Auto-generated constructor stub
		this.listaParametros=lista;
		this.tipoParametros=tipo;
	}
	public ArrayList<String> getListaParametros() {
		return listaParametros;
	}
	public void setListaParametros(ArrayList<String> listaParametros) {
		this.listaParametros = listaParametros;
	}
	public String getTipoParametro() {
		return tipoParametros;
	}
	public void setTipoParametro(String tipoParametros) {
		this.tipoParametros = tipoParametros;
	}
}
