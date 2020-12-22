package com.fcastillo.capitulo.primefaces.ejb;

import com.fcastillo.capitulo.primefaces.Carrera;
import com.fcastillo.capitulo.primefaces.Facultad;
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
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author fcastillo
 */
@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> implements CarreraFacadeLocal {

  @PersistenceContext(unitName = "com.fcastillo_capitulo-primefaces_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CarreraFacade() {
    super(Carrera.class);
  }

  @Override
  public List<Carrera> findByFacultad(int idfacultad) {
    String consulta = "FROM Carrera c WHERE c.idFacultad.idFacultad=?1";
    TypedQuery query = em.createQuery(consulta, Carrera.class);
    query.setParameter(1, idfacultad);
    return query.getResultList();
  }

  @Override
  public List<Carrera> findByParams(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filterBy) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Carrera> criteriaQuery = criteriaBuilder.createQuery(Carrera.class);
    
    Root<Carrera> root = criteriaQuery.from(Carrera.class);
    
    Join<Carrera, Facultad> joinFacultad = root.join("idFacultad", JoinType.INNER);

    CriteriaQuery<Carrera> select = criteriaQuery.select(root);
   
    List<Predicate> lstPredicados = new ArrayList<>();

    if (sortField != null) {
      criteriaQuery.orderBy(sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(root.get(sortField)) : criteriaBuilder.desc(root.get(sortField)));
    }

    if (!filterBy.isEmpty()) {
      filterBy.forEach((k, v) -> {
        if (v.getFilterValue() != null) {

          if (v.getFilterField().equals("idFacultad")) {
            Predicate predicadoFacultad = criteriaBuilder.like(criteriaBuilder.lower(joinFacultad.get("nombre")), "%" + v.getFilterValue().toString().toLowerCase() + "%");
            lstPredicados.add(predicadoFacultad);
          } else {
            Predicate predicado = criteriaBuilder.like(criteriaBuilder.lower(root.get(k)), "%" + v.getFilterValue().toString().toLowerCase() + "%");
            lstPredicados.add(predicado);
          }

        }
      });
    }

    if (lstPredicados.size() > 0) {
      lstPredicados.forEach((p) -> {
        criteriaQuery.where(lstPredicados.toArray(new Predicate[lstPredicados.size()]));
      });
    }

    TypedQuery<Carrera> query = em.createQuery(select);
    query.setFirstResult(first);
    query.setMaxResults(pageSize);

    return query.getResultList();
  }

  @Override
  public int getCount(Map<String, FilterMeta> filterBy) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Carrera> criteriaQuery = criteriaBuilder.createQuery(Carrera.class);

    Root<Carrera> root = criteriaQuery.from(Carrera.class);

    Join<Carrera, Facultad> joinFacultad = root.join("idFacultad", JoinType.INNER);

    CriteriaQuery<Carrera> select = criteriaQuery.select(root);

    List<Predicate> lstPredicados = new ArrayList<>();

    if (!filterBy.isEmpty()) {
      filterBy.forEach((k, v) -> {
        if (v.getFilterValue() != null) {

          if (v.getFilterField().equals("idFacultad")) {
            Predicate predicadoFacultad = criteriaBuilder.like(criteriaBuilder.lower(joinFacultad.get("nombre")), "%" + v.getFilterValue().toString().toLowerCase() + "%");
            lstPredicados.add(predicadoFacultad);
          } else {
            Predicate predicado = criteriaBuilder.like(criteriaBuilder.lower(root.get(k)), "%" + v.getFilterValue().toString().toLowerCase() + "%");
            lstPredicados.add(predicado);
          }

        }
      });
    }

    // Añadimos predicados al criterio de busqueda
    if (lstPredicados.size() > 0) {
      lstPredicados.forEach((p) -> {
        criteriaQuery.where(lstPredicados.toArray(new Predicate[lstPredicados.size()]));
      });
    }

    TypedQuery<Carrera> query = em.createQuery(select);
    List<Carrera> lstCarreras = query.getResultList();
    return lstCarreras.size();

  }

  @Override
  public List<Carrera> findByNameLike(String nombrecarrera) {
    Query query = em.createQuery("SELECT c FROM Carrera c WHERE lower(c.nombre) like :nombre");
    nombrecarrera = "%" + nombrecarrera.trim() + "%";
    return query.setParameter("nombre", nombrecarrera).getResultList();
  }

}
