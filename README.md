# ProcesadoresLenguajes_II
Segunda parte del desarrollo del compilador. En esta parte se desarrolla el semántico, código intermedio y código final.

Se trabaja sobre el parser.cup.

Se crean dos clases en src/compiler/soporte/

Soporte.java --> Contendrán todos los métodos necesarios para las comprobaciones semánticas.
SoporteCI.java --> Métodos para generar el código intermedio.

En src/compiler/syntax/nonTerminal/ creamos las distintas clases y métodos para la gestión de los no-terminales

En src/compiler/code/ se crea:

TraductorCF --> traduce el código intermedio a código final para que pueda ser interpretado por el emulador ENS2001.

Se modifica el ExecutionEnvironmentEns2001.java para adaptarlo al traductorCF.
