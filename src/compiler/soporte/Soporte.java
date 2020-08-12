package compiler.soporte;
import java_cup.runtime.Symbol;
import java.util.*;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalFactoryIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.lexical.*;
//import es.uned.lsi.compiler.code.*;
//import es.uned.lsi.compiler.intermediate.*;
import es.uned.lsi.compiler.semantic.*;
import es.uned.lsi.compiler.semantic.symbol.*;
import es.uned.lsi.compiler.semantic.type.*;
import es.uned.lsi.compiler.syntax.*;

import compiler.CompilerContext;
import compiler.intermediate.Temporal;
import compiler.lexical.*;
import compiler.syntax.nonTerminal.*;
import compiler.semantic.*;
import compiler.semantic.symbol.*;
import compiler.semantic.type.*;
//import compiler.intermediate.*;
//import compiler.code.*;

public class Soporte {
	private static SyntaxErrorManager        syntaxErrorManager   = CompilerContext.getSyntaxErrorManager();
	private static SemanticErrorManager      semanticErrorManager = CompilerContext.getSemanticErrorManager ();
	private static ScopeManagerIF            scopeManager         = CompilerContext.getScopeManager ();
	//private staticFinalCodeFactoryIF        finalCodeFactory     = CompilerContext.getFinalCodeFactory ();
	public Soporte() {
		// TODO Auto-generated constructor stub
		super();
	}
	public static void mensajes(String mensaje)
	{
		semanticErrorManager.semanticDebug(mensaje);
	}
	
	public static void creaAmbito(String nombre)
	{
		//Tengo que controlar la llegada de procedimiento o funcion//
		ScopeIF ambito = scopeManager.openScope(nombre); 
		gestionTablaTipos(ambito);
		//mensajes("Abierto nuevo ambito: "+ambito.getName());
	}
	
