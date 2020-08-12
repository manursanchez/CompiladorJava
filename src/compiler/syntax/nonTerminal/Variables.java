package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Variables extends NonTerminal {
	private TypeIF tipo;
	private SymbolIF identificador;
	private boolean dirArray=false;
	public Variables(TypeIF tipo,SymbolIF identificador) {
		// TODO Auto-generated constructor stub
		this.tipo=tipo;
		this.identificador=identificador;
	}
	
	public TypeIF getTipo() {
		return tipo;
	}
	public SymbolIF getIdentificador() {
		return identificador;
	}

	public boolean isDirArray() {
		return dirArray;
	}

	public void setDirArray(boolean dirArray) {
		this.dirArray = dirArray;
	}
	
	
}
