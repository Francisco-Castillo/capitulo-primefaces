package com.fcastillo.capitulo.primefaces.converter;

import com.fcastillo.capitulo.primefaces.Facultades;
import com.fcastillo.capitulo.primefaces.ejb.FacultadesFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class FacultadConverter implements Converter {

    @EJB
    FacultadesFacadeLocal facultadFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Facultades facultad = new Facultades();
        try {
            if (value != null) {
                facultad.setIdfacultad(Integer.parseInt(value));
                facultad = facultadFacade.find(facultad.getIdfacultad());
            }
        } catch (NumberFormatException e) {
            System.out.println("No se encontro la facultad " + e);
        }
        return facultad;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String r = "";
        try {
            if (value instanceof Facultades) {
                Facultades l = (Facultades) value;
                r = String.valueOf(l.getIdfacultad());
            } else if (value instanceof String) {
                r = (String) value; 
            }
        } catch (Exception e) {
            //JsfUtil.errorMessage("getAsString()" + e.getLocalizedMessage());
        }
        return r;
    }

}
