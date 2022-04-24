package intface;
import classes.Tokens;
import static classes.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%
entero {lexeme=yytext(); return Entero;}
si {lexeme=yytext(); return Si;}
sino {lexeme=yytext(); return Si_No;}
booleano {lexeme=yytext(); return Booleano;}
cadena {lexeme=yytext(); return Cadena;}
ciclo {lexeme=yytext(); return Ciclo;}
mientras {lexeme=yytext(); return Ciclo_Mientras;}
func {lexeme=yytext(); return Func;}
clase {lexeme=yytext(); return Clase;}
encender {lexeme=yytext(); return Encender;}
apagar {lexeme=yytext(); return Apagar;}
avanzar {lexeme=yytext(); return Avanzar;}
detener {lexeme=yytext(); return Detener;}
start {lexeme=yytext(); return Start;}
retroceder {lexeme=yytext(); return Retroceder;}
recorrido {lexeme=yytext(); return Recorrido;}
caso {lexeme=yytext(); return Caso;}
entrada {lexeme=yytext(); return Entrada;}
rotarR {lexeme=yytext(); return RotarR;}
rotarL {lexeme=yytext(); return RotarL;}
Sensor {lexeme=yytext(); return Sensor;}
espera {lexeme=yytext(); return Espera;}
notificar {lexeme=yytext(); return Notificar;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"\n" {lexeme=yytext(); return Linea;}
"=" {lexeme=yytext(); return Igual;}
"(" {lexeme=yytext(); return Parentesis_a;}
")" {lexeme=yytext(); return Parentesis_c;}
"{" {lexeme=yytext(); return Llave_a;}
"}" {lexeme=yytext(); return Llave_c;}
";" {lexeme=yytext(); return P_coma;}
"." {lexeme=yytext(); return Punto;}
"clase" {lexeme=yytext(); return Clase;}
"++" {lexeme=yytext(); return Op_incremento;}
"--" {lexeme=yytext(); return Op_incremento;}
"+=" {lexeme=yytext(); return Op_asignacion;}
"-=" {lexeme=yytext(); return Op_asignacion;}
"*=" {lexeme=yytext(); return Op_asignacion;}
"/=" {lexeme=yytext(); return Op_asignacion;}
"%=" {lexeme=yytext(); return Op_asignacion;}
"&&" {lexeme=yytext(); return Op_booleano;}
"||" {lexeme=yytext(); return Op_booleano;}

/*Operadores Booleanos*/
( verdadero | falso ) {lexeme=yytext(); return Op_booleano;}

{L}({L}|{D})* {lexeme=yytext(); return Identificador;}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" ) {lexeme=yytext(); return Op_relacional;}

/* Numero*/
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}

/* Numero Decimal*/
//("(-"{D}+"."{D}+")")|({D}+"."{D}+) {lexeme=yytext(); return NumeroD;}

/* Texto */
("\""({L}|{D}|{espacio}|"\n")*"\"") {lexeme=yytext(); return Texto;}
 . {lexeme=yytext(); return ERROR;}