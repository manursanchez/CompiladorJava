package compiler.code;

import compiler.CompilerContext;
import compiler.intermediate.Label;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import es.uned.lsi.compiler.code.FinalCodeFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.SemanticErrorManager;
import es.uned.lsi.compiler.syntax.SyntaxErrorManager;

public class TraductorCF {
	private static SyntaxErrorManager        syntaxErrorManager   = CompilerContext.getSyntaxErrorManager();
	private static SemanticErrorManager      semanticErrorManager = CompilerContext.getSemanticErrorManager ();
	private static ScopeManagerIF            scopeManager         = CompilerContext.getScopeManager ();
	private static FinalCodeFactoryIF        finalCodeFactory     = CompilerContext.getFinalCodeFactory ();
	
	public static StringBuffer traducirSUB(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("SUB " + o1 + ", " + o2 + "\n");
		instruccion.append("MOVE " + ".A " + ", " + r);
		return instruccion;
	}
	public static StringBuffer traducirADD(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("ADD " + o1 + ", " + o2 + "\n");
		instruccion.append("MOVE " + ".A " + ", " + r);
		return instruccion;
	}
	public static StringBuffer traducirMUL(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		
		instruccion.append("MUL " + o1 + ", " + o2 + "\n");
		instruccion.append("MOVE " + ".A " + ", " + r);
		return instruccion;
	}
	public static StringBuffer traducirOR(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("OR " + o1 + ", " + o2 + "\n");
		instruccion.append("MOVE " + ".A " + ", " + r);
		return instruccion;
	}
	public static StringBuffer traducirNOT(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String r = operacion(quadruple.getResult());
		instruccion.append("NOT " + r + "\n");
		return instruccion;
	}
	public static StringBuffer traducirGR(QuadrupleIF quadruple)//mayor que
	{
		StringBuffer instruccion = new StringBuffer();
		LabelFactory lf = new LabelFactory();
		LabelIF label1 = lf.create();
		LabelIF label2 = lf.create();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("CMP " + o2 + "," + o1 + "\n");
		instruccion.append("BN /" + label1 + "\n");
		instruccion.append("MOVE"+ " #0" + ", "+ r + "\n");	
		instruccion.append("BR" + " /"+ label2 + "\n");				
		instruccion.append(label1).append(" :\n");
		instruccion.append("MOVE"+ " #1"+ ", " + r + "\n");
		instruccion.append(label2).append(" :");
		return instruccion;
	}
	public static StringBuffer traducirEQ(QuadrupleIF quadruple)//Igual que
	{
		StringBuffer instruccion = new StringBuffer();
		LabelFactory lf = new LabelFactory();
		LabelIF label1 = lf.create();
		LabelIF label2 = lf.create();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("CMP " + o1 + "," + o2 + "\n");
		instruccion.append("BZ /" + label1 + "\n");
		instruccion.append("MOVE"+ " #0" + ", "+ r + "\n");	
		instruccion.append("BR" + " /"+ label2 + "\n");				
		instruccion.append(label1).append(" :\n");
		instruccion.append("MOVE"+ " #1"+ ", " + r + "\n");
		instruccion.append(label2).append(" :");
		return instruccion;
	}
	public static StringBuffer traducirMV(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("MOVE " + o1 + ", " + r);
		return instruccion;
	}
	public static StringBuffer traducirHALT(QuadrupleIF quadruple) 
	{
		StringBuffer instruccion = new StringBuffer();
		instruccion.append("HALT");
		instruccion.append("\n");
		return instruccion;
	}
	public static StringBuffer traducirMVA(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String r = operacion(quadruple.getResult());
		//Todas las variables serán globales
		instruccion.append("MOVE " + o1 + ", " + r);
		return instruccion;
	}
	public static StringBuffer traducirMVP(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String r = operacion(quadruple.getResult());
		//instruccion.append("MOVE " + o1 + ", " +r);
		instruccion.append("MOVE " + o1 + ", " +".R1\n");
		instruccion.append("MOVE" + " [.R1]"+ ", "+r);
		return instruccion;
	}
	public static StringBuffer traducirSTP(QuadrupleIF quadruple) 
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("MOVE " + r + ", " + ".R1" + "\n");
		instruccion.append("MOVE " + o1 + ", " + "[.R1]");
		
