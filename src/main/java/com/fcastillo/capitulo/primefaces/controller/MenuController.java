/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.Menu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author fcastillo
 */
@Named(value = "menuController")
@ViewScoped
public class MenuController implements Serializable {

    private List<Menu> lstMenu = new ArrayList<>();

    public List<Menu> getLstMenu() {
        return lstMenu;
    }

    public void setLstMenu(List<Menu> lstMenu) {
        this.lstMenu = lstMenu;
    }

    @PostConstruct
    public void init() {
        iniciar();
    }

    private void iniciar() {
        lstMenu.add(new Menu(1, "Autocomplete", "autocomplete"));
        lstMenu.add(new Menu(1, "Autocomplete con insert", "autocompleteinsert"));
        lstMenu.add(new Menu(2, "DataTable Simple", "datatable"));
        lstMenu.add(new Menu(3, "DataTable con carga perezosa", "datatablelazyloading"));
        lstMenu.add(new Menu(4, "SelectOneMenu simple", "selectonemenu"));
        lstMenu.add(new Menu(5, "SelectOneMenu dependiente con ajax", "selectonemenudependiente"));
    }

}
