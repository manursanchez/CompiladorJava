package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class Parametros extends NonTerminal {
	private ArrayList<String> lista;
	public Parametros() {
		// TODO Auto-generated constructor stub
		lista=new ArrayList<String>();
	}
	public ArrayList<String> getLista() {
		return lista;
	}
	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
	
}
