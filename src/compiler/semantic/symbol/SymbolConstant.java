package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolConstant.
 */

// TODO: Student work
//       Include properties to characterize constants

public class SymbolConstant
    extends SymbolBase
{
	private String valorID;
    /**
     * Constructor for SymbolConstant.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolConstant (ScopeIF scope,
                           String name,
                           TypeIF type,String valor)
    {
        super (scope, name, type);
        valorID=valor; //La dejo por si acaso necesito el valor
    }
	public String getValorID() {
		return valorID;
	}
	public void setValorID(String valorID) {
		this.valorID = valorID;
	}
    
}
