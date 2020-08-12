package compiler.soporte;

import java.util.List;
import compiler.CompilerContext;
import compiler.intermediate.Label;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolArray;
import compiler.semantic.symbol.SymbolConstant;
import compiler.semantic.symbol.SymbolVariable;
import compiler.syntax.nonTerminal.CabModule;
import compiler.syntax.nonTerminal.Cuerpo;
import compiler.syntax.nonTerminal.ExprArit;
import compiler.syntax.nonTerminal.ExprLogica;
import compiler.syntax.nonTerminal.Expresion;
import compiler.syntax.nonTerminal.IdArray;
import compiler.syntax.nonTerminal.Principal;
import compiler.syntax.nonTerminal.SentAsign;
import compiler.syntax.nonTerminal.SentElse;
import compiler.syntax.nonTerminal.SentFor;
import compiler.syntax.nonTerminal.SentIf;
import compiler.syntax.nonTerminal.SentWriteI;
import compiler.syntax.nonTerminal.SentWriteLn;
import compiler.syntax.nonTerminal.SentWriteS;
import compiler.syntax.nonTerminal.Sentencias;
import compiler.syntax.nonTerminal.ValorBooleano;
import compiler.syntax.nonTerminal.Variables;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.Quadruple;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalFactoryIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.intermediate.ValueIF;
import es.uned.lsi.compiler.intermediate.VariableIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.SemanticErrorManager;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.syntax.SyntaxErrorManager;
import compiler.soporte.Soporte;
public class SoporteCI {
	private static SyntaxErrorManager        syntaxErrorManager   = CompilerContext.getSyntaxErrorManager();
	private static SemanticErrorManager      semanticErrorManager = CompilerContext.getSemanticErrorManager ();
	private static ScopeManagerIF            scopeManager         = CompilerContext.getScopeManager ();
	//private staticFinalCodeFactoryIF        finalCodeFactory     = CompilerContext.getFinalCodeFactory ();
	public SoporteCI() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * M�todo para la propagaci�n (variables, valores y temporales)
	 * del CI de una expresi�n aritm�tica.
	 * La llamada viene desde la producci�n:
	 * expresion::=exprArit
	 * Realmente lo que se hace aqu� es insertar las variables 
	 * temporales que vienen de producciones inferiores.
	 * @param exp
	 * @param eA
	 * @return
	 */
	public static Expresion generarCIexp(Expresion exp,ExprArit eA) {
		ScopeIF scope = scopeManager.getCurrentScope();
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder (scope);
		TemporalIF eATemp = eA.getTemporal ();
		cb.addQuadruples (eA.getCode ());
		exp.setTemporal (eATemp);
		exp.setCode (cb.create ());
		
		return exp;
	}
	/**
	 * M�todo para la propagaci�n (variables, valores y temporales)
	 * del CI de una expresi�n logica.
	 * La llamada viene desde la producci�n:
	 * expresion::=exprLogica
	 * Realmente lo que se hace aqu� es insertar las variables 
	 * temporales que vienen de producciones inferiores.
	 * @param exp
	 * @param eL
	 * @return
	 */
	public static Expresion generarCIexp(Expresion exp,ExprLogica eL) {
		ScopeIF scope = scopeManager.getCurrentScope();
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder (scope);
		TemporalIF eATemp = eL.getTemporal ();
		cb.addQuadruples (eL.getCode ());
		exp.setTemporal (eATemp);
		exp.setCode (cb.create ());
		
		return exp;
	}
	/**
	 * M�todo que genera el CI de la expresion aritm�tica RESTA
	 * La llamada viene desde:
	 * exprArit::=expresion MINUS expresion
	 * @param exp1
	 * @param exp2
	 * @param eA
	 * @return
	 */
	
