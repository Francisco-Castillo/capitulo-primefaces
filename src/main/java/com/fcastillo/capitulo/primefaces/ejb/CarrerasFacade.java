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

        Root<Carreras> raiz = criteriaQuery.from(Carreras.class);
        Join<Carreras, Facultades> joinFacultad = raiz.join("idfacultad", JoinType.INNER);

        List<Predicate> listaPredicados = new ArrayList<>();
        List<Carreras> lstCarreras = new ArrayList<>();

        criteriaQuery.select(raiz);

        if (sortField != null) {
            criteriaQuery.orderBy(sortOrder == SortOrder.ASCENDING ? criteriaBuilder.asc(raiz.get(sortField)) : criteriaBuilder.desc(raiz.get(sortField)));
        }

        try {
            String nombreFacultad = "";
            String nombreCarrera = "";
            if (filters != null && filters.size() > 0) {
                if (filters.get("nombre") != null) {
                    nombreCarrera = (String) filters.get("nombre");
                    Predicate predicadoNombre = criteriaBuilder.like(criteriaBuilder.lower(raiz.get("nombre")), "%" + nombreCarrera.toLowerCase() + "%");
                    listaPredicados.add(predicadoNombre);
                }

                if (filters.get("idfacultad") != null) {
                    nombreFacultad = (String) filters.get("idfacultad");
                    Predicate predicadoFacultad = criteriaBuilder.like(criteriaBuilder.lower(joinFacultad.get("nombre")), "%" + nombreFacultad.toLowerCase() + "%");
                    listaPredicados.add(predicadoFacultad);
                }

                if (listaPredicados.size() > 0) {
                    listaPredicados.forEach((p) -> {
                        criteriaQuery.where(listaPredicados.toArray(new Predicate[listaPredicados.size()]));
                    });
                }
            }
            TypedQuery<Carreras> query = em.createQuery(criteriaQuery);
            query.setFirstResult(start);
            query.setMaxResults(size);

            lstCarreras = query.getResultList();
        } catch (Exception e) {
            System.out.println("findByParams() : " + e.getMessage());
        }
        return lstCarreras;
    }

    @Override
    public int getFilteredRowCount(Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Carreras> criteriaQuery = criteriaBuilder.createQuery(Carreras.class);

        Root<Carreras> raiz = criteriaQuery.from(Carreras.class);
        Join<Carreras, Facultades> joinFacultad = raiz.join("idfacultad", JoinType.INNER);

        List<Predicate> listaPredicados = new ArrayList<>();
        List<Carreras> lstCarreras = new ArrayList<>();

        criteriaQuery.select(raiz);

        String nombreFacultad = "";
        String nombreCarrera = "";
        if (filters != null && filters.size() > 0) {
            if (filters.get("nombre") != null) {
                nombreCarrera = (String) filters.get("nombre");
                Predicate predicadoNombre = criteriaBuilder.like(criteriaBuilder.lower(raiz.get("nombre")), "%" + nombreCarrera.toLowerCase() + "%");
                listaPredicados.add(predicadoNombre);
            }

            if (filters.get("idfacultad") != null) {
                nombreFacultad = (String) filters.get("idfacultad");
                Predicate predicadoFacultad = criteriaBuilder.like(criteriaBuilder.lower(joinFacultad.get("nombre")), "%" + nombreFacultad.toLowerCase() + "%");
                listaPredicados.add(predicadoFacultad);
            }

            if (listaPredicados.size() > 0) {
                listaPredicados.forEach((p) -> {
                    System.out.println("Predicado : " + p.getExpressions().toString());
                    criteriaQuery.where(listaPredicados.toArray(new Predicate[listaPredicados.size()]));
                });
            }
        }

        TypedQuery<Carreras> query = em.createQuery(criteriaQuery);

        return query.getResultList().size();

    }

    @Override
    public List<Carreras> findByNameLike(String nombrecarrera) {
        Query query = em.createQuery("SELECT c FROM Carreras c WHERE lower(c.nombre) like :nombre");
        nombrecarrera = "%" + nombrecarrera.trim() + "%";
        return query.setParameter("nombre", nombrecarrera).getResultList();
    }

}
