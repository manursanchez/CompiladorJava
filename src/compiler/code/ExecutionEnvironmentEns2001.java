package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.intermediate.Variable;
import compiler.semantic.type.TypeSimple;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    
    private StringBuffer instruccionCF;
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }

    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {      
        //TODO: Student work CURSO 2019
    	
    	String op = quadruple.getOperation();
    	StringBuffer instruccionCF=new StringBuffer();
    	
    	switch(op)
		{
    		case "SUB":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirSUB(quadruple));
    			break;
    		case "ADD":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirADD(quadruple));
    			break;
    		case "MUL":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirMUL(quadruple));
    			break;
    		case "OR":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirOR(quadruple));
    			break;
    		case "NOT":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirNOT(quadruple));
    			break;
    		case "MVA":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirMVA(quadruple));
    			break;
    		case "MVP":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirMVP(quadruple));
    			break;
    		case "MV":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirMV(quadruple));
    			break;
    		case "STP":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirSTP(quadruple));
    			break;
    		case "WRITESTRING":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirWTS(quadruple));
    			break;
    		case "DATA":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirDATA(quadruple));
    			break;
    		case "HALT":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirHALT(quadruple));
    			break;
    		case "WRITELN":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirWRITELN(quadruple));
    			break;
    		case "WRITEINT":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirWRITEINT(quadruple));
    			break;
    		case "GR"://Comparacion mayor que
    			instruccionCF=instruccionCF.append(TraductorCF.traducirGR(quadruple));
    			break;
    		case "EQ"://igualdad
    			instruccionCF=instruccionCF.append(TraductorCF.traducirEQ(quadruple));
    			break;
    		case "BR":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirBR(quadruple));
    			break;
    		case "INL":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirINL(quadruple));
    			break;
    		case "BRF":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirBRF(quadruple));
    			break;
    		case "BRT":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirBRT(quadruple));
    			break;
    		case "END":
    			instruccionCF=instruccionCF.append(TraductorCF.traducirEND(quadruple));
    			break;
    		case "CMP"://Instrucción creada por mi para la comparación y control en el bucle FOR
    			instruccionCF=instruccionCF.append(TraductorCF.traducirCMP(quadruple));
    			break;
			default:
				break;
		}
    	if (instruccionCF.equals(null)) {	
    		instruccionCF.append("No hay instrucción");
    		return instruccionCF.toString();
    	}
    	else
    		return instruccionCF.toString();
        //return quadruple.toString(); 
    }//fin del traductor a CF
}//Fin de la clase