	public static ExprArit generarCIeAresta(Expresion exp1,Expresion exp2, ExprArit eA) {
		ScopeIF scope=scopeManager.getCurrentScope();
		TemporalFactoryIF tf=new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF exp1Temp=exp1.getTemporal();
		TemporalIF exp2Temp=exp2.getTemporal();
		TemporalIF expTemp =tf.create();
		cb.addQuadruples(exp1.getIntermediateCode());
		cb.addQuadruples(exp2.getIntermediateCode());
		cb.addQuadruple("SUB",expTemp,exp1Temp,exp2Temp);
		
		eA.setTemporal(expTemp);
		eA.setIntermediateCode(cb.create());
		return eA;
	}
	/**
	 * M�todo que genera el CI de la expresion aritm�tica PRODUCTO
	 * La llamada viene desde:
	 * exprArit::=expresion PRODUCTO expresion
	 * @param exp1
	 * @param exp2
	 * @param eA
	 * @return
	 */
	
	public static ExprArit generarCIeAproducto(Expresion exp1,Expresion exp2, ExprArit eA) {
		ScopeIF scope=scopeManager.getCurrentScope();
		TemporalFactoryIF tf=new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF exp1Temp=exp1.getTemporal();
		TemporalIF exp2Temp=exp2.getTemporal();
		TemporalIF expTemp =tf.create();
		cb.addQuadruples(exp1.getIntermediateCode());
		cb.addQuadruples(exp2.getIntermediateCode());
		cb.addQuadruple("MUL",expTemp,exp1Temp,exp2Temp);
		
		eA.setTemporal(expTemp);
		eA.setIntermediateCode(cb.create());
		return eA;
	}
	/**
	 * Genera el CI de un n�mero entero.
	 * La llamada viene desde la producci�n:
	 * expArit::= ENTERO
	 * @param eA
	 * @param numero
	 * @return
	 */
	public static ExprArit generarCIeAentero(ExprArit eA, String numero) {
		ScopeIF scope = scopeManager.getCurrentScope();
		TemporalFactory tf = new TemporalFactory(scope);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		TemporalIF temp = tf.create();
		ValueIF valor=new Value(numero);
		cb.addQuadruple("MV", temp, valor);
		eA.setTemporal(temp);
		eA.setIntermediateCode(cb.create());
		return eA;
	}
	/**
	 * Genera el CI de un valor booleano.
	 * La llamada viene desde la producci�n:
	 * expLogica::= vBooleano
	 * @param eL: expresion l�gica
	 * @param vB: valor booleano que viene de vBoolean
	 * @return eL: expresion boolena con el CI generado.
	 */
	public static ExprLogica generarCIeLboolean(ExprLogica eL,ValorBooleano vB) {
		ScopeIF scope = scopeManager.getCurrentScope();
		TemporalFactory tf = new TemporalFactory(scope);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		TemporalIF temp = tf.create();
		ValueIF valor=null;
		if (vB.getvBoolean().equals("TRUE")) {
			valor=new Value("1");
		}
		if(vB.getvBoolean().equals("FALSE")) {
			valor=new Value("0");
		}
		//System.out.println("El valor es: "+valor.getValue());
		cb.addQuadruple("MV", temp, valor);
		eL.setTemporal(temp);
		eL.setIntermediateCode(cb.create());
		return eL;
	}
	/**
	 * M�todo que genera el CI para expresi�n l�gica MAYOR
	 * La llamada viene desde:
	 * exprLogica::=expresion MAYOR expresion
	 * @param exp1
	 * @param exp2
	 * @param eL
	 * @return
	 */
	public static ExprLogica generarCImayorQue(Expresion exp1,Expresion exp2, ExprLogica eL) {
		ScopeIF scope=scopeManager.getCurrentScope();
		TemporalFactoryIF tf=new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF exp1Temp=exp1.getTemporal();
		TemporalIF exp2Temp=exp2.getTemporal();
		TemporalIF expTemp=tf.create();
		
		cb.addQuadruples(exp1.getIntermediateCode());
		cb.addQuadruples(exp2.getIntermediateCode());
		cb.addQuadruple("GR",expTemp,exp1Temp,exp2Temp);
		
		eL.setTemporal(expTemp);
		eL.setIntermediateCode(cb.create());
		return eL;
	}
	/**
	 * M�todo que genera el CI para expresi�n l�gica IGUAL
	 * La llamada viene desde:
	 * exprLogica::=expresion IGUAL expresion
	 * @param exp1
	 * @param exp2
	 * @param eL
	 * @return
	 */
	public static ExprLogica generarCIeLigual(Expresion exp1,Expresion exp2, ExprLogica eL) {
		ScopeIF scope=scopeManager.getCurrentScope();
		TemporalFactoryIF tf=new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF exp1Temp=exp1.getTemporal();
		TemporalIF exp2Temp=exp2.getTemporal();
		TemporalIF expTemp=tf.create();
		
		cb.addQuadruples(exp1.getIntermediateCode());
		cb.addQuadruples(exp2.getIntermediateCode());
		cb.addQuadruple("EQ",exp1Temp,exp2Temp);
		
		eL.setTemporal(expTemp);
		eL.setIntermediateCode(cb.create());
		return eL;
	}
	/**
	 * M�todo que genera el CI para expresi�n l�gica OR
	 * La llamada viene desde:
	 * exprLogica::=expresion OR expresion
	 * @param exp1
	 * @param exp2
	 * @param eL
	 * @return
	 */
	public static ExprLogica generarCIeLOR(Expresion exp1,Expresion exp2, ExprLogica eL) {
		ScopeIF scope=scopeManager.getCurrentScope();
		TemporalFactoryIF tf=new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF exp1Temp=exp1.getTemporal();
		TemporalIF exp2Temp=exp2.getTemporal();
		TemporalIF temp=tf.create();
		
		cb.addQuadruples(exp1.getIntermediateCode());
		cb.addQuadruples(exp2.getIntermediateCode());
		cb.addQuadruple("OR",temp,exp1Temp,exp2Temp);
		
		eL.setTemporal(temp);
		eL.setIntermediateCode(cb.create());
		return eL;
	}
	/**
	 * M�todo que genera el CI para expresi�n l�gica NOT
	 * La llamada viene desde:
	 * exprLogica::=NOT expresion
	 * @param exp
	 * @param eL
	 * @return
	 */
	public static ExprLogica generarCIeLNOT(Expresion exp, ExprLogica eL) {
		ScopeIF scope=scopeManager.getCurrentScope();
		//TemporalFactoryIF tf=new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF expTemp=exp.getTemporal();
		cb.addQuadruples(exp.getIntermediateCode());
		cb.addQuadruple("NOT",expTemp);
		
		eL.setTemporal(expTemp);
		eL.setIntermediateCode(cb.create());
		return eL;
	}
	/**
	 * M�todo para generar el CI de una variable o de una constante.
	 * La llamda viene desde la producci�n:
	 * variables::=IDENTIFICADOR
	 * Si desarrollo los subprogramas, he de controlarlos aqu� tambi�n
	 * @param ambito
	 * @param identificador
	 * @param v
	 * @return
	 */
	public static Variables generarCIvariablesID(ScopeIF ambito,Variables v) {
		TemporalFactory tf = new TemporalFactory(ambito);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(ambito);
		TemporalIF temp = tf.create();
		//TemporalIF vTemp = v.getTemporal();
		
		if (v.getIdentificador() instanceof SymbolConstant) {
			ValueIF valor=new Value(((SymbolConstant) v.getIdentificador()).getValorID());
			cb.addQuadruple("MV", temp, valor);
		} 
		else if (v.getIdentificador() instanceof SymbolVariable) {
			VariableIF var = new Variable((SymbolVariable) v.getIdentificador());
			cb.addQuadruple("MVA", temp,var);// SI CAMBIO POR MVP ME HACE OTRA COSA
			//cb.addQuadruple("MVP", temp,var);
		} 
		v.setTemporal(temp);
		v.setIntermediateCode(cb.create());
		return v;
	}
	
