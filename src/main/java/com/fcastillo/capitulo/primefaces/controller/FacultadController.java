package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.Carreras;
import com.fcastillo.capitulo.primefaces.Facultades;
import com.fcastillo.capitulo.primefaces.ejb.CarrerasFacadeLocal;
import com.fcastillo.capitulo.primefaces.ejb.FacultadesFacadeLocal;
import com.fcastillo.capitulo.primefaces.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author fcastillo
 */
@Named(value = "facultadController")
@ViewScoped
public class FacultadController implements Serializable {

    @EJB
    FacultadesFacadeLocal facultadFacade;
    @EJB
    CarrerasFacadeLocal carreraFacade;

    private List<Facultades> lstFacultades;
    private List<Carreras> lstCarreras;
    private LazyDataModel<Carreras> lstCarrerasLazyModel;
    private Facultades facultad = new Facultades();
    private Carreras carrera = new Carreras();
    private int idfacultad;

    public List<Facultades> getLstFacultades() {
        return lstFacultades;
    }

    public void setLstFacultades(List<Facultades> lstFacultades) {
        this.lstFacultades = lstFacultades;
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
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

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public LazyDataModel<Carreras> getLstCarrerasLazyModel() {
        return lstCarrerasLazyModel;
    }

    public void setLstCarrerasLazyModel(LazyDataModel<Carreras> lstCarrerasLazyModel) {
        this.lstCarrerasLazyModel = lstCarrerasLazyModel;
    }

    @PostConstruct
    public void init() {
        lstFacultades = facultadFacade.findAll();
    }

    /**
     * Metodo que permite listar todas las carreras de una determinada facultad.
     */
    public void handleFacultad() {
        lstCarreras = carreraFacade.findByFacultad(facultad.getIdfacultad());
    }

    public List<Facultades> completeWithInsert(String query) {
        List<Facultades> sugerencias = new ArrayList<>();
        try {
            sugerencias = facultadFacade.findByNameLike(query);
            if (sugerencias == null || sugerencias.isEmpty()) {
                if (Utilidades.espaciosFinales(query) >= 2) {
                    query = query.trim();
                    Facultades facultad = new Facultades();
                    facultad.setNombre(query);
                    facultadFacade.create(facultad);
                    System.out.println("Facultad insertada...");
                }
                sugerencias = facultadFacade.findByNameLike(query);
            }
        } catch (Exception e) {
            System.out.println("completeWithInsert() " + e.getLocalizedMessage());
        }
        return sugerencias;
    }
    
}
