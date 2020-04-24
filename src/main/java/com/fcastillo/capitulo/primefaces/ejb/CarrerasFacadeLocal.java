/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Carreras;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fcastillo
 */
@Local
public interface CarrerasFacadeLocal {

    void create(Carreras carreras);

    void edit(Carreras carreras);

    void remove(Carreras carreras);

    Carreras find(Object id);

    List<Carreras> findAll();

    List<Carreras> findByFacultad(int idfacultad);

    List<Carreras> findRange(int[] range);

    int count();

    List<Carreras> findByParams(int start, int size, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    int getFilteredRowCount(Map<String, Object> filters);

    List<Carreras> findByNameLike(String nombrecarrera);
}