	/**
	 * M�todo para generar el CI de una sentencia de asignaci�n.
	 * La llamada viene desde la producci�n:
	 * senAsign::=variables DOSPUNTOSIGUAL expresion
	 * @param var
	 * @param exp
	 * @param sA
	 * @return
	 */
	public static SentAsign generarCIasignacion(Variables var, 
			Expresion exp,SentAsign sA) 
	{
		ScopeIF scope =scopeManager.getCurrentScope();
		TemporalFactoryIF tF = new TemporalFactory(scope);
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		TemporalIF temp = tF.create();
		
		TemporalIF vTemp=var.getTemporal();
		TemporalIF eTemp = exp.getTemporal();
		cb.addQuadruples(var.getIntermediateCode());
		cb.addQuadruples(exp.getIntermediateCode());
		
		cb.addQuadruple("MVA", temp, vTemp);
		cb.addQuadruple("MVA", temp, eTemp);//STP
		//Extraemos la direcci�n de la variable, para guardar el resultado.
		//Hay que comprobar si es un s�mbolo array o una variable
		System.out.println("El atributo dirArray: "+exp.isArray());
		Variable varCI;
		if(var.getIdentificador() instanceof SymbolArray) {
			varCI=new Variable((SymbolArray) var.getIdentificador());
			cb.addQuadruple("STP", vTemp,eTemp);
			//cb.addQuadruple("MVP", varCI,temp);
		}
		else {
				varCI=new Variable((SymbolVariable) var.getIdentificador());
				cb.addQuadruple("MVA", varCI,temp);
			}
		
		sA.setIntermediateCode(cb.create());
		return sA;
	}
	
