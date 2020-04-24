/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces;

/**
 *
 * @author fcastillo
 */
public class Menu {

    private int id;
    private String titulo;
    private String ruta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Menu(int id, String titulo, String ruta) {
        this.id = id;
        this.titulo = titulo;
        this.ruta = ruta;
    }
    

}
