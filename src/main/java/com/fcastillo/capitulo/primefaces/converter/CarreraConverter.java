package com.fcastillo.capitulo.primefaces.converter;

import com.fcastillo.capitulo.primefaces.Carrera;
import com.fcastillo.capitulo.primefaces.Facultad;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import com.fcastillo.capitulo.primefaces.ejb.CarreraFacadeLocal;
import com.fcastillo.capitulo.primefaces.ejb.FacultadFacadeLocal;

@Named
@RequestScoped
public class CarreraConverter implements Converter {

    @EJB
    CarreraFacadeLocal carreraFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Carrera carrera = new Carrera();
        try {
            if (value != null) {
                carrera.setIdCarrera(Integer.parseInt(value));
                carrera = carreraFacade.find(carrera.getIdCarrera());
            }
        } catch (NumberFormatException e) {
            System.out.println("No se encontro la facultad " + e);
        }
        return carrera;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String carreraString = "";
        try {
            if (value instanceof Carrera) {
                Carrera carrera = (Carrera) value;
                carreraString = String.valueOf(carrera.getIdCarrera());
            } else if (value instanceof String) {
                carreraString = (String) value;
            }
        } catch (Exception e) {
            //JsfUtil.errorMessage("getAsString()" + e.getLocalizedMessage());
        }
        return carreraString;
    }

}
