package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ListSentencia extends NonTerminal {
	private ArrayList<Sentencia> listaSentencias;
	
	public ListSentencia() {
		// TODO Auto-generated constructor stub
		listaSentencias = new ArrayList<Sentencia>();
	}
	
	public ArrayList<Sentencia> getListaSentencias() 
	{
		return listaSentencias;
	}

	public void agregarSentencia(Sentencia instruccion)
    {
		listaSentencias.add(instruccion);
	}
}
