package com.fcastillo.capitulo.primefaces.converter;

import com.fcastillo.capitulo.primefaces.Carreras;
import com.fcastillo.capitulo.primefaces.Facultades;
import com.fcastillo.capitulo.primefaces.ejb.CarrerasFacadeLocal;
import com.fcastillo.capitulo.primefaces.ejb.FacultadesFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class CarreraConverter implements Converter {

    @EJB
    CarrerasFacadeLocal carreraFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Carreras carrera = new Carreras();
        try {
            if (value != null) {
                carrera.setIdcarrera(Integer.parseInt(value));
                carrera = carreraFacade.find(carrera.getIdcarrera());
            }
        } catch (NumberFormatException e) {
            System.out.println("No se encontro la facultad " + e);
        }
        return carrera;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String r = "";
        try {
            if (value instanceof Carreras) {
                Carreras l = (Carreras) value;
                r = String.valueOf(l.getIdcarrera());
            } else if (value instanceof String) {
                r = (String) value;
            }
        } catch (Exception e) {
            //JsfUtil.errorMessage("getAsString()" + e.getLocalizedMessage());
        }
        return r;
    }

}
