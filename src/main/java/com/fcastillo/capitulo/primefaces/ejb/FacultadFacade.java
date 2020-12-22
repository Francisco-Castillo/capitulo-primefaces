/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Facultad;
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
public class FacultadFacade extends AbstractFacade<Facultad> implements FacultadFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_capitulo-primefaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadFacade() {
        super(Facultad.class);
    }

    @Override
    public List<Facultad> findByNameLike(String nombrefacultad) {
        Query query = em.createQuery("SELECT f FROM Facultad f WHERE lower(f.nombre) like :nombre");
        nombrefacultad = "%" + nombrefacultad.trim() + "%";
        return query.setParameter("nombre", nombrefacultad).getResultList();
    }

}
