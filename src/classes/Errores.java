/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Gonzalo
 */
public class Errores {
    public String Descripcion;
    public int linea;
    public int columna;

    public Errores(String Descripcion){
        this.Descripcion = Descripcion;
    }
    
    public Errores(String Descripcion, int linea,int columna) {
        this.Descripcion = Descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public Errores() {
        this.Descripcion = "";
        this.linea = 0;
        this.columna = 0;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
