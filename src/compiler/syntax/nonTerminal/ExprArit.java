package compiler.syntax.nonTerminal;
import es.uned.lsi.compiler.semantic.type.TypeIF;


public class ExprArit extends NonTerminal {
	private TypeIF tipo;
	
	public ExprArit(TypeIF tipo) {
		// TODO Auto-generated constructor stub
		this.tipo=tipo;
	}
	public TypeIF getTipo() {
		return tipo;
	}
}
