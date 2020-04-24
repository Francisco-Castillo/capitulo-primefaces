/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fcastillo
 */
@Stateless
public class GraficosFacade implements GraficosFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_capitulo-primefaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public List<Object[]> cantCarrerasPorFacultad() {
        String consulta = "SELECT f.abreviatura, COUNT(c) FROM Carreras c JOIN c.idfacultad f GROUP BY f.idfacultad";
        Query query = em.createQuery(consulta);
        return query.getResultList();
    }

}
