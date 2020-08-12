package compiler.syntax;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import es.uned.lsi.compiler.lexical.ScannerIF;
public class subparser extends parser{
	//Insertar aqui la extensión de los ficheros de tests
	public static String extension=".cuned";	
	public subparser() {}
	public subparser(ScannerIF s) {super(s);}
	public static String error="";
	public static String getError() {return error;}
	public static void executeTest(String fileName) {
		  try {
			  FileReader aFileReader = new FileReader (fileName);
			  Class scannerClass = Class.forName ("compiler.lexical.Scanner"); 
			  Constructor scannerConstructor = scannerClass.getConstructor(Reader.class);
			  ScannerIF aScanner = (ScannerIF) scannerConstructor.newInstance(aFileReader);
			  subparser s=new subparser(aScanner);
			  try {
			  s.parse();}
			  catch(Exception npe) {
				  error="#ERROR: "+npe.toString();
			  }		  
		  }catch(Exception e) {}
	}	 
}