	public static void gestionTablaTipos(ScopeIF ambito)
	{
		//Introducimos en la tabla de tipos los tipos a usar en el �mbito
				// Creamos una variable tipo tabla y le a�adimos la del �mbito
				TypeTableIF tablaTipos=ambito.getTypeTable();
				
				//Creamos los tipos
				TypeSimple tipoInteger=new TypeSimple(ambito);
				tipoInteger.setName("Integer");
				TypeSimple tipoBoolean=new TypeSimple(ambito);
				tipoBoolean.setName("Boolean");
				TypeProcedure tipoVoid=new TypeProcedure(ambito);
				tipoVoid.setName("Void");
				TypeArray tipoArray=new TypeArray(ambito);
				tipoArray.setName("Array");
				
				//Tipo especial para almacenar en una lista los tipos de
				//los parametros para la comparaci�n.
				TypeListParam tipoLista=new TypeListParam(ambito);
				tipoLista.setName("Lista");
				
				//Agregamos todos los tipos creados en la tabla de tipos
				tablaTipos.addType(tipoInteger);
				tablaTipos.addType(tipoBoolean);
				tablaTipos.addType(tipoVoid);
				tablaTipos.addType(tipoArray);
				tablaTipos.addType(tipoLista);
				//System.out.println(tablaTipos.getType("Integer"));
	}
	/**
	 * M�todo para la gesti�n del nombre del programa
	 * Lo voy a tratar como un procedimiento sin par�metros
	 * @param nombrePrograma
	 */
	public static void gestionNombrePrograma(String nombrePrograma)
	{
		ScopeIF scope=scopeManager.getCurrentScope();
		SymbolTableIF sTable=scope.getSymbolTable();
		if (sTable.containsSymbol(nombrePrograma)){
			semanticErrorManager.semanticFatalError("Error, s�mbolos repetidos en el mismo �mbito.");
			}
		else {
			TypeIF tipoSimboloProc = scopeManager.searchType("Void");
			SymbolProcedure sProc=new SymbolProcedure(scope,nombrePrograma,tipoSimboloProc);
			//System.out.println("Se almacena s�mbolo: "+nombrePrograma);
			sTable.addSymbol(sProc);
		}	
	}
	/**
	 * @author Cyberchorbo
	 * M�todo para la gesti�n de los s�mbolos que representan procedimientos y funciones
	 * @param nombreSimbolo
	 * @param tr este objeto me indica si hay configurado un tipo retorno o no.
	 * 			lo que significar� si es una funci�n o un procedimiento
	 */
	public static void gestionSimboloProcFun(String nombreSimbolo,TipoRetorno tr)
	{
		ScopeIF scope=scopeManager.getCurrentScope();
		SymbolTableIF sTable=scope.getSymbolTable();
		if (sTable.containsSymbol(nombreSimbolo)){
			semanticErrorManager.semanticFatalError("Error, s�mbolos repetidos en el mismo �mbito.");
			}
		else {
			if(tr==null) 
			{//Es un procedimiento
				TypeIF tipoSimboloProc = scopeManager.searchType("Void");
				SymbolProcedure sProc=new SymbolProcedure(scope,nombreSimbolo,tipoSimboloProc);
				sTable.addSymbol(sProc);
			}
			else
			{//Es una funcion
				if (tr.getTipoRetorno().equals("INTEGER"))
				{
					TypeIF tipoSimboloFun = scopeManager.searchType("Integer");
					SymbolFunction sFun=new SymbolFunction(scope,nombreSimbolo,tipoSimboloFun);
					sTable.addSymbol(sFun);
				}
				else
				{
					TypeIF tipoSimboloFun = scopeManager.searchType("Boolean");
					SymbolFunction sFun=new SymbolFunction(scope,nombreSimbolo,tipoSimboloFun);
					sTable.addSymbol(sFun);
				}
			}
		}	
	}
	/**
	 * M�todo para la gesti�n de los s�mbolos que representan constantes.
	 * @param nombreSimbolo
	 * @param datoValor
	 */
	public static void gestionSimboloConstante(String nombreSimbolo, String datoValor)
	{
		//System.out.println("El nombre que entra es: "+nombreSimbolo);
		//semanticErrorManager.semanticError("El valor que entra es: "+datoValor);
		ScopeIF scope=scopeManager.getCurrentScope();
		SymbolTableIF sTable=scope.getSymbolTable();
		// 	Preguntamos si esta tabla contiene el s�mbolo
		if (sTable.containsSymbol(nombreSimbolo)){
			semanticErrorManager.semanticFatalError("Error, s�mbolos repetidos en el mismo �mbito.");
			}
		else {
			//Preguntamos si el valor que viene de datoValor es booleano
			if (datoValor.equals("TRUE") || datoValor.equals("FALSE"))
			{
				//Creamos el tipo, mediante el contructor de tipos
				TypeIF tipoSimboloConst = scopeManager.searchType("Boolean");
				// Creamos el objeto s�mbolo constante
				SymbolConstant sc=new SymbolConstant(scope,nombreSimbolo,tipoSimboloConst,datoValor);
				//Almacenamos el s�mbolo en la tabla.
				sTable.addSymbol(sc);
			}
			else//el valor es INTEGER
			{
				//Creamos el tipo, mediante el contructor de tipos
				TypeIF tipoSimboloConst = scopeManager.searchType("Integer");
				// Creamos el objeto s�mbolo constante
				SymbolConstant sc=new SymbolConstant(scope,nombreSimbolo,tipoSimboloConst,datoValor);
				//Almacenamos el s�mbolo en la tabla.
				sTable.addSymbol(sc);
			}
		}
	}
	public static void gestionSimboloArray(String nombreMatriz,EntOid rangoInicio, EntOid rangoFin,IntOBool tipo )
	{
		//gestion rango inicio
		int valorInicio;
		String tipoPrimitivo=tipo.getTipoVariable();
	
		if(rangoInicio.getSimbolo()==null)
			valorInicio=Integer.parseInt(rangoInicio.getNumeroEntero());
		else
			valorInicio=Integer.parseInt(rangoInicio.getSimbolo().getValorID());
		
		//gestion rango fin
		int valorFin;
		if(rangoFin.getSimbolo()==null)
			valorFin=Integer.parseInt(rangoFin.getNumeroEntero());
		else
			valorFin=Integer.parseInt(rangoFin.getSimbolo().getValorID());
		
		//Gestion del simbolo de tipo array
		if (valorFin>valorInicio)
		{
			ScopeIF scope=scopeManager.getCurrentScope();
			//Recupero la tabla de s�mbolos del �mbito actual
			SymbolTableIF sTable=scope.getSymbolTable();
			if (sTable.containsSymbol(nombreMatriz))
			{
				semanticErrorManager.semanticFatalError("Error, s�mbolos repetidos en el mismo �mbito.");
			}
			else
			{
				TypeIF tipoSimboloArray = scopeManager.searchType("Array");
				SymbolArray sMatrizArray = new SymbolArray(scope, nombreMatriz,tipoSimboloArray,valorInicio,valorFin,tipoPrimitivo);
				sTable.addSymbol(sMatrizArray);
			}
		}
		else
			semanticErrorManager.semanticFatalError("Rango de la matriz incorrecto. "
												+ "Valor de inicio: "+valorInicio+". "
												+ "Valor de fin: "+valorFin);
		
		/*semanticErrorManager.semanticDebug("Valor inicio tiene: "+valorInicio);
		semanticErrorManager.semanticDebug("Valor fin tiene: "+valorFin);
		semanticErrorManager.semanticDebug("Tipo es: "+tipo.getTipoVariable());*/
	}
	/**
	 * M�todo para la gesti�n de los s�mbolos que representan n�meros enteros.
	 * @param nombreSimbolo
	 * @param datoValor
	 */
	public static void gestionSimboloVariable(ArrayList<String> listaVar, String tipo)
	{
		String nombreSimbolo;
		ScopeIF scope=scopeManager.getCurrentScope();
		//Recupero la tabla de s�mbolos del �mbito actual
		SymbolTableIF sTable=scope.getSymbolTable();
		for (int contador=0;contador<listaVar.size();contador++)
		{
			nombreSimbolo=listaVar.get(contador);
			
			if (sTable.containsSymbol(nombreSimbolo)){
				semanticErrorManager.semanticFatalError("Error, s�mbolos repetidos en el mismo �mbito.");
				}
			else
			{
				if (tipo.equals("BOOLEAN"))
				{
					TypeIF tipoSimboloBooleano = scopeManager.searchType("Boolean");
					SymbolVariable sBooleano = new SymbolVariable(scope, nombreSimbolo,tipoSimboloBooleano);
					sTable.addSymbol(sBooleano);
				}
				else if (tipo.equals("INTEGER"))
				{
					TypeIF tipoSimboloEntero = scopeManager.searchType("Integer");
					SymbolVariable sEntero = new SymbolVariable(scope, nombreSimbolo,tipoSimboloEntero);
					sTable.addSymbol(sEntero);
				}
					else
					{
						//creamos un simbolo Array
						TypeIF tipoSimboloArray = scopeManager.searchType("Array");
						
						//Comprobamos que exista el simbolo de tipo, y recogemos el simbolo completo
						SymbolIF idTipo=identificadorDeTipo(tipo);
						
						//Hacemos un cast para acceder a sus variables encapsuladas
						SymbolArray sTipoArray=(SymbolArray)idTipo;
						int rangoInicio=sTipoArray.getRangoInicio();
						int rangoFin=sTipoArray.getRangoFin();
						String tipoPrimitivo=sTipoArray.getTipoPrimitivo();
						
						//Creamos el s�mbolo array, para tener la m�xima informcion de esta variable
						//de tipo array.
						SymbolArray sArray = new SymbolArray(scope, nombreSimbolo,tipoSimboloArray,
															rangoInicio,rangoFin,tipoPrimitivo);
						
						//La agregamos a la tabla de s�mbolos.
						sTable.addSymbol(sArray);
					}
			}
		}
	}
	/**
	 * M�todo para almacenar los parametros en las tablas de simbolos
	 * @param parametros: objeto que contiene una lista de objetos con una lista
	 * de parametros en cada objeto
	 * 
	 */
	public static void gestionParametros(ProcParenParam parametros)
	{
		/**
		 * nObjetos contendr� el n�mero de objetos del array de ProcListParam
		 */
		
		//Si no hay paramtros declarados, no hacemos nada
		if(parametros.getListaParametros()!=null)
		{
			int nObjetos=parametros.getListaParametros().getListaObjeto().size();
			String nombreParametro;
		
			//Recuperamos el ambito actual
			ScopeIF scope=scopeManager.getCurrentScope();
		
			//Recupero la tabla de s�mbolos del �mbito actual
			SymbolTableIF sTable=scope.getSymbolTable();
		
			//Vamos a recuperar los parametros que hemos traido del arbol
			for (int contadorObjetos=0;contadorObjetos<nObjetos;contadorObjetos++) 
			{
				//System.out.println("Vienen los objetos "+parametros.getListaParametros().getListaObjeto().get(contadorObjetos));
				//extraer cada uno de los parametros del array y su tipo
				String tipo=parametros.getListaParametros().getListaObjeto().get(contadorObjetos).getTipoParametro();
				int nParametros=parametros.getListaParametros().getListaObjeto().get(contadorObjetos).getListaParametros().size();
				for (int contadorParametros=0;contadorParametros<nParametros;contadorParametros++)
				{
					nombreParametro=parametros.getListaParametros().getListaObjeto().get(contadorObjetos).getListaParametros().get(contadorParametros);

					//agregamos parametro a la tabla de s�mbolos del ambito funcion/proc
					if (sTable.containsSymbol(nombreParametro)){
						semanticErrorManager.semanticFatalError("Error, s�mbolos repetidos en el mismo �mbito.");
						}
					else
					{
						if (tipo.equals("BOOLEAN"))
						{
							//System.out.println("Meto parametro BOOLEAN");
							TypeIF tipoSimboloBooleano = scopeManager.searchType("Boolean");
							SymbolParameter sBooleano = new SymbolParameter(scope, nombreParametro,tipoSimboloBooleano);
							sTable.addSymbol(sBooleano);
							guardarTipoEnLista("BOOLEAN");
						}
						else if (tipo.equals("INTEGER"))
						{
							//System.out.println("Meto parametro INTEGER");
							TypeIF tipoSimboloEntero = scopeManager.searchType("Integer");
							SymbolParameter sEntero = new SymbolParameter(scope, nombreParametro,tipoSimboloEntero);
							sTable.addSymbol(sEntero);
							guardarTipoEnLista("INTEGER");
						}
						else
						{
							//System.out.println("Meto parametro ARRAY");
							TypeIF tipoSimboloArray = scopeManager.searchType("Array");
							//Buscamos en las tablas de s�mbolos, el s�mbolo array que ahora hace la funci�n de tipo
							SymbolIF idTipo=identificadorDeTipo(tipo);
							//Si lo hemos encontrado,pues creamos el par�metro, pero usando 
							//el constructor de parametros con simbolo-tipo array.
							SymbolParameter sArray = new SymbolParameter(scope, nombreParametro,tipoSimboloArray,idTipo);
							sTable.addSymbol(sArray);
							guardarTipoEnLista("ARRAY");
						}
					}
				}
			}
		}
	}
	
