package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.Facultades;
import com.fcastillo.capitulo.primefaces.ejb.FacultadesFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author fcastillo
 */
@Named(value = "facultadController")
@RequestScoped
public class FacultadController {

    @EJB
    FacultadesFacadeLocal facultadFacade;
    private List<Facultades> lstFacultades;
    private Facultades facultad = new Facultades();

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

    @PostConstruct
    public void init() {
        lstFacultades = facultadFacade.findAll();
    }

}
