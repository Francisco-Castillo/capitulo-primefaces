/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Facultad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface FacultadFacadeLocal {

    void create(Facultad facultades);

    void edit(Facultad facultades);

    void remove(Facultad facultades);

    Facultad find(Object id);

    List<Facultad> findAll();

    List<Facultad> findRange(int[] range);

    int count();

    List<Facultad> findByNameLike(String nombrefacultad);

}
