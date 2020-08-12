package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ParFuncion extends NonTerminal {
	private ArrayList<String> lista;
	public ParFuncion(ArrayList<String> lista) {
		// TODO Auto-generated constructor stub
		this.lista=lista;
	}
	public ArrayList<String> getLista() {
		return lista;
	}
	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
}
