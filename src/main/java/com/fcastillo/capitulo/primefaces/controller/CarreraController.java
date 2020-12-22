package com.fcastillo.capitulo.primefaces.controller;

import com.fcastillo.capitulo.primefaces.Carrera;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.fcastillo.capitulo.primefaces.ejb.CarreraFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named(value = "carreraController")
@ViewScoped
public class CarreraController implements Serializable {

  @EJB
  CarreraFacadeLocal carreraFacade;

  private LazyDataModel<Carrera> lstCarrerasLazyModel;
  private List<Carrera> lstCarreras;
  private Carrera carrera = new Carrera();
  private Carrera carreraSeleccionada;

  public LazyDataModel<Carrera> getLstCarrerasLazyModel() {
    return lstCarrerasLazyModel;
  }

  public void setLstCarrerasLazyModel(LazyDataModel<Carrera> lstCarrerasLazyModel) {
    this.lstCarrerasLazyModel = lstCarrerasLazyModel;
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

  public Carrera getCarreraSeleccionada() {
    return carreraSeleccionada;
  }

  public void setCarreraSeleccionada(Carrera carreraSeleccionada) {
    this.carreraSeleccionada = carreraSeleccionada;
  }

  @PostConstruct
  public void init() {
    iniciarLazyLoading();
  }

  private void iniciarLazyLoading() {
    lstCarrerasLazyModel = new LazyDataModel<Carrera>() {
      @Override
      public List<Carrera> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filterBy) {
        List<Carrera> lstCarreras = carreraFacade.findByParams(first, pageSize, sortField, sortOrder, filterBy);
        lstCarrerasLazyModel.setRowCount(carreraFacade.getCount(filterBy));
        refreshTableState();
        return lstCarreras;
      }

      @Override
      public Object getRowKey(Carrera object) {
        return object.getIdCarrera();
      }

      @Override
      public Carrera getRowData(String rowKey) {
        Carrera carrera = null;
        try {
          carrera = carreraFacade.find(Integer.parseInt(rowKey));
        } catch (NumberFormatException e) {
          System.out.println("ocurrio un error : " + e.getLocalizedMessage());
        }
        return carrera;
      }
    };
  }

  public List<Carrera> completeNombreCarrera(String query) {
    List<Carrera> sugerencias = new ArrayList<>();
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

  private void refreshTableState() {
    UIComponent tabla = FacesContext.getCurrentInstance().getViewRoot().findComponent(":frmPrincipal:tblCarreras");
    tabla.setValueExpression("sortBy", null);

  }
}