	public static SentIf generarCIif(SentIf sIF, Expresion e, Sentencias s,SentElse sE) {
		ScopeIF scope = scopeManager.getCurrentScope();
		
		LabelFactory lF= new LabelFactory();
		LabelIF label1 = lF.create(); //Etiqueta para Else
		LabelIF label2 = lF.create(); //Etiqueta para fin de sentencia_IF
		TemporalIF expTemp = e.getTemporal();
		IntermediateCodeBuilder cb= new IntermediateCodeBuilder(scope);
		cb.addQuadruples(e.getIntermediateCode()); //Se a�ade el c�digo de la expresi�n
		cb.addQuadruple("BRF", expTemp ,label1); //Si no se cumple la condicion salto a label1
		cb.addQuadruples (s.getIntermediateCode()); //A�adimos c�digo sentencias del IF
		cb.addQuadruple("BR", label2); //Saltamos a la etiqueta label2
		cb.addQuadruple("INL", label1); // Insertarmos la etiqueta label1
		
		if(sE!=null)//Si hay sentencias en el ELSE a�adimos su CI correspondiente
			cb.addQuadruples(sE.getIntermediateCode()); 
		
		cb.addQuadruple("INL", label2); // Insertamos etiqueta label2
		sIF.setIntermediateCode(cb.create());
		
		return sIF;
	}
	
