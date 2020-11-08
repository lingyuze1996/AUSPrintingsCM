package ling.yuze.controller.customer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class EditIndustry implements Serializable {
    @ManagedProperty(value="#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    private Industrytype industry;
    private Integer id;
    
    @PostConstruct
    public void init() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void getIndustryById() {
        industry = customerManagedBean.getIndustryById(id);
    }
    
    public String editIndustry() {
        try {
            customerManagedBean.updateIndustry(industry);
            return "/faces/admin/industries?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
}
