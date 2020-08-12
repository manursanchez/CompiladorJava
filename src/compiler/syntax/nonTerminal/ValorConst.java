package compiler.syntax.nonTerminal;

public class ValorConst extends NonTerminal {
	private String tValor;
	public ValorConst(String tipoValor) {
		// TODO Auto-generated constructor stub
		this.tValor=tipoValor;
	}
	public String gettValor() {
		return tValor;
	}
}
