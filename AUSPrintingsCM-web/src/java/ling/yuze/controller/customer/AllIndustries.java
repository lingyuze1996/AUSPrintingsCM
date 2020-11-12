package ling.yuze.controller.customer;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import ling.yuze.managedbean.CustomerManagedBean;
import ling.yuze.repository.entity.Industrytype;

/**
 *
 * @author Roger
 */
@Named
@RequestScoped
public class AllIndustries implements Serializable {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
   
    private List<Industrytype> industries;
    
    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
        
        industries = customerManagedBean.getAllIndustries();
    }

    public List<Industrytype> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industrytype> industries) {
        this.industries = industries;
    }
    
    public String editIndustry(Industrytype industry) {
        return "/faces/admin/editIndustry?faces-redirect=true&id=" + industry.getIid();
    }
    
    public String deleteIndustry(Industrytype industry) {
        try {
            customerManagedBean.deleteIndustry(industry);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Industry has been deleted successfully!")); 
            
            // Refresh List
            industries = customerManagedBean.getAllIndustries();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to delete industry! Please update customers in this industry first!")); 
        }
        
        return null;
    }
}
