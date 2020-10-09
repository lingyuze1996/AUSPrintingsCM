/*
 * To change this license header, choose License Headers in Project Properties.
 */
package ling.yuze.repository.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roger
 */
@Entity
@Table(name = "CONTACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
    , @NamedQuery(name = "Contact.findByContid", query = "SELECT c FROM Contact c WHERE c.contid = :contid")
    , @NamedQuery(name = "Contact.findByContfirstname", query = "SELECT c FROM Contact c WHERE c.contfirstname = :contfirstname")
    , @NamedQuery(name = "Contact.findByContlastname", query = "SELECT c FROM Contact c WHERE c.contlastname = :contlastname")
    , @NamedQuery(name = "Contact.findByContgender", query = "SELECT c FROM Contact c WHERE c.contgender = :contgender")
    , @NamedQuery(name = "Contact.findByContposition", query = "SELECT c FROM Contact c WHERE c.contposition = :contposition")
    , @NamedQuery(name = "Contact.findByContphoneno", query = "SELECT c FROM Contact c WHERE c.contphoneno = :contphoneno")
    , @NamedQuery(name = "Contact.findByContemail", query = "SELECT c FROM Contact c WHERE c.contemail = :contemail")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CONTID")
    private Integer contid;
    @Basic(optional = false)
    @Column(name = "CONTFIRSTNAME")
    private String contfirstname;
    @Basic(optional = false)
    @Column(name = "CONTLASTNAME")
    private String contlastname;
    @Basic(optional = false)
    @Column(name = "CONTGENDER")
    private Character contgender;
    @Basic(optional = false)
    @Column(name = "CONTPOSITION")
    private String contposition;
    @Basic(optional = false)
    @Column(name = "CONTPHONENO")
    private String contphoneno;
    @Basic(optional = false)
    @Column(name = "CONTEMAIL")
    private String contemail;
    @JoinColumn(name = "CUSTID", referencedColumnName = "CUSTID")
    @ManyToOne(optional = false)
    private Customer custid;

    public Contact() {
    }

    public Contact(Integer contid) {
        this.contid = contid;
    }

    public Contact(Integer contid, String contfirstname, String contlastname, Character contgender, String contposition, String contphoneno, String contemail) {
        this.contid = contid;
        this.contfirstname = contfirstname;
        this.contlastname = contlastname;
        this.contgender = contgender;
        this.contposition = contposition;
        this.contphoneno = contphoneno;
        this.contemail = contemail;
    }

    public Integer getContid() {
        return contid;
    }

    public void setContid(Integer contid) {
        this.contid = contid;
    }

    public String getContfirstname() {
        return contfirstname;
    }

    public void setContfirstname(String contfirstname) {
        this.contfirstname = contfirstname;
    }

    public String getContlastname() {
        return contlastname;
    }

    public void setContlastname(String contlastname) {
        this.contlastname = contlastname;
    }

    public Character getContgender() {
        return contgender;
    }

    public void setContgender(Character contgender) {
        this.contgender = contgender;
    }

    public String getContposition() {
        return contposition;
    }

    public void setContposition(String contposition) {
        this.contposition = contposition;
    }

    public String getContphoneno() {
        return contphoneno;
    }

    public void setContphoneno(String contphoneno) {
        this.contphoneno = contphoneno;
    }

    public String getContemail() {
        return contemail;
    }

    public void setContemail(String contemail) {
        this.contemail = contemail;
    }

    public Customer getCustid() {
        return custid;
    }

    public void setCustid(Customer custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contid != null ? contid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contid == null && other.contid != null) || (this.contid != null && !this.contid.equals(other.contid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.contfirstname + " " + this.contlastname;
    }
    
}
