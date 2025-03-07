/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.repository.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roger
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a")
    , @NamedQuery(name = "Appuser.findByUid", query = "SELECT a FROM Appuser a WHERE a.uid = :uid")
    , @NamedQuery(name = "Appuser.findByUrole", query = "SELECT a FROM Appuser a WHERE a.urole = :urole")
    , @NamedQuery(name = "Appuser.findByUfirstname", query = "SELECT a FROM Appuser a WHERE a.ufirstname = :ufirstname")
    , @NamedQuery(name = "Appuser.findByUlastname", query = "SELECT a FROM Appuser a WHERE a.ulastname = :ulastname")
    , @NamedQuery(name = "Appuser.findByUdob", query = "SELECT a FROM Appuser a WHERE a.udob = :udob")
    , @NamedQuery(name = "Appuser.findByUgender", query = "SELECT a FROM Appuser a WHERE a.ugender = :ugender")
    , @NamedQuery(name = "Appuser.findByUemail", query = "SELECT a FROM Appuser a WHERE a.uemail = :uemail")
    , @NamedQuery(name = "Appuser.findByUpassword", query = "SELECT a FROM Appuser a WHERE a.upassword = :upassword")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UID")
    private Integer uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UROLE")
    private String urole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UFIRSTNAME")
    private String ufirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ULASTNAME")
    private String ulastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UDOB")
    @Temporal(TemporalType.DATE)
    private Date udob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UGENDER")
    private Character ugender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "UEMAIL")
    private String uemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 64, max = 64)
    @Column(name = "UPASSWORD")
    private String upassword;
    @OneToMany(mappedBy = "uid", fetch = FetchType.EAGER)
    private List<Customer> customerList;

    public Appuser() {
    }

    public Appuser(Integer uid) {
        this.uid = uid;
    }

    public Appuser(Integer uid, String urole, String ufirstname, String ulastname, Date udob, Character ugender, String uemail, String upassword) {
        this.uid = uid;
        this.urole = urole;
        this.ufirstname = ufirstname;
        this.ulastname = ulastname;
        this.udob = udob;
        this.ugender = ugender;
        this.uemail = uemail;
        this.upassword = upassword;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public String getUfirstname() {
        return ufirstname;
    }

    public void setUfirstname(String ufirstname) {
        this.ufirstname = ufirstname;
    }

    public String getUlastname() {
        return ulastname;
    }

    public void setUlastname(String ulastname) {
        this.ulastname = ulastname;
    }

    public Date getUdob() {
        return udob;
    }

    public void setUdob(Date udob) {
        this.udob = udob;
    }

    public Character getUgender() {
        return ugender;
    }

    public void setUgender(Character ugender) {
        this.ugender = ugender;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
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
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ling.yuze.repository.entity.Appuser[ uid=" + uid + " ]";
    }
    
}
