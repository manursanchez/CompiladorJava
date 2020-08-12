package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolArray extends SymbolBase {
	/**
     * Constructor para Símbolo matriz creado por mi
     * @param scope
     * @param name
     * @param type
     * @param rangoI
     * @param rangoF
     * @param tipoP: el tipo primitivo Boolean o Integer del array
     */
	private int rangoInicio,rangoFin;
	private String tipoPrimitivo;
	private int address;
	
	public SymbolArray(ScopeIF scope, String name, TypeIF type,int rangoI,int rangoF,String tipoP) {
		super(scope, name, type);
		this.rangoInicio=rangoI;
    	this.rangoFin=rangoF;
    	this.tipoPrimitivo=tipoP;
		// TODO Auto-generated constructor stub
	}
	public int getRangoInicio() {
		return rangoInicio;
	}

	public void setRangoInicio(int rangoInicio) {
		this.rangoInicio = rangoInicio;
	}

	public int getRangoFin() {
		return rangoFin;
	}
	public void setRangoFin(int rangoFin) {
		this.rangoFin = rangoFin;
	}
	public String getTipoPrimitivo() {
		return tipoPrimitivo;
	}
	public void setTipoPrimitivo(String tipoPrimitivo) {
		this.tipoPrimitivo = tipoPrimitivo;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	
}