	//Busqueda de un s�mbolo sin recursi�n. Sin usar, funciona de la
	//otra forma.
	public static void buscaSimbolo(String simbolo)
	{
		SymbolIF s;
		s=scopeManager.searchSymbol(simbolo);
		semanticErrorManager.semanticDebug("He encontrado el simbolo: "+s);
	}
	
	/**
	 * Funci�n recursiva que busca un s�mbolo con ese nombre en el �mbito proporcionado
	 * y de no encontrarlo vuelve a llamarse con el padre del �mbito
	 * @param scope El �mbito donde empezar a buscar
	 * @param variable El nombre del s�mbolo
	 * @return el s�mbolo encontrado hacerlo booleano, si lo encuentra llama al m�todo 
	 * 			comprobar tipo para ver si son iguales.
	 */
	public static SymbolIF buscarSimbolo(ScopeIF ambito, String nombreSimbolo) {
		try{
			SymbolTableIF sTable = ambito.getSymbolTable();
			if (sTable.containsSymbol(nombreSimbolo)) {
				//Si encuentra el s�mbolo, lo devolvemos
				return sTable.getSymbol(nombreSimbolo);
			} else {
				//Si no lo encuentra, hago la llamada recursiva mandando el �mbito padre
				return buscarSimbolo(ambito.getParentScope(), nombreSimbolo);
			}
		} catch (NullPointerException e) {
		}
		semanticErrorManager.semanticFatalError("�No existe el s�mbolo '"+nombreSimbolo+"'!");
		return null;
	}
	
