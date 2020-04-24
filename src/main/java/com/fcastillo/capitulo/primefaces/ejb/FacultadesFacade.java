/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Facultades;
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
public class FacultadesFacade extends AbstractFacade<Facultades> implements FacultadesFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_capitulo-primefaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadesFacade() {
        super(Facultades.class);
    }

    @Override
    public List<Facultades> findByNameLike(String nombrefacultad) {
        Query query = em.createQuery("SELECT f FROM Facultades f WHERE lower(f.nombre) like :nombre");
        nombrefacultad = "%" + nombrefacultad.trim() + "%";
        return query.setParameter("nombre", nombrefacultad).getResultList();
    }

}