		return instruccion;
	}
	public static StringBuffer traducirWTS(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		instruccion.append("WRSTR /" + o1);
		return instruccion;
	}
	public static StringBuffer traducirDATA(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String res=operacion(quadruple.getResult());
		instruccion.append(o1+": DATA "+"\""+res+"\"");
		return instruccion;
	}
	public static StringBuffer traducirWRITELN(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String res=operacion(quadruple.getResult());
		instruccion.append("WRSTR /"+res);
		return instruccion;
	}
	public static StringBuffer traducirWRITEINT(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String res=operacion(quadruple.getResult());
		instruccion.append("WRINT "+res);
		return instruccion;
	}
	public static StringBuffer traducirBR(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String res=operacion(quadruple.getResult());
		instruccion.append("BR "+"/"+res);
		return instruccion;
	}
	public static StringBuffer traducirBRF(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String res= operacion(quadruple.getResult());
		instruccion.append("CMP " + res + ", " +"#0"+ "\n");
		instruccion.append("BZ " + "/"+ o1);	
		return instruccion;
	}
	/**
	 * Método para convertir el CI de for en CF
	 * ayudándome del libro: Compiladores, teoría e implementación
	 * de Jacinto Ruiz Catalán.
	 * @param quadruple
	 * @return
	 */
	public static StringBuffer traducirBRT(QuadrupleIF quadruple)
	{
		//System.out.println("El temporal trae TRADUCIR: "+quadruple.getResult());
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String res= operacion(quadruple.getResult());
		instruccion.append("CMP " + "#0" + ", " +res+ "\n");
		//instruccion.append("CMP " + res + ", " +"#0"+ "\n");
		instruccion.append("BZ " + "/"+ o1);	
		return instruccion;
	}
	public static StringBuffer traducirINL(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String res=operacion(quadruple.getResult());
		instruccion.append(res+": ");
		return instruccion;
	}
	public static StringBuffer traducirEND(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		instruccion.append("END");
		return instruccion;
	}
	
	public static StringBuffer traducirCMP(QuadrupleIF quadruple)
	{
		StringBuffer instruccion = new StringBuffer();
		String o1 = operacion(quadruple.getFirstOperand());
		String o2 = operacion(quadruple.getSecondOperand());
		String r = operacion(quadruple.getResult());
		instruccion.append("CMP " + r + ", " + o1+ "\n");//Comparo para ver si es positivo
		instruccion.append("BP " + "/" + o2);//Si lo es, salgo del bucle.
		
		/*instruccion.append("CMP " + r + ", " + o1+ "\n");//Si no lo es, comparo para ver si es
		//igual a 0, si es así, solo nos queda una vuelta al bucle
		instruccion.append("BZ " + "$1\n");
		instruccion.append("MOVE " + "#1" + ", " + ".R6");*/
		
		return instruccion;
	}
	private static String operacion(OperandIF op) {
		if (op instanceof Variable) {
			Variable v=(Variable)op;
			if(v.isGlobal()) 
				return "/-"+v.getAddress();
			else //Para el caso de las funciones. 
				System.out.println("La variable no es global");
				return "#-"+v.getAddress()+"[.IX]";
		}
		if (op instanceof Value) {
			return "#"+((Value)op).getValue();
		}
		if (op instanceof Temporal) {
			return "#-"+((Temporal)op).getAddress()+"[.IX]";
		}
		if (op instanceof Label) {
			return ((Label)op).getName();
		}
		return null;
	}
	
}	
