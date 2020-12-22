package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.Carrera;
import com.fcastillo.capitulo.primefaces.Facultad;
import com.fcastillo.capitulo.primefaces.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.fcastillo.capitulo.primefaces.ejb.CarreraFacadeLocal;
import com.fcastillo.capitulo.primefaces.ejb.FacultadFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named(value = "facultadController")
@ViewScoped
public class FacultadController implements Serializable {

    @EJB
    FacultadFacadeLocal facultadFacade;
    @EJB
    CarreraFacadeLocal carreraFacade;

    private List<Facultad> lstFacultades;
    private List<Carrera> lstCarreras;
    private LazyDataModel<Carrera> lstCarrerasLazyModel;
    private Facultad facultad = new Facultad();
    private Carrera carrera = new Carrera();
    private int idfacultad;

    public List<Facultad> getLstFacultades() {
        return lstFacultades;
    }

    public void setLstFacultades(List<Facultad> lstFacultades) {
        this.lstFacultades = lstFacultades;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Carrera> getLstCarreras() {
        return lstCarreras;
    }

    public void setLstCarreras(List<Carrera> lstCarreras) {
        this.lstCarreras = lstCarreras;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public LazyDataModel<Carrera> getLstCarrerasLazyModel() {
        return lstCarrerasLazyModel;
    }

    public void setLstCarrerasLazyModel(LazyDataModel<Carrera> lstCarrerasLazyModel) {
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
        lstCarreras = carreraFacade.findByFacultad(facultad.getIdFacultad());
    }

    public List<Facultad> completeWithInsert(String query) {
        List<Facultad> sugerencias = new ArrayList<>();
        try {
            sugerencias = facultadFacade.findByNameLike(query);
            if (sugerencias == null || sugerencias.isEmpty()) {
                if (Utilidades.espaciosFinales(query) >= 2) {
                    query = query.trim();
                    Facultad facultad = new Facultad();
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
