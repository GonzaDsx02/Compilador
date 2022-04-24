/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.*;

/**
 *
 * @author Gonzalo
 */
public class GeneradorIno {

    final String codigoInicial = "int i01_1a = 9; //amarillo arriba - IZQUIERDA A\n"
            + "int i01_0b = 8; //verde abajo - IZQUIERDA B\n"
            + "int d10_1a = 7; //verde arriba - DERECHA A\n"
            + "int d10_0b = 6; //amarillo abajo - DERECHA B\n"
            + "int v11_1x = 255; // Velocidad de los motores\n"
            + "boolean b001_1 = false; //bandera para evitar ciclos infinitos en funcion loop()\n"
            + "\n"
            + "int p_e_0x1 = 11;            // define el pin 2 como (pecho) para el Ultrasonido\n"
            + "int p_t_0x0 = 12;    //pin trig\n"
            + "volatile int d_1_1x0 = 0; //duracion\n"
            + "volatile int d_0_1x1 = 0; //distancia\n"
            + "int s_1x1_p_1x0 = 2; //sensor proximidad (ultrasonico)\n"
            + "volatile long cx_01_x1 = 0; //reg contador\n"
            + "\n"
            + "int c_11x1_0[5]={131,262,523,1046,2093};\n"
            + "int g_11x0_0[5]={196,392,784,1568,3136};\n"
            + "int d_11x1_0[5]={147,294,587,1175,2349};\n"
            + "int f_11x1_0[5]={175,349,698,1379,2794};\n"
            + "int a_11x1_0[5]={220,440,880,1760,3520};\n"
            + "int sound_xx11_0011 = 5;\n"
            + "\n"
            + "void setup(){  \n"
            + "  //pinMode(13,OUTPUT); //led que prende cuando hay obstaculos\n"
            + "  pinMode(p_e_0x1, INPUT);   // define el pin 2 como entrada (pecho) \n"
            + "  pinMode(p_t_0x0,OUTPUT); \n"
            + "  pinMode(i01_1a,OUTPUT); \n"
            + "  pinMode(i01_0b,OUTPUT); \n"
            + "  pinMode(d10_1a,OUTPUT); \n"
            + "  pinMode(d10_0b,OUTPUT); \n"
            + "  pinMode(s_1x1_p_1x0, INPUT);  \n"
            + "}\n"
            + "\n"
            + "void loop(){\n";

    final String codigoFinal = "void avanzar(long t_1x0_0){    \n"
            + "  cx_01_x1 = t_1x0_0*10;\n"
            + "  \n"
            + "  while(cx_01_x1 >= 0){\n"
            + "    digitalWrite(p_t_0x0, HIGH);   // genera el pulso de trigger por 10us\n"
            + "    delayMicroseconds(10);\n"
            + "    digitalWrite(p_t_0x0, LOW);\n"
            + "        \n"
            + "    d_1_1x0 = pulseIn(p_e_0x1, HIGH);          // Lee el tiempo del Echo\n"
            + "    d_0_1x1 = (d_1_1x0/2) / 29;    \n"
            + "    \n"
            + "    analogWrite(d10_0b, 0);     \n"
            + "    analogWrite(i01_0b, 0); \n"
            + "    analogWrite(d10_1a, v11_1x);  \n"
            + "    analogWrite(i01_1a, v11_1x);    \n"
            + "    while(d_0_1x1 <= 10){\n"
            + "      digitalWrite(13,HIGH);\n"
            + "      detener();\n"
            + "      no_11ta_0x1(c_11x1_0[4],100);\n"
            + "      no_11ta_0x1(g_11x0_0[4],100);\n"
            + "      noTone(sound_xx11_0011);\n"
            + "      digitalWrite(p_t_0x0, HIGH);\n   // genera el pulso de trigger por 10us\n"
            + "      delayMicroseconds(10);\n"
            + "      digitalWrite(p_t_0x0, LOW);\n"
            + "      \n"
            + "      d_1_1x0 = pulseIn(p_e_0x1, HIGH);          // Lee el tiempo del Echo\n"
            + "      d_0_1x1 = (d_1_1x0/2) / 29; \n"
            + "      delay(10);\n"
            + "    }\n"
            + "    cx_01_x1--;\n"
            + "    delay(10);\n"
            + "  }\n"
            + "  cx_01_x1 = 0;\n"
            + "  detener();\n"
            + "  return;\n"
            + "}\n"
            + "\n"
            + "void detener(){\n"
            + "  analogWrite(d10_0b, 0);     \n"
            + "  analogWrite(i01_0b, 0); \n"
            + "  analogWrite(d10_1a, 0);    \n"
            + "  analogWrite(i01_1a, 0); \n"
            + "}\n"
            + "\n"
            + "void rotarL(){\n"
            + "  cx_01_x1 = 110;\n"
            + "  \n"
            + "  while(cx_01_x1 >= 0){ \n"
            + "    analogWrite(d10_0b, 0);            // Girar durante 600 milisegundos   \n"
            + "    analogWrite(i01_0b, 0); \n"
            + "    analogWrite(d10_1a, 0);  \n"
            + "    analogWrite(i01_1a, v11_1x);\n"
            + "    cx_01_x1--;\n"
            + "    delay(10);\n"
            + "  }\n"
            + "  cx_01_x1 = 0;\n"
            + "  detener();\n"
            + "  return;\n"
            + "}\n"
            + "\n"
            + "void rotarR(){\n"
            + "  cx_01_x1 = 110;\n"
            + "  \n"
            + "  while(cx_01_x1 >= 0){ \n"
            + "    analogWrite(d10_0b, 0);            // Girar durante 600 milisegundos   \n"
            + "    analogWrite(i01_0b, 0); \n"
            + "    analogWrite(d10_1a, v11_1x);  \n"
            + "    analogWrite(i01_1a, 0);\n"
            + "    cx_01_x1--;\n"
            + "    delay(10);\n"
            + "  }\n"
            + "  cx_01_x1 = 0;\n"
            + "  detener();\n"
            + "  return;\n"
            + "}\n"
            + "\n"
            + "void retroceder(long t_1x0_0){    \n"
            + "  cx_01_x1 = t_1x0_0*80;\n"
            + "  \n"
            + "  while(cx_01_x1 >= 0){ \n"
            + "    analogWrite(d10_1a, 0);     \n"
            + "    analogWrite(i01_1a, 0); \n"
            + "    analogWrite(d10_0b, v11_1x);  \n"
            + "    analogWrite(i01_0b, v11_1x);    \n"
            + "    cx_01_x1--;\n"
            + "    delay(10);\n"
            + "  }\n"
            + "  cx_01_x1 = 0;\n"
            + "  detener();\n"
            + "  return;\n"
            + "}\n"
            + "void no_11ta_0x1(int f_11xx_0010, int t_x10_1xx0){\n"
            + "  tone(sound_xx11_0011, f_11xx_0010);\n"
            + "  delay(t_x10_1xx0);\n"
            + "}\n"
            + "void notificar(){\n"
            + "  no_11ta_0x1(d_11x1_0[4],100);\n"
            + "  no_11ta_0x1(d_11x1_0[4],100);\n"
            + "  no_11ta_0x1(f_11x1_0[4],100);\n"
            + "  no_11ta_0x1(g_11x0_0[4],100);\n"
            + "  no_11ta_0x1(a_11x1_0[4],100);\n"
            + "  noTone(sound_xx11_0011);\n"
            + "}\n"
            + "void encender(){}\n"
            + "void apagar(){}";

