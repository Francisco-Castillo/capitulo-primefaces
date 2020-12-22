/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "facultades")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
  @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad"),
  @NamedQuery(name = "Facultad.findByNombre", query = "SELECT f FROM Facultad f WHERE f.nombre = :nombre"),
  @NamedQuery(name = "Facultad.findByAbreviatura", query = "SELECT f FROM Facultad f WHERE f.abreviatura = :abreviatura")})
public class Facultad implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_facultad")
  private Integer idFacultad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "nombre")
  private String nombre;
  @Size(max = 20)
  @Column(name = "abreviatura")
  private String abreviatura;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultad")
  private List<Carrera> carreraList;

  public Facultad() {
  }

  public Facultad(Integer idFacultad) {
    this.idFacultad = idFacultad;
  }

  public Facultad(Integer idFacultad, String nombre) {
    this.idFacultad = idFacultad;
    this.nombre = nombre;
  }

  public Integer getIdFacultad() {
    return idFacultad;
  }

  public void setIdFacultad(Integer idFacultad) {
    this.idFacultad = idFacultad;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getAbreviatura() {
    return abreviatura;
  }

  public void setAbreviatura(String abreviatura) {
    this.abreviatura = abreviatura;
  }

  @XmlTransient
  public List<Carrera> getCarreraList() {
    return carreraList;
  }

  public void setCarreraList(List<Carrera> carreraList) {
    this.carreraList = carreraList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idFacultad != null ? idFacultad.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Facultad)) {
      return false;
    }
    Facultad other = (Facultad) object;
    if ((this.idFacultad == null && other.idFacultad != null) || (this.idFacultad != null && !this.idFacultad.equals(other.idFacultad))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.capitulo.primefaces.Facultad[ idFacultad=" + idFacultad + " ]";
  }
  
}
