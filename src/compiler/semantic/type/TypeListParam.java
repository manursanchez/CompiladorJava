package compiler.semantic.type;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

public class TypeListParam extends TypeBase {
	private ArrayList<String> listaTipo;
	public TypeListParam(ScopeIF scope) {
		// TODO Auto-generated constructor stub
		super(scope);
		listaTipo=new ArrayList<String>();
	}
	public ArrayList<String> getListaTipo() {
		return listaTipo;
	}
	public void setListaTipo(ArrayList<String> listaTipo) {
		this.listaTipo = listaTipo;
	}
}
