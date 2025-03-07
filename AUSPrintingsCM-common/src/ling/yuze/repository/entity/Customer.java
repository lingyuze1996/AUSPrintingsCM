package ling.yuze.repository.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findByUserid", query = "SELECT c FROM Customer c WHERE c.uid.uid = :uid")
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
    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "CUSTABN")
    private String custabn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CUSTNAME")
    private String custname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "CUSTADDRESS")
    private String custaddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 10)
    @Column(name = "CUSTCENTRALTEL")
    private String custcentraltel;
    @Size(max = 40)
    @Column(name = "CUSTWEBSITE")
    private String custwebsite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTFOUNDEDYEAR")
    private Integer custfoundedyear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custid", fetch = FetchType.EAGER)
    private List<Contact> contactList;
    @JoinColumn(name = "UID", referencedColumnName = "UID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Appuser uid;
    @JoinColumn(name = "IID", referencedColumnName = "IID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Industrytype iid;

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

    public Integer getCustfoundedyear() {
        return custfoundedyear;
    }

    public void setCustfoundedyear(Integer custfoundedyear) {
        this.custfoundedyear = custfoundedyear;
    }

    @XmlTransient
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public Appuser getUid() {
        return uid;
    }

    public void setUid(Appuser uid) {
        this.uid = uid;
    }

    public Industrytype getIid() {
        return iid;
    }

    public void setIid(Industrytype iid) {
        this.iid = iid;
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
        return "ling.yuze.repository.entity.Customer[ custid=" + custid + " ]";
    }
    
}