	/**
	 * M�todo para generar el CI que permitir� sacar mensajes por consola
	 * @param sWs: Objeto sentenciaWriteString
	 * @param listaTextos: lista de todas las sentencias de este tipo
	 * @param texto: texto asociado a la nueva sentencia.
	 * @return: devolvemos el objeto con las cuadruplas actualizadas
	 * para su propagaci�n
	 */
	public static SentWriteS generarCIWrString(SentWriteS sWs,List<QuadrupleIF> listaTextos,String texto) 
	{
		ScopeIF scope =scopeManager.getCurrentScope();
		TemporalFactory tF = new TemporalFactory(scope);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		LabelFactory lF = new LabelFactory();
		LabelIF lb = lF.create();
		TemporalIF temp = tF.create();
		//Guardamos la cuadrupla en el CI
		cb.addQuadruple("WRITESTRING", temp, lb);
		
		//Guardamos la cuadrupla DATA, de la cadena de caracteres en "listaCadenas" 
		//la cual recuperaremos en producci�n cuerpo par a�adirlas en el CI 
		listaTextos.add(new Quadruple("DATA", new Label(texto), lb));
		
		sWs.setIntermediateCode(cb.create());
		return sWs;
	}
	/**
	 * Agrega las cadenas generadas en el WRTITESTRING al final del CI
	 * 
	 * @param body
	 * @param lista
	 * @return
	 */
	public static Cuerpo agregarDatasCI(Cuerpo body,List<QuadrupleIF> lista) {
		ScopeIF scope =scopeManager.getCurrentScope();
		IntermediateCodeBuilder cb=new IntermediateCodeBuilder(scope);
		cb.addQuadruples(body.getIntermediateCode());
		cb.addQuadruple("HALT");//Aprovechamos para meter el final.
		
		//Al final de todo el CI, metemos las cuadruplas que contiene
		//las etiquetas DATA de textos para mostrar por consola.
		for(int c=0;c<lista.size();c++) {
			QuadrupleIF q=lista.get(c);
			cb.addQuadruple(q);
		}
		cb.addQuadruple("END");
		body.setIntermediateCode(cb.create());
		return body;
	}
	
	/**
	 * M�todo para generar el CI de la sentencia FOR
	 * @param sFor
	 * @param e1
	 * @param e2
	 * @param s
	 * @return
	 */
	public static SentFor generarCIfor(SentFor sFor,SymbolIF simbolo, 
			Expresion exp1,Expresion exp2, Sentencias s) {
		
		ScopeIF scope = scopeManager.getCurrentScope();
		LabelFactory lF=new LabelFactory();
		LabelIF label1 = lF.create(); //Etiqueta para continuar
		LabelIF label2 = lF.create(); //Etiqueta para finalizar
		
		TemporalIF exp1Temp = exp1.getTemporal();
		TemporalIF exp2Temp = exp2.getTemporal();
		TemporalIF exp3Temp; //Temporal que viene de la expresi�n de la sentencia de asignaci�n
		IntermediateCodeBuilder cb= new IntermediateCodeBuilder(scope);
		cb.addQuadruples (exp1.getIntermediateCode());
		cb.addQuadruples (exp2.getIntermediateCode());
		Variables v=new Variables(simbolo.getType(),simbolo);
		Variable varCI=new Variable((SymbolVariable)v.getIdentificador());
		
		//B�squeda de sentencia de asignaci�n y el temporal de su expresi�n
		exp3Temp=Soporte.extraeTemporalAsignacion(s, simbolo);
		//exp3Temp, he de compararlo con exp2Temp
		
		cb.addQuadruple("MVA", varCI,exp1Temp);
		cb.addQuadruple("INL", label1);
		
		//Sentencias del FOR
		
		cb.addQuadruples (s.getIntermediateCode());
		
		//Instrucci�n CMP creada nueva para control del FOR
		//Esta isntrucci�n de CI genera dos instrucciones de CF
		cb.addQuadruple("CMP",exp2Temp,exp3Temp,label2);
		cb.addQuadruple("MV",varCI ,exp3Temp);
		cb.addQuadruple("BR", label1);
		cb.addQuadruple("INL", label2);
		sFor.setIntermediateCode(cb.create());
		return sFor;
	}
	
