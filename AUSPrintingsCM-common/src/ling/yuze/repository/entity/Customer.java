/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roger
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findByCustid", query = "SELECT c FROM Customer c WHERE c.custid = :custid")
    , @NamedQuery(name = "Customer.findByCustabn", query = "SELECT c FROM Customer c WHERE c.custabn = :custabn")
    , @NamedQuery(name = "Customer.findByCustname", query = "SELECT c FROM Customer c WHERE c.custname = :custname")
    , @NamedQuery(name = "Customer.findByCustaddress", query = "SELECT c FROM Customer c WHERE c.custaddress = :custaddress")
    , @NamedQuery(name = "Customer.findByCustcentraltel", query = "SELECT c FROM Customer c WHERE c.custcentraltel = :custcentraltel")
    , @NamedQuery(name = "Customer.findByCustwebsite", query = "SELECT c FROM Customer c WHERE c.custwebsite = :custwebsite")
    , @NamedQuery(name = "Customer.findByCustfoundedyear", query = "SELECT c FROM Customer c WHERE c.custfoundedyear = :custfoundedyear")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTID")
    private Integer custid;
    @Basic(optional = false)
    @Column(name = "CUSTABN")
    private String custabn;
    @Basic(optional = false)
    @Column(name = "CUSTNAME")
    private String custname;
    @Basic(optional = false)
    @Column(name = "CUSTADDRESS")
    private String custaddress;
    @Basic(optional = false)
    @Column(name = "CUSTCENTRALTEL")
    private String custcentraltel;
    @Column(name = "CUSTWEBSITE")
    private String custwebsite;
    @Basic(optional = false)
    @Column(name = "CUSTFOUNDEDYEAR")
    private int custfoundedyear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custid")
    private Collection<Contact> contactCollection;
    @JoinColumn(name = "UID", referencedColumnName = "UID")
    @ManyToOne(optional = false)
    private Appuser uid;
    @JoinColumn(name = "INAME", referencedColumnName = "INAME")
    @ManyToOne(optional = false)
    private Industrytype iname;

    public Customer() {
    }

    public Customer(Integer custid) {
        this.custid = custid;
    }

    public Customer(Integer custid, String custabn, String custname, String custaddress, String custcentraltel, int custfoundedyear) {
        this.custid = custid;
        this.custabn = custabn;
        this.custname = custname;
        this.custaddress = custaddress;
        this.custcentraltel = custcentraltel;
        this.custfoundedyear = custfoundedyear;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getCustabn() {
        return custabn;
    }

    public void setCustabn(String custabn) {
        this.custabn = custabn;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustaddress() {
        return custaddress;
    }

    public void setCustaddress(String custaddress) {
        this.custaddress = custaddress;
    }

    public String getCustcentraltel() {
        return custcentraltel;
    }

    public void setCustcentraltel(String custcentraltel) {
        this.custcentraltel = custcentraltel;
    }

    public String getCustwebsite() {
        return custwebsite;
    }

    public void setCustwebsite(String custwebsite) {
        this.custwebsite = custwebsite;
    }

    public int getCustfoundedyear() {
        return custfoundedyear;
    }

    public void setCustfoundedyear(int custfoundedyear) {
        this.custfoundedyear = custfoundedyear;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
    }

    public Appuser getUid() {
        return uid;
    }

    public void setUid(Appuser uid) {
        this.uid = uid;
    }

    public Industrytype getIname() {
        return iname;
    }

    public void setIname(Industrytype iname) {
        this.iname = iname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custid != null ? custid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custid == null && other.custid != null) || (this.custid != null && !this.custid.equals(other.custid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.custname;
    }
    
}