	/**
	 * M�todo para la busqueda y comprobacion de un simbolo de tipo array para una
	 * variable o par�metro declarado con este tipo.
	 * @param idArray: nombre del identificador de tipo array
	 * @return un objeto de tipo s�mbolo correspondiente a un array declarado en 
	 * 			la parte TYPE
	 */
	public static SymbolIF identificadorDeTipo(String idArray) {
		ScopeIF ambito = scopeManager.getCurrentScope();
    	SymbolArray simboloArray=(SymbolArray)Soporte.buscarSimbolo(ambito,idArray);
    	SymbolIF tv=simboloArray;
    	return tv;
	}
	/**
	 * M�todo para comprobar la igualdad del nombre de inicio y de fin de un subprograma
	 * o el m�dulo principal
	 * @param nombreInicio
	 * @param nombreFinal
	 */
	public static void comprobarNombresProgramas(String nombreInicio,String nombreFinal)
	{
		if (!nombreInicio.equals(nombreFinal))
			semanticErrorManager.semanticFatalError("Los nombres de inicio y fin del modulo: "+nombreInicio+", no coinciden");
	}
	/**
	 * M�todo para comparar los tipos de expresiones en operaciones
	 * aritemticas o l�gicas
	 * @param ex1
	 * @param ex2
	 * @param tipoCorrecto
	 */
	public static void compararExpresiones(Expresion ex1,Expresion ex2)
	{
		TypeIF expresion1=ex1.getTipoExpresion();
		TypeIF expresion2=ex2.getTipoExpresion();
		
		//Comparamos el tip mediante el nombre del tipo.
		if(!expresion1.getName().equals(expresion2.getName()))
		{
			semanticErrorManager.semanticFatalError("�Incompatibilidad de tipos "
													+ "en la expresi�n del �mbito: '"
													+ ""+scopeManager.getCurrentScope().getName()+"'!"
													+ ""+expresion1.getName()+" <> "+expresion2.getName());
		}		
	}
	
