package ling.yuze.controller.staff;
;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.managedbean.UserManagedBean;
import ling.yuze.repository.entity.Appuser;
import ling.yuze.repository.entity.Customer;
import ling.yuze.repository.entity.Industrytype;

/**
 *
 * @author Roger
 */
@Named
@SessionScoped
public class EditCustomerAdmin implements Serializable {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    @ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean;
    
    private List<Appuser> users;
    private Integer uid;
    private Customer customer;
    private Integer id;
    private String industry;
    private List<Industrytype> industries;
    
    public List<Appuser> getUsers() {
        return users;
    }

    public void setUsers(List<Appuser> users) {
        this.users = users;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uId) {
        this.uid = uId;
    }

    public List<Industrytype> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industrytype> industries) {
        this.industries = industries;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        users = userManagedBean.getAllUsers();
        industries = customerManagedBean.getAllIndustries();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void getCustomerById() {
        customer = customerManagedBean.getCustomerById(id);
        industry = customer.getIid().getIname();
    }
    
    public String editCustomer() {
        try {
            Appuser assigned = userManagedBean.getUserById(uid);
            customer.setUid(assigned);
            
            customer.setIid(customerManagedBean.getIndustryByName(industry));
            customerManagedBean.editCustomer(customer);
            return "/faces/admin/customer?faces-redirect=true&id=" + customer.getCustid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
}
