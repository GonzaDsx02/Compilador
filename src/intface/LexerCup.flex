package intface;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//" ) {/*Ignore*/}

/* Tipos de datos */
( entero | booleano ) {return new Symbol(sym.T_dato, yychar, yyline, yytext());}

/* Tipo de dato String */
( cadena ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}

/* Palabra reservada If */
( si ) {return new Symbol(sym.Si, yychar, yyline, yytext());}

/* Palabra reservada Else */
( sino ) {return new Symbol(sym.Si_No, yychar, yyline, yytext());}

/* Palabra reservada Caso */
( caso ) {return new Symbol(sym.Caso, yychar, yyline, yytext());}

/* Palabra reservada While */
( mientras ) {return new Symbol(sym.Mientras, yychar, yyline, yytext());}

/* Palabra reservada For */
( ciclo ) {return new Symbol(sym.Ciclo, yychar, yyline, yytext());}

/* Palabra reservada Recorrido */
( recorrido ) {return new Symbol(sym.Recorrido, yychar, yyline, yytext());}

/* Palabra reservada Func */
( func ) {return new Symbol(sym.Func, yychar, yyline, yytext());}

/* Palabra reservada Encender */
( encender ) {return new Symbol(sym.Encender, yychar, yyline, yytext());}

/* Palabra reservada Apagar */
( apagar ) {return new Symbol(sym.Apagar, yychar, yyline, yytext());}

/* Palabra reservada Avanzar */
( avanzar ) {return new Symbol(sym.Avanzar, yychar, yyline, yytext());}

/* Palabra reservada Detener */
( detener ) {return new Symbol(sym.Detener, yychar, yyline, yytext());}

/* Palabra reservada Retroceder */
( retroceder ) {return new Symbol(sym.Retroceder, yychar, yyline, yytext());}

/* Palabra reservada Recorrido */
( recorrido ) {return new Symbol(sym.Recorrido, yychar, yyline, yytext());}

/* Palabra reservada RotarR */
( rotarR ) {return new Symbol(sym.RotarR, yychar, yyline, yytext());}

/* Palabra reservada RotarL */
( rotarL ) {return new Symbol(sym.RotarL, yychar, yyline, yytext());}

/* Palabra reservada Start */
( start ) {return new Symbol(sym.Start, yychar, yyline, yytext());}

/* Palabra reservada Sensor */
( Sensor ) {return new Symbol(sym.Sensor, yychar, yyline, yytext());}

/* Palabra reservada Espera */
( espera ) {return new Symbol(sym.Espera, yychar, yyline, yytext());}

/* Palabra reservada Notificar */
( notificar ) {return new Symbol(sym.Notificar, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Operadores logicos */
( "&&" | "||" ) {return new Symbol(sym.Op_logico, yychar, yyline, yytext());}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" ) {return new Symbol(sym.Op_relacional, yychar, yyline, yytext());}

/* Operadores Asignacion */
( "+=" | "-="  | "*=" | "/=" | "%=" ) {return new Symbol(sym.Op_asignacion, yychar, yyline, yytext());}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return new Symbol(sym.Op_incremento, yychar, yyline, yytext());}

/*Operadores Booleanos*/
( verdadero | falso ) {return new Symbol(sym.Op_booleano, yychar, yyline, yytext());}

/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "clase" ) {return new Symbol(sym.Clase, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Punto*/
( "." ) {return new Symbol(sym.Punto, yychar, yyline, yytext());}

/* Identificador */
({L}({L}|{D})*) {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
(("(-"{D}+")")|{D}+) {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Texto */
({espacio}*"\""({L}|{D}|{espacio})*"\"") {return new Symbol(sym.Texto, yychar, yyline, yytext());}

/* Texto sin comillas de apertura */
(({L}|{D}|{espacio})*"\"") {return new Symbol(sym.TextoSA, yychar, yyline, yytext());}

/* Texto sin comillas de cierre */
("\""({L}|{D}|{espacio})*) {return new Symbol(sym.TextoSC, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}

//--------------------------------------------------------------------------------


/*Numero Decimal*/
//("(-"{D}+"."{D}+")")|({D}+"."{D}+) {return new Symbol(sym.NumeroD, yychar, yyline, yytext());}

/* Corchete de apertura */
//( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}

/* Corchete de cierre */
//( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}

/* Operador Suma */
//( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}

/* Operador Resta */
//( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}

/* Operador Multiplicacion */
//( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}

/* Operador Division */
//( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}