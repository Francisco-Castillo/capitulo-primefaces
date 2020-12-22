/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "carreras")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
  @NamedQuery(name = "Carrera.findByIdCarrera", query = "SELECT c FROM Carrera c WHERE c.idCarrera = :idCarrera"),
  @NamedQuery(name = "Carrera.findByNombre", query = "SELECT c FROM Carrera c WHERE c.nombre = :nombre"),
  @NamedQuery(name = "Carrera.findByTipo", query = "SELECT c FROM Carrera c WHERE c.tipo = :tipo")})
public class Carrera implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_carrera")
  private Integer idCarrera;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 150)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Column(name = "tipo")
  private int tipo;
  @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad")
  @ManyToOne(optional = false)
  private Facultad idFacultad;

  public Carrera() {
  }

  public Carrera(Integer idCarrera) {
    this.idCarrera = idCarrera;
  }

  public Carrera(Integer idCarrera, String nombre, int tipo) {
    this.idCarrera = idCarrera;
    this.nombre = nombre;
    this.tipo = tipo;
  }

  public Integer getIdCarrera() {
    return idCarrera;
  }

  public void setIdCarrera(Integer idCarrera) {
    this.idCarrera = idCarrera;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public Facultad getIdFacultad() {
    return idFacultad;
  }

  public void setIdFacultad(Facultad idFacultad) {
    this.idFacultad = idFacultad;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idCarrera != null ? idCarrera.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Carrera)) {
      return false;
    }
    Carrera other = (Carrera) object;
    if ((this.idCarrera == null && other.idCarrera != null) || (this.idCarrera != null && !this.idCarrera.equals(other.idCarrera))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.capitulo.primefaces.Carrera[ idCarrera=" + idCarrera + " ]";
  }
  
}
