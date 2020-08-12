package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolVariable.
 */

// TODO: Student work
//       Include properties to characterize parameters

public class SymbolParameter
    extends SymbolBase
{  
   private SymbolIF simboloArray;
    /**
     * Constructor for SymbolParameter.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolParameter (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    } 
    /**
     * Constructor para los parametros de tipos definidos. 
     * En este caso para parámetros de tipo array.
     * @param scope: ámbito
     * @param name: nombre del parametro de tipo predefinido array
     * @param type:nombre del tipo predefinido array
     * @param simboloArray: guardo en el simbolo parametro, la informacion completa
     * 		del simbolo predefinido como array. Esto permitirá extraer el tipo primitivo y
     * 		el valor de los rangos para, por ejemplo, las expresiones.
     */
    public SymbolParameter (ScopeIF scope, 
            String name,
            TypeIF type,SymbolIF simboloA)
    {
    	super (scope, name, type);
    	this.simboloArray=simboloA;
    }
	public SymbolIF getSimboloArray() {
		return simboloArray;
	}
	public void setSimboloArray(SymbolIF simboloArray) {
		this.simboloArray = simboloArray;
	}
    
}
