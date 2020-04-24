/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.Carreras;
import com.fcastillo.capitulo.primefaces.ejb.CarrerasFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fcastillo
 */
@Named(value = "carreraController")
@ViewScoped
public class CarreraController implements Serializable {

    @EJB
    CarrerasFacadeLocal carreraFacade;

    private LazyDataModel<Carreras> lstCarrerasLazyModel;
    private List<Carreras> lstCarreras;
    private Carreras carrera = new Carreras();

    public LazyDataModel<Carreras> getLstCarrerasLazyModel() {
        return lstCarrerasLazyModel;
    }

    public void setLstCarrerasLazyModel(LazyDataModel<Carreras> lstCarrerasLazyModel) {
        this.lstCarrerasLazyModel = lstCarrerasLazyModel;
    }

    public List<Carreras> getLstCarreras() {
        return lstCarreras;
    }

    public void setLstCarreras(List<Carreras> lstCarreras) {
        this.lstCarreras = lstCarreras;
    }

    public Carreras getCarrera() {
        return carrera;
    }

    public void setCarrera(Carreras carrera) {
        this.carrera = carrera;
    }

    @PostConstruct
    public void init() {
        iniciarLazyLoading();
    }

    private void iniciarLazyLoading() {
        lstCarrerasLazyModel = new LazyDataModel<Carreras>() {
            @Override
            public List<Carreras> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Carreras> lstCarreras = carreraFacade.findByParams(first, pageSize, sortField, sortOrder, filters);
                lstCarrerasLazyModel.setRowCount(carreraFacade.getFilteredRowCount(filters));
                return lstCarreras;
            }
        };
    }

    public List<Carreras> completeNombreCarrera(String query) {
        List<Carreras> sugerencias = new ArrayList<>();
        try {
            query = query.trim();
            lstCarreras = carreraFacade.findByNameLike(query.toLowerCase());
            lstCarreras.stream().forEach((p) -> {
                sugerencias.add(p);
            });
        } catch (Exception e) {
            System.out.println("completeNombreCarrera() " + e.getLocalizedMessage());
        }
        return sugerencias;
    }
}