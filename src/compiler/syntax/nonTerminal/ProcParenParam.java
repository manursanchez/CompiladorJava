package compiler.syntax.nonTerminal;

public class ProcParenParam extends NonTerminal {

	private ProcListParam listaParametros;
	public ProcParenParam(ProcListParam lista) {
		// TODO Auto-generated constructor stub
		this.listaParametros=lista;
	}
	public ProcListParam getListaParametros() {
		return listaParametros;
	}
	public void setListaParametros(ProcListParam listaParametros) {
		this.listaParametros = listaParametros;
	}
	
}
