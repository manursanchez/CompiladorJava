# Compilador desarrollado en Java

Compilador desarrollado en Java donde se ha definido un lenguaje de programación. Se define e implementa: 
- El léxico del lenguaje.
- El sintático 
- El semántico
- Generación de Código Intermedio
- Generación de Código Final.

El código final generado pertenece al conjunto de instrucciones máquina del simulador ENS2001 https://ens2001.falvarez.es/

Se trabaja sobre el parser.cup.

Se crean dos clases en src/compiler/soporte/

Soporte.java --> Contendrán todos los métodos necesarios para las comprobaciones semánticas.
SoporteCI.java --> Métodos para generar el código intermedio.

En src/compiler/syntax/nonTerminal/ creamos las distintas clases y métodos para la gestión de los no-terminales

En src/compiler/code/ se crea:

TraductorCF --> traduce el código intermedio a código final para que pueda ser interpretado por el emulador ENS2001.

Se modifica el ExecutionEnvironmentEns2001.java para adaptarlo al traductorCF.
