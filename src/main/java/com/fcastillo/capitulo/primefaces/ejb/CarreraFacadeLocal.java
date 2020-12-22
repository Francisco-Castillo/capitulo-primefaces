/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Carrera;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fcastillo
 */
@Local
public interface CarreraFacadeLocal {

    void create(Carrera carreras);

    void edit(Carrera carreras);

    void remove(Carrera carreras);

    Carrera find(Object id);

    List<Carrera> findAll();

    List<Carrera> findByFacultad(int idfacultad);

    List<Carrera> findRange(int[] range);

    int count();

    /**
     * Carga en memoria solo los registros que se necesiten y no todo el conjunto de datos. Cuenta con paginación, filtrado y ordenamiento de datos.
     *
     * @param first Primer página a mostrar.
     * @param pageSize  Cantidad de registros a mostrar por página.
     * @param sortField Campos por los que se desea ordenar los registros.
     * @param sortOrder Tipo de ordenamiento de los registros (Ascending - Descending).
     * @param filterBy  Map de filtros.
     * @return Registros a mostrar.
     */
    List<Carrera> findByParams(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filterBy);

    int getCount(Map<String, FilterMeta> filterBy);

    List<Carrera> findByNameLike(String nombrecarrera);
}
