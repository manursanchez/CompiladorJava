package compiler.syntax.nonTerminal;
import java.util.ArrayList;
public class CadIdVar extends NonTerminal {
	
	private ArrayList<String> listaVariables;
	
	public CadIdVar() {
		// TODO Auto-generated constructor stub
		listaVariables=new ArrayList<String>();
	}
	
	public ArrayList<String> getListaVariables() {
		return listaVariables;
	}

	public void setListaVariables(ArrayList<String> listaVariables) {
		this.listaVariables = listaVariables;
	}
	public void agregarVariable(String variable)
    {
		listaVariables.add(variable);
	}
}