    final String ecuhhCode;

    public GeneradorIno(String ecuhhCode) {
        this.ecuhhCode = ecuhhCode;
    }

    public GeneradorIno(){
        ecuhhCode = "";
    }
    
    public void generarArchivo() {
        String codigoCompleto = codigoInicial
                + ecuhhCode
                + codigoFinal;
        escribirArchivo(codigoCompleto);
    }        

    private String getRoute() {
        File doc = new File(System.getProperty("user.dir") + "/HEX/ruta.dll");
        String route = "";
        try {
            Scanner obj;
            obj = new Scanner(doc);
            while (obj.hasNextLine()) {
                route += obj.nextLine();
            }
            if (!route.equals("")) {
                String[] cad = route.split(" ");
                if (!cad[0].equals("1")) {

                    //verificamops que la ruta sea correcta
                    String newRoute;
                    do {
                        newRoute = showInputDialog("Ingresa la ruta para el codigo objeto");
                    } while (!writeNewRoute(newRoute));
                    return newRoute;
                } else {
                    return cad[1];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorIno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private String updateDebugInfo(String updatedRoute) {
        File doc = new File(System.getProperty("user.dir") + "/HEX/debug.bat");
        String info = "";
        Scanner obj;
        try {
            obj = new Scanner(doc);
            while (obj.hasNextLine()) {
                info += obj.nextLine()+"\n";
            }
            String[] infoSplit = info.split("\n");
            String[] thirdLineContents = infoSplit[2].split(" ");
            String newInfo = infoSplit[0] + "\n" + infoSplit[1] + "\n" + thirdLineContents[0] + " " + thirdLineContents[1] + " " + updatedRoute + "\\Objeto.ino";            
            return newInfo;
        } catch (FileNotFoundException ex) {
            showMessageDialog(null, "Error al escribir el archivo debug.bat");
        }
        return "";
    }

    public boolean writeNewRoute(String ruta) {
        FileWriter fichero = null;        
        FileWriter fichero2 = null;        
        PrintWriter pw = null;        
        PrintWriter pw2 = null;        
        try {
            //Primero escribimos la nueva ruta en el archivo ruta.txt
            fichero = new FileWriter(System.getProperty("user.dir") + "/HEX/ruta.dll");
            pw = new PrintWriter(fichero);
            pw.println("1 " + ruta);                            
        } catch (Exception e) {
            showMessageDialog(null, "No se encontró la ruta especificada para el archivo ruta.dll");
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }               
        try {
            //escribimos la ruta en el archivo debug.bat
            String debugNewInfo = updateDebugInfo(ruta);            
            fichero2 = new FileWriter(System.getProperty("user.dir") + "/HEX/debug.bat");
            pw2 = new PrintWriter(fichero2);
            pw2.print(debugNewInfo);            
            return true;
        } catch (IOException ex) {
            showMessageDialog(null, "No se encontró la ruta especificada para el archivo debug.bat");
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero2) { //IMPORTANTISIMO VERIFICAR QUE SEA EL FICHERO EN USO; DE LO CONTRARIO, NO SE ESCRIBIRA NINGUN DATO
                    fichero2.close();                    
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }        

    private void escribirArchivo(String code) {

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            //establecemos la ruta donde se generará el archivo
            fichero = new FileWriter(getRoute() + "/Objeto.ino");
            pw = new PrintWriter(fichero);
            pw.println(code);
        } catch (Exception e) {
            showMessageDialog(null, "No se encontró la ruta especificada");

        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