	/**
	 * M�todo para comparar el tipo de una asignacion y su expresi�n
	 */
	public static void compararAsignaciones(Variables var,Expresion exp)
	{
		TypeIF variable=var.getTipo();
		TypeIF expresion=exp.getTipoExpresion();
		//Comparamos el tip mediante el nombre del tipo.
		if(!variable.getName().equals(expresion.getName()))
		{
			semanticErrorManager.semanticFatalError("�Incompatibilidad de tipos "
													+ "en la asignaci�n! ");
		}		
	}
	/**
	 * Comprueba el tipo de una expresion
	 * @param tipoExpresion
	 * @param tipoCorrecto
	 */
	public static void comprobarTipo(TypeIF tipoExpresion,String tipoCorrecto) {
		if (!tipoExpresion.getName().equals(tipoCorrecto))
			semanticErrorManager.semanticFatalError("El tipo de la expresi�n no es "
					+ "correcto para la estructura del �mbito."+scopeManager.getCurrentScope().getName());
	}
	
	/**
	 * M�todo para devolver el tipo primitivo de un array en forma de TypeIF, que 
	 * permitir� construir el objeto Expresion.
	 * @param simbolo: el identificador del array
	 * @return: TypeIF
	 */
	public static TypeIF extraeTipoPrimitivo(SymbolIF simbolo) {
		TypeIF tPrimitivo;
		SymbolArray tSimbolo=(SymbolArray)simbolo;
		
		if(tSimbolo.getTipoPrimitivo().equals("BOOLEAN")) {
			tPrimitivo= scopeManager.searchType("Boolean");
			return tPrimitivo;
		}
		else if (tSimbolo.getTipoPrimitivo().equals("INTEGER")) {
			tPrimitivo=scopeManager.searchType("Integer");
			return tPrimitivo;
		}
			else {
				semanticErrorManager.semanticFatalError("Error en el tipo primitivo del array. Tiene que ser entero o booleano.");
			}
		return null;
	}
	/**
	 * M�todo para devolver el tipo de una funcion o en forma de TypeIF.
	 * @param simbolo: el identificador de la funci�n
	 * @return: TypeIF
	 */
	public static TypeIF extraeTipo(SymbolIF simbolo) {
		if (simbolo instanceof SymbolFunction) { //comprobamos que es una funci�n
			//semanticErrorManager.semanticDebug("Es un s�mbolo funci�n");
			TypeIF tipo=simbolo.getType();
			
			return tipo;
		}else {
			semanticErrorManager.semanticFatalError("�No es una funcion!");
			return null;
		}
	}
	/**
	 * M�todo para devolver el tipo de un identificador cualquiera en forma de TypeIF.
	 * @param simbolo: el identificador
	 * @return: TypeIF
	 */
	public static TypeIF extraerLosTipos(SymbolIF simbolo) {
		if (simbolo instanceof SymbolVariable) { 
			
			TypeIF tipo=simbolo.getType();
			
			return tipo;
		}else {
			semanticErrorManager.semanticFatalError("�El s�mbolo: "+simbolo.getName()+", no es v�lido para esta expresi�n!");
			return null;
		}
	}
	
