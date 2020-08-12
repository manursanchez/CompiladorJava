package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

//Aquí vamos a construir un objeto por cada tipo de sentencia que nos encontremos

public class Sentencia extends NonTerminal {
	private String instruccion;
	private TypeIF tipo;
	private String cadena;
	private Expresion expresion;
	private SentIf sIF;
	private SentAsign sA;
	private SentFor sFor;
	private SentProcedure sP;
	/**
	 * Constructor para:
	 * ASIGNACION
	 * @param instruccion: nombre de la instruccion.
	 * @param sA: contenido de la instruccion.
	 */
	public Sentencia(String instruccion, SentAsign sA) {
		super();
		this.instruccion=instruccion;
		this.sA=sA;
	}
	
	/**
	 * Constructor para: IF
	 * @param instruccion: nombre de la instruccion
	 * @param sIF: contenido de la instruccion
	 */
	public Sentencia(String instruccion, SentIf sIF) {
		super();
		this.instruccion=instruccion;
		this.sIF=sIF;
	}
	/**
	 * Constructor para: FOR
	 * @param instruccion
	 * @param sFor
	 */
	public Sentencia(String instruccion, SentFor sFor) {
		super();
		this.instruccion = instruccion;
		this.sFor = sFor;
	}
	/**
	 * Constructor para: PROCEDIMIENTO
	 * @param instruccion
	 * @param sP
	 */
	public Sentencia(String instruccion, SentProcedure sP) {
		super();
		this.instruccion = instruccion;
		this.sP = sP;
	}

	/**
	 * Constructor para: WRITESTRING
	 * @param instruccion: nombre de la instruccion
	 * @param cadena: cadena de caracteres para mostrar en pantalla
	 */
	public Sentencia(String instruccion,String cadena) {
		// TODO Auto-generated constructor stub
		super();
		this.cadena=cadena;
		this.instruccion=instruccion;
	}
	/**
	 * Constructor para instruccion: WRITEINT
	 * @param instruccion
	 * @param expresion: expresion de tipo entero.
	 */
	public Sentencia(String instruccion, Expresion expresion) {
		super();
		this.instruccion = instruccion;
		this.expresion = expresion;
	}
	/**
	 * Constructor para instrucción: WRITELN
	 * @param instruccion
	 */
	public Sentencia(String instruccion) {
		super();
		this.instruccion = instruccion;
	}
	/** 
	 * Constructor para: RETURN
	 * @param instruccion
	 * @param tipo
	 */
	public Sentencia(String instruccion, TypeIF tipo) {
		super();
		this.instruccion = instruccion;
		this.tipo = tipo;
	}
	
	/******************************************************
	 *Métodos para extración de variables privadas 
	 */
	public String getInstruccion() {
		return instruccion;
	}
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}
	public TypeIF getTipo() {
		return tipo;
	}
	public void setTipo(TypeIF tipo) {
		this.tipo = tipo;
	}
	//Para extraer la cadena de WriteString
	public String getCadena() {
		return cadena;
	}
	//Para extraer la expresion de tipo entero de WRITEINT
	public Expresion getExpresion() {
		return expresion;
	}
	
	//Para extraer informacion del IF
	public SentIf getsIF() {
		return sIF;
	}
	//Para extraer informacion de la sentencia asignacion (expresion y variable)
	public SentAsign getsA() {
		return sA;
	}
	//Para extraer informacion del FOR
	public SentFor getsFor() {
		return sFor;
	}
	
	//Para extraer los elementos de un procedimiento
	public SentProcedure getsP() {
		return sP;
	}
	
	/**
	 * **********************************************
	 */

}
