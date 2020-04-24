/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Carreras;
import com.fcastillo.capitulo.primefaces.Facultades;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fcastillo
 */
@Stateless
public class CarrerasFacade extends AbstractFacade<Carreras> implements CarrerasFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_capitulo-primefaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarrerasFacade() {
        super(Carreras.class);
    }

    @Override
    public List<Carreras> findByFacultad(int idfacultad) {
        String consulta = "FROM Carreras c WHERE c.idfacultad.idfacultad=?1";
        TypedQuery query = em.createQuery(consulta, Carreras.class);
        query.setParameter(1, idfacultad);
        return query.getResultList();
    }

    @Override
    public List<Carreras> findByParams(int start, int size, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Carreras> criteriaQuery = criteriaBuilder.createQuery(Carreras.class);
        Root<Carreras> root = criteriaQuery.from(Carreras.class);
        CriteriaQuery<Carreras> select = criteriaQuery.select(root);

        Join<Carreras, Facultades> facultad = root.join("idfacultad");

        // Ordenamiento
        if (sortField != null) {
            criteriaQuery.orderBy(sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(root.get(sortField)) : criteriaBuilder.desc(root.get(sortField)));
        }

        if (filters != null && filters.size() > 0) {
            List<Predicate> predicados = new ArrayList<>();

            filters.entrySet().forEach((entry) -> {
                String key = entry.getKey();
                Object val = entry.getValue();
                if (!(val == null)) {
                    // Construimos la expresion con los predicados que si existan
                    Expression<String> expresion = root.get(key).as(String.class);

                    Predicate predicado = criteriaBuilder.like(criteriaBuilder.lower(expresion), "%" + val.toString().toLowerCase() + "%");
                    predicados.add(predicado);
                }
            });
            if (predicados.size() > 0) {
                criteriaQuery.where(criteriaBuilder.and(predicados.toArray(new Predicate[predicados.size()])));
            }
        }
        // Creamos la consulta
        TypedQuery<Carreras> consulta = em.createQuery(select);
        consulta.setFirstResult(start);
        consulta.setMaxResults(size);

        return consulta.getResultList();

    }

    @Override
    public int getFilteredRowCount(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<Carreras> root = criteriaQuery.from(Carreras.class);
        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));

        if (filters != null && filters.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            filters.entrySet().forEach((entry) -> {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (!(value == null)) {
                    Expression<String> expr = root.get(field).as(String.class);
                    Predicate p = cb.like(cb.lower(expr),
                            "%" + value.toString().toLowerCase() + "%");
                    predicates.add(p);
                }
            });

            if (predicates.size() > 0) {
                criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }
        }
        Long count = em.createQuery(select).getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Carreras> findByNameLike(String nombrecarrera) {
        Query query = em.createQuery("SELECT c FROM Carreras c WHERE lower(c.nombre) like :nombre");
        nombrecarrera = "%" + nombrecarrera.trim() + "%";
        return query.setParameter("nombre", nombrecarrera).getResultList();
    }

}