	/**
	 * M�todo que guarda en el tipo lista cada uno de los tipos de los par�metros
	 * declarados en la funci�n o procedimiento. Despues recuperaremos esta lista
	 * y la comnpararemos con las lista de parametros de la llamada
	 * @param tipo
	 */
	public static void guardarTipoEnLista(String tipo) 
	{
		TypeIF tipoLista = scopeManager.searchType("Lista");
		TypeListParam tL=(TypeListParam)tipoLista;	
		tL.getListaTipo().add(tipo);
	}
	
	/**
	 * Agrega el tipo del parametro a la lista que se propagar� para compararla
	 * con el TypeListParam del ambito
	 * @param exp
	 * @param par
	 */
	public static void agregarParametro(Expresion exp,Parametros par) {
	
		par.getLista().add(exp.getTipoExpresion().getName().toUpperCase());
		//semanticErrorManager.semanticDebug("Se ha agregado el tipo: "+exp.getTipoExpresion().getName()+" a la lista");
		//mostrarElementosArray(par.getLista());
	}
	/**
	 * M�todo para comparar las listas de parametros del s�mbolo funci�n/procedimiento
	 * declarado, con el s�mbolo funci�n/procedimiento en la sentencia de llamada
	 * del cuerpo de sentencias.
	 * @param id: nombre del s�mbolo
	 * @param pF: objeto con la lista de par�metros que trae la llamada.
	 */
	public static void compararListasParametros(SymbolIF id,ParFuncion pF) {
		
		if(pF==null) {
			comprobarListaParametros(id,scopeManager.getCurrentScope());
		}else {
		//////////////////////Recuperar la lista de la sentencia de llamada////////
		ArrayList<String> listaLlamada=pF.getLista();
		Collections.reverse(listaLlamada);
		//mostrarElementosArray(listaLlamada);
		
		//////////////////////Recuperar lista de la declaraci�n/////////////////// 
		//Extraemos todos los �mbitos
		ArrayList<String> listaDeclaracion;
		List<ScopeIF> ambitos= new ArrayList<ScopeIF>();
		ambitos=scopeManager.getAllScopes();
		
		//Buscar el �mbito de la lista de �mbitos cuyo nombre es el id
		boolean encontrado=false;
		ScopeIF ambito = null;
		for(int c=0;c<ambitos.size();c++) {
			if(ambitos.get(c).getName().equals(id.getName())) {
				ambito=ambitos.get(c);
				encontrado=true;
			}
		}
		if (encontrado==false) semanticErrorManager.semanticFatalError("No se ha encontrado "
				+ "ambito de la funci�n. Imposible recuperar lista de par�metros.");
		
		//Recuperar la tabla de tipos del �mbito, que es el nombre de la funcion
		// y es donde est� la lista que buscamos.
		TypeTableIF tTipos=ambito.getTypeTable();
		TypeListParam tipoDeclaracion=(TypeListParam)tTipos.getType("Lista");
					//Aqu� haremos la comprobacion de si est� vac�a o no///
		listaDeclaracion=tipoDeclaracion.getListaTipo();
		
		////////////Ya tenemos las dos listas, ahora las comparamos///////////////
		
		if(listaDeclaracion.size()==listaLlamada.size()) {
			for(int contador=0;contador<listaDeclaracion.size();contador++) {
				if(!listaDeclaracion.get(contador).equals(listaLlamada.get(contador))) {
					semanticErrorManager.semanticFatalError("Error en el orden "
							+ "o tipo de par�metros en la llamada a la funci�n: "+id.getName());
				}	
			}
		}else {
			semanticErrorManager.semanticFatalError("Error en el n�mero de par�metros "
					+ "en la llamada a la funci�n: "+id.getName());
		}
		}
	}
	/**
	 * M�todo para comprobar si un simbolo es una funcion o procedimiento y si tiene 
	 * parametros declarados. Esta se utiliza en las producciones "variables" y 
	 * "sentProcedure".
	 * @param id: el s�mbolo a comprobar
	 * @param ambito: el �mbito en donde est� declarado el s�mbolo.
	 */
	public static void comprobarListaParametros(SymbolIF id,ScopeIF ambitoActual) {
		//Recuperar la tabla de tipos del �mbito, que es donde est� la lista que buscamos.
			
		//////////////////////Recuperar lista de la declaraci�n/////////////////// 
		//Extraemos todos los �mbitos
		//ArrayList<String> listaDeclaracion;
		List<ScopeIF> ambitos= new ArrayList<ScopeIF>();
		ambitos=scopeManager.getAllScopes();

		//Buscar el �mbito de la lista de �mbitos cuyo nombre es el id
		boolean encontrado=false;
		ScopeIF ambito = null;
		for(int c=0;c<ambitos.size();c++) {
			if(ambitos.get(c).getName().equals(id.getName())) {
				ambito=ambitos.get(c);
				encontrado=true;
			}
		}
		if (encontrado==false) semanticErrorManager.semanticFatalError("No se ha encontrado "
				+ "ambito de la funci�n. Imposible recuperar lista de par�metros.");

		//Recuperar la tabla de tipos del �mbito, que es el nombre de la funcion
		// y es donde est� la lista que buscamos.
		TypeTableIF tTipos=ambito.getTypeTable();
		TypeListParam tipoDeclaracion=(TypeListParam)tTipos.getType("Lista");
		if (!tipoDeclaracion.getListaTipo().isEmpty()) {
			//mostrarElementosArray(tipoDeclaracion.getListaTipo());
			semanticErrorManager.semanticFatalError("Faltan par�metros en la llamada a: "
					+id.getName());
			
		}
	}
	
