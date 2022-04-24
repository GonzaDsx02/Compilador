#include <Arduino.h>
#line 1 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
int i01_1a = 9; //amarillo arriba - IZQUIERDA A
int i01_0b = 8; //verde abajo - IZQUIERDA B
int d10_1a = 7; //verde arriba - DERECHA A
int d10_0b = 6; //amarillo abajo - DERECHA B
int v11_1x = 255; // Velocidad de los motores
boolean b001_1 = false; //bandera para evitar ciclos infinitos en funcion loop()

int p_e_0x1 = 11;            // define el pin 2 como (pecho) para el Ultrasonido
int p_t_0x0 = 12;    //pin trig
volatile int d_1_1x0 = 0; //duracion
volatile int d_0_1x1 = 0; //distancia
int s_1x1_p_1x0 = 2; //sensor proximidad (ultrasonico)
volatile long cx_01_x1 = 0; //reg contador

int c_11x1_0[5]={131,262,523,1046,2093};
int g_11x0_0[5]={196,392,784,1568,3136};
int d_11x1_0[5]={147,294,587,1175,2349};
int f_11x1_0[5]={175,349,698,1379,2794};
int a_11x1_0[5]={220,440,880,1760,3520};
int sound_xx11_0011 = 5;

#line 22 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void setup();
#line 33 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void loop();
#line 41 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void avanzar(long t_1x0_0);
#line 79 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void detener();
#line 86 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void rotarL();
#line 102 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void rotarR();
#line 118 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void retroceder(long t_1x0_0);
#line 133 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void no_11ta_0x1(int f_11xx_0010, int t_x10_1xx0);
#line 137 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void notificar();
#line 145 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void encender();
#line 146 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void apagar();
#line 22 "C:\\Users\\DELL\\Desktop\\Objeto\\Sketch\\Objeto\\Objeto.ino"
void setup(){  
  //pinMode(13,OUTPUT); //led que prende cuando hay obstaculos
  pinMode(p_e_0x1, INPUT);   // define el pin 2 como entrada (pecho) 
  pinMode(p_t_0x0,OUTPUT); 
  pinMode(i01_1a,OUTPUT); 
  pinMode(i01_0b,OUTPUT); 
  pinMode(d10_1a,OUTPUT); 
  pinMode(d10_0b,OUTPUT); 
  pinMode(s_1x1_p_1x0, INPUT);  
}

void loop(){
	int vueltas = 0;
	while(vueltas<=10){
	notificar();
	vueltas++;
	}
  exit(0); //finaliza el ciclo loop()
}
void avanzar(long t_1x0_0){    
  cx_01_x1 = t_1x0_0*10;
  
  while(cx_01_x1 >= 0){
    digitalWrite(p_t_0x0, HIGH);   // genera el pulso de trigger por 10us
    delayMicroseconds(10);
    digitalWrite(p_t_0x0, LOW);
        
    d_1_1x0 = pulseIn(p_e_0x1, HIGH);          // Lee el tiempo del Echo
    d_0_1x1 = (d_1_1x0/2) / 29;    
    
    analogWrite(d10_0b, 0);     
    analogWrite(i01_0b, 0); 
    analogWrite(d10_1a, v11_1x);  
    analogWrite(i01_1a, v11_1x);    
    while(d_0_1x1 <= 10){
      digitalWrite(13,HIGH);
      detener();
      no_11ta_0x1(c_11x1_0[4],100);
      no_11ta_0x1(g_11x0_0[4],100);
      noTone(sound_xx11_0011);
      digitalWrite(p_t_0x0, HIGH);
   // genera el pulso de trigger por 10us
      delayMicroseconds(10);
      digitalWrite(p_t_0x0, LOW);
      
      d_1_1x0 = pulseIn(p_e_0x1, HIGH);          // Lee el tiempo del Echo
      d_0_1x1 = (d_1_1x0/2) / 29; 
      delay(10);
    }
    cx_01_x1--;
    delay(10);
  }
  cx_01_x1 = 0;
  detener();
  return;
}

void detener(){
  analogWrite(d10_0b, 0);     
  analogWrite(i01_0b, 0); 
  analogWrite(d10_1a, 0);    
  analogWrite(i01_1a, 0); 
}

void rotarL(){
  cx_01_x1 = 110;
  
  while(cx_01_x1 >= 0){ 
    analogWrite(d10_0b, 0);            // Girar durante 600 milisegundos   
    analogWrite(i01_0b, 0); 
    analogWrite(d10_1a, 0);  
    analogWrite(i01_1a, v11_1x);
    cx_01_x1--;
    delay(10);
  }
  cx_01_x1 = 0;
  detener();
  return;
}

void rotarR(){
  cx_01_x1 = 110;
  
  while(cx_01_x1 >= 0){ 
    analogWrite(d10_0b, 0);            // Girar durante 600 milisegundos   
    analogWrite(i01_0b, 0); 
    analogWrite(d10_1a, v11_1x);  
    analogWrite(i01_1a, 0);
    cx_01_x1--;
    delay(10);
  }
  cx_01_x1 = 0;
  detener();
  return;
}

void retroceder(long t_1x0_0){    
  cx_01_x1 = t_1x0_0*80;
  
  while(cx_01_x1 >= 0){ 
    analogWrite(d10_1a, 0);     
    analogWrite(i01_1a, 0); 
    analogWrite(d10_0b, v11_1x);  
    analogWrite(i01_0b, v11_1x);    
    cx_01_x1--;
    delay(10);
  }
  cx_01_x1 = 0;
  detener();
  return;
}
void no_11ta_0x1(int f_11xx_0010, int t_x10_1xx0){
  tone(sound_xx11_0011, f_11xx_0010);
  delay(t_x10_1xx0);
}
void notificar(){
  no_11ta_0x1(d_11x1_0[4],100);
  no_11ta_0x1(d_11x1_0[4],100);
  no_11ta_0x1(f_11x1_0[4],100);
  no_11ta_0x1(g_11x0_0[4],100);
  no_11ta_0x1(a_11x1_0[4],100);
  noTone(sound_xx11_0011);
}
void encender(){}
void apagar(){}

