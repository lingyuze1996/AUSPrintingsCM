/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.repository.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Roger
 */
@Entity
@Table(name = "INDUSTRYTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Industrytype.findAll", query = "SELECT i FROM Industrytype i")
    , @NamedQuery(name = "Industrytype.findByIid", query = "SELECT i FROM Industrytype i WHERE i.iid = :iid")
    , @NamedQuery(name = "Industrytype.findByIname", query = "SELECT i FROM Industrytype i WHERE i.iname = :iname")
    , @NamedQuery(name = "Industrytype.findByIdesc", query = "SELECT i FROM Industrytype i WHERE i.idesc = :idesc")})
public class Industrytype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IID")
    private Integer iid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INAME")
    private String iname;
    @Size(max = 50)
    @Column(name = "IDESC")
    private String idesc;
    @OneToMany(mappedBy = "iid", fetch = FetchType.EAGER)
    private List<Customer> customerList;

    public Industrytype() {
    }

    public Industrytype(Integer iid) {
        this.iid = iid;
    }

    public Industrytype(Integer iid, String iname) {
        this.iid = iid;
        this.iname = iname;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    @XmlTransient
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iid != null ? iid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Industrytype)) {
            return false;
        }
        Industrytype other = (Industrytype) object;
        if ((this.iid == null && other.iid != null) || (this.iid != null && !this.iid.equals(other.iid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ling.yuze.repository.entity.Industrytype[ iid=" + iid + " ]";
    }
    
}