	/**
	 * M�todo para comprobar la existencia y compatibilidad del return en una funcion
	 * @param sS: Lista de sentencias del cuerpo de la funci�n
	 * @param nombreF: nombre de la funci�n
	 */
	public static void comprobarReturn(Sentencias sS,String nombreF)
	{
		Sentencia sentenciaReturn=null;
		ScopeIF ambito=scopeManager.getCurrentScope();
		ScopeIF ambitoSuperior=scopeManager.getParentScope();
		if (ambitoSuperior!=null) { //Si estamos en una funcion o procedimiento, entramos
			SymbolTableIF tablaSimbolos=ambitoSuperior.getSymbolTable();
			SymbolIF funcion=tablaSimbolos.getSymbol(nombreF);
			//Comprobamos que el s�mbolo sea una funci�n
			if (funcion instanceof SymbolFunction) 
			{
				//Recorremos la lista de sentencias en busca del RETURN
				for (int conta=0;conta<sS.getlistaInstrucciones().size();conta++) {
					//Si encontramos el RETURN lo guardamos en su variable
					if (sS.getlistaInstrucciones().get(conta).getInstruccion().equals("RETURN")) {
						sentenciaReturn=sS.getlistaInstrucciones().get(conta);
					}
				}
				//Comprobamos que hemos encontrado el RETURN
				if(sentenciaReturn!=null) {
					//Comprobamos que el return devuelve el mismo tipo que la funcion
					if(funcion.getType().getName()!=sentenciaReturn.getTipo().getName()) 
					{
						semanticErrorManager.semanticFatalError("El tipo devuelto por el RETURN no coincide con el tipo de la funci�n: "+nombreF);
					}
				}
				else 
				{
					semanticErrorManager.semanticFatalError("No existe el RETURN en la lista de sentencias de la funci�n");
				}
			}
		}
	}
	
	/**
	 * M�todo para la gesti�n de memoria. 
	 * No voy a implementar lo relativo a subprogramas.
	 * @return
	 */
	public static int gestionDeMemoria() {
		//Direccion global. Vamos almacenando la direcci�n de cada 
		//variable, constante o array
		int gDireccion = 1;//Direcciones para variables, constantes y array
		int tDireccion = 30; //Direcciones para los temporales
		List<ScopeIF> scopes = scopeManager.getAllScopes ();
		//System.out.println("Los �mbitos son: "+scopes);
	 	for (ScopeIF scope: scopes) {
	 		//Recupero la tabla del simbolos del ambito
	 		List<SymbolIF> symbols = scope.getSymbolTable ().getSymbols();
	 		
	 		for (SymbolIF s: symbols) {
	 			if (s instanceof SymbolVariable) {
	 				SymbolVariable simboloV=(SymbolVariable)s;
	 				if (scope.getLevel () == 0) {
	 					simboloV.setAddress(gDireccion);
	 					gDireccion=gDireccion+simboloV.getType().getSize();
	 					/*System.out.println("La variable: "+simboloV.getName()+" se le asigna la direcci�n"
	 		 					+ ": "+simboloV.getAddress());*/
	 				}
	 				
	 			}else if(s instanceof SymbolArray) {//reserva de memoria para arrays
	 				SymbolArray simboloA=(SymbolArray)s;
	 				//tamanoArray tendr� el tama�o del array
	 				int tamanoArray=1+simboloA.getRangoFin()-simboloA.getRangoInicio();
	 				simboloA.setAddress(gDireccion);
	 				gDireccion=gDireccion+tamanoArray;
	 				/*semanticErrorManager.semanticInfo("La variable: "+simboloA.getName()+" se le asigna la direcci�n"
	 					+ ": "+simboloA.getAddress()+" con tama�o: "+tamanoArray);*/
	 			}
	 			//A partir de aqu� se calcular�a la direcci�n para 
				//variables de subprogramas
	 		}
	 		
	 		//Gesti�n de los temporales
	 		List<TemporalIF> temporals = scope.getTemporalTable().getTemporals();
	 		for (TemporalIF t: temporals) { 
	 			Temporal temporal=(Temporal)t;
	 			temporal.setAddress(tDireccion);
	 			tDireccion=tDireccion+temporal.getSize();
	 			/*System.out.println("El temporal: "+temporal.getName()+" tiene"
	 					+ ": "+temporal.getAddress());*/
	 		}
	 	}
	 	return gDireccion;
	}
	
