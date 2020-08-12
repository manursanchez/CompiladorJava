package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ProcListParam extends NonTerminal {
	private ArrayList<ProcParam> listaObjeto;
	
	public ProcListParam() {
		// TODO Auto-generated constructor stub
		listaObjeto=new ArrayList<ProcParam>();
	}
	
	public ArrayList<ProcParam> getListaObjeto() {
		return listaObjeto;
	}

	public void setListaObjeto(ArrayList<ProcParam> listaObjeto) {
		this.listaObjeto = listaObjeto;
	}

	public void agregarObjetoConListaParametros(ProcParam objeto)
    {
		listaObjeto.add(objeto);
	}

}
