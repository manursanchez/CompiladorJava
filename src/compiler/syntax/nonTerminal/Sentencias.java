package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class Sentencias extends NonTerminal {
	private ArrayList<Sentencia> listaInstrucciones;
	public Sentencias(ArrayList<Sentencia> listaSentencias) {
		// TODO Auto-generated constructor stub
		listaInstrucciones = new ArrayList<Sentencia>();
		listaInstrucciones=listaSentencias;
	}
	public ArrayList<Sentencia> getlistaInstrucciones() 
	{
		return listaInstrucciones;
	}
}