	/**
	 * M�todo para extraer el temporal de la expresi�n de la asignaci�n.
	 * Comprobamos que en la lista de sentencias exista una sentencia
	 * de asignaci�n, y si existe, comprobamos tambi�n que la asignaci�n 
	 * se haga a la variable que controla el �ndice del bucle.
	 * @param s: Lista de sentencias
	 * @param simbolo: variable que controla el �ndice del FOR
	 * @return Devuielve el temporal de la expresi�n de asignaci�n
	 */
	public static TemporalIF extraeTemporalAsignacion(Sentencias s,SymbolIF simbolo) {
		TemporalIF temporal = null;
		for (int i=0;i<s.getlistaInstrucciones().size();i++) 
		{
			if(s.getlistaInstrucciones().get(i).getsA() instanceof SentAsign) 
			{
				if(s.getlistaInstrucciones().get(i).getsA().getVar().getIdentificador().getName().equals(simbolo.getName()))
				{
					temporal=s.getlistaInstrucciones().get(i).getsA().getExp().getTemporal();
					//System.out.println(temporal);
				}
			}
		}
		if(temporal==null) {
			semanticErrorManager.semanticInfo("Parece que no se dan las condiciones "
					+ "para la finalizaci�n del bucle.");
			};
		return temporal;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////M�TODOS PARA PRUEBAS////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/**
	 * metodo para mostrar los elementos del arrayList
	 */
	public static void mostrarElementosArray(ArrayList<String> lista) 
	{
		//System.out.println("Entro en mostrar elementos del array. Tama�o: "+lista.size());
		for (int x=0;x<lista.size();x++) {
			//System.out.println("Mostrando elemento: ");
			System.out.println(lista.get(x));
		}
	}
	public static void mostrarElementosArray(SentElse se) 
	{
		
		for (int x=0;x<se.getSentencias().getlistaInstrucciones().size();x++) {
			System.out.println(se.getSentencias().getlistaInstrucciones().get(x));
		}
	}
	public static void mostrarElementosArray(Sentencias se) 
	{
		System.out.println("Tama�o"+se.getlistaInstrucciones().size());
		for (int x=0;x<se.getlistaInstrucciones().size();x++) {
			System.out.println(se.getlistaInstrucciones().get(x));
		}
	}
	public static void comprobarObjeto(ListSentencia lS)
	{		
		if(lS.getListaSentencias()==null)
			System.out.println("No hay lista de parametros");
		else
			System.out.println("Lista de parametros tiene: "+lS.getListaSentencias());
	}
	
	public static void copiarLista(Parametros par) {
		ArrayList<String> lista;
		lista=par.getLista();
		System.out.println("Llamo desde copiarLista");
		mostrarElementosArray(lista);
	}
	
	public static void comprobarSentencias(Sentencias s) {
		if (!(s==null)) { 
			//System.out.println("Entro en mostrar sentencias. Tama�o: "+s.getlistaInstrucciones().size());
			for (int x=0;x<s.getlistaInstrucciones().size();x++) {
				System.out.println("Mostrando elemento: "+s.getlistaInstrucciones().get(x).getInstruccion());
			}
		}else {
			System.out.println("La lista de sentencias est� vac�a");
		}
	}
}