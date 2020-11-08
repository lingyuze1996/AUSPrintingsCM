package ling.yuze.controller.customer;

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
public class AddIndustry {
    @ManagedProperty(value="#{userManagedBean}")
    private CustomerManagedBean customerManagedBean;
    private Industrytype industry;
    
    @PostConstruct
    public void init() {
        industry = new Industrytype();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
    }

    public Industrytype getIndustry() {
        return industry;
    }

    public void setIndustry(Industrytype industry) {
        this.industry = industry;
    }
    
    
    
    public String createIndustry() {
        try {
            customerManagedBean.createIndustry(industry);
            return "/faces/admin/industries?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail to create new industry!")); 
        }
        
        return null;
    }
    
}
