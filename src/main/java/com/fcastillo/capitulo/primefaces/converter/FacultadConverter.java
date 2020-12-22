package com.fcastillo.capitulo.primefaces.converter;

import com.fcastillo.capitulo.primefaces.Facultad;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import com.fcastillo.capitulo.primefaces.ejb.FacultadFacadeLocal;

@Named
@RequestScoped
public class FacultadConverter implements Converter {

    @EJB
    FacultadFacadeLocal facultadFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Facultad facultad = new Facultad();
        try {
            if (value != null) {
                facultad.setIdFacultad(Integer.parseInt(value));
                facultad = facultadFacade.find(facultad.getIdFacultad());
            }
        } catch (NumberFormatException e) {
            System.out.println("No se encontro la facultad " + e);
        }
        return facultad;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String facultadString = "";
        try {
            if (value instanceof Facultad) {
                Facultad facultad = (Facultad) value;
                facultadString = String.valueOf(facultad.getIdFacultad());
            } else if (value instanceof String) {
                facultadString = (String) value; 
            }
        } catch (Exception e) {
            //JsfUtil.errorMessage("getAsString()" + e.getLocalizedMessage());
        }
        return facultadString;
    }

}
