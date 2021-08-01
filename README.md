# Compilador desarrollado en Java

Compilador desarrollado en Java donde se ha definido un lenguaje de programación. Se define e implementa: 

- El léxico del lenguaje.
- El sintático 
- El semántico
- Generación de Código Intermedio
- Generación de Código Final.

El código final generado pertenece al conjunto de instrucciones máquina del simulador ENS2001 https://ens2001.falvarez.es/

En el fichero scanner.flex se han definido los tokens del lenguaje.
En el fichero parser.cup se ha trabajado la sintaxis, la semántica y las llamadas a métodos de clase para que ayudan a las comprobaciones semánticas. Si la sintaxis y la semántica es correcta, tambine se hacen llamadas a métodos para la generación de código intermedio y código final.

Se crean dos clases de soporte en src/compiler/soporte/

Soporte.java --> Contendrán todos los métodos necesarios para las comprobaciones semánticas.
SoporteCI.java --> Métodos para generar el código intermedio.

Hay que tener en cuenta que el código intermedio se irá creando desde las hojas del árbol gramatical (los terminales), y se irá propagando este código a través de todas las ramas, hasta llegar todo este código intermedio al inicio del árbol, momento en el cual se hará la llamada a los métodos que convierten ese código intermedio en final (el que interpreta el procesador).

La dificultad sobretodo está en la propagación del código intermedio. El método usado es LALR.

En src/compiler/syntax/nonTerminal/ creamos las distintas clases y métodos para la gestión de los no-terminales

En src/compiler/code/ se crea:

TraductorCF --> traduce el código intermedio a código final para que pueda ser interpretado por el emulador ENS2001.

Se modifica el ExecutionEnvironmentEns2001.java para adaptarlo al traductorCF.

Para más información, consultar los documentos:

1- Instrucciones de implementación.pdf --> se dan las directrices e instrucciones para la construcción del compilador.

2- Descripción implementación.pdf --> resumen del trabajo realizado.