	/**
	 * M�todo para generar el CI para un salto de l�nea.
	 * Usaremos la misma estructura que en WRITESTRING, pero mandaremos
	 * un DATA con un salto de l�nea en vez de una cadena.
	 * 
	 * @param sWln: objeto SentWriteLn
	 * @param listaTextos: la lista de cadenas del parser.
	 * @return
	 */
	public static SentWriteLn generarCIWln(SentWriteLn sWln,List<QuadrupleIF> listaTextos) {
		ScopeIF scope = scopeManager.getCurrentScope();
		TemporalFactory tf = new TemporalFactory(scope);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		TemporalIF temp = tf.create();
		LabelFactory lF = new LabelFactory();
		LabelIF lb = lF.create();
		cb.addQuadruple("WRITELN",lb);
		listaTextos.add(new Quadruple("DATA", new Label("\\n"),lb));
		sWln.setTemporal(temp);
		sWln.setIntermediateCode(cb.create());
		return sWln;
	}
	/**
	 * M�todo para generar el CI que permite mostrar por pantalla 
	 * un n�mero entero.
	 * @param sWi: objeto
	 * @param exp: expresion
	 * @return
	 */
	public static SentWriteI generarCIsWi(SentWriteI sWi,Expresion exp) {
		ScopeIF scope = scopeManager.getCurrentScope();
		TemporalFactory tf = new TemporalFactory(scope);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		TemporalIF temp = tf.create();
		TemporalIF eTemp = exp.getTemporal();
		cb.addQuadruples(exp.getIntermediateCode());
		if(exp.isArray()) {
			cb.addQuadruple("MVP",temp,eTemp);
			cb.addQuadruple("WRITEINT",temp);
		}else {
			cb.addQuadruple("WRITEINT",eTemp);
		}
		sWi.setTemporal(eTemp);  
		sWi.setIntermediateCode(cb.create());
		return sWi;
	}
	
	public static Variables generarCIarray(ScopeIF ambito,Variables v,SymbolIF id,IdArray iA) {
		
		TemporalFactory tF = new TemporalFactory(ambito);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(ambito);
		
		v.setDirArray(iA.getExp().isArray());//Se actualiza el atributo que indica que 
											//la expresi�n es una direcci�n de array
		
		//cb.addQuadruples (iA.getExp().getIntermediateCode()); 
		cb.addQuadruples (iA.getIntermediateCode()); 
		
		TemporalIF temp1 = iA.getTemporal();
		//TemporalIF temp2 = tF.create();
		TemporalIF temp3 = tF.create();
		//TemporalIF temp = tF.create();
		//TemporalIF temp4 = tF.create();
		
		Variable var=new Variable((SymbolArray)v.getIdentificador());
		//Aqu� genero las direcciones de memoria, donde se almacenar�n las expresiones
		//cb.addQuadruple("MVA", temp2, var);  
		
		cb.addQuadruple("ADD", temp3, temp1,var);
		//cb.addQuadruple("MVA", temp, temp3);

		v.setTemporal(temp3);
		v.setIntermediateCode(cb.create());
		return v;	
	}
	
	public static IdArray generarCIidArray(IdArray iA, Expresion exp) {
		ScopeIF scope = scopeManager.getCurrentScope();
		TemporalFactory tF = new TemporalFactory(scope);
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder (scope);
		exp.setArray(true);//Se indica que la expresi�n es una direcci�n de un array
		
		cb.addQuadruples(exp.getIntermediateCode());
		TemporalIF temp = exp.getTemporal ();
		TemporalIF temp2=tF.create();
		
		cb.addQuadruple("MUL", temp2, temp,"-1");//Convierto la expresion en direcci�n
		iA.setTemporal (temp2);
		iA.setIntermediateCode(cb.create());
		return iA;
	}
	
	//////////////////////////////////////////////////////////////////
	public static void listarCuadruplas(String mensaje,List<QuadrupleIF> lista) {
		// TODO Auto-generated method stub
		System.out.println(mensaje);
		for (QuadrupleIF q:lista){
			if (q.getFirstOperand() instanceof Variable) {
				Variable v=(Variable)q.getFirstOperand();
				//System.out.println("Reconozco la variable "+v.getName()+" como una variable con direcci�n: "+v.getAddress());
	    	}
			if (q.getFirstOperand() instanceof Temporal) {
				Temporal t=(Temporal)q.getFirstOperand();
				//System.out.println("Reconozco el temporal "+t.getName()+" como un temporal con direcci�n: "+t.getAddress());
			}
			System.out.println(q.toString());
		}
	}
}
