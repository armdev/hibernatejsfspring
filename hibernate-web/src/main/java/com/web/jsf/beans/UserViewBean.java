package com.web.jsf.beans;

import com.project.entities.User;
import com.project.services.UserService;
import com.web.jsf.utils.ParamUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Home
 */
@ManagedBean(name = "userViewBean")
@SessionScoped
public class UserViewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{i18n}")
    private ResourceBundle bundle = null;
    private User user = new User();
    //@ManagedProperty("#{param.userId}")
    private Long userId;
    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public UserViewBean() {
        

    }

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        
        userId = ParamUtil.longValue((this.getRequestParameter("userId")));
        
        user = userService.getUserById(userId);
       // System.out.println("User id 11 " + user.getId());
      //  System.out.println("User post construct " + user);

        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userlist.jsf?hack==false");
            } catch (IOException ex) {
                Logger.getLogger(UserViewBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String updateUser() {
      
        if (user == null || user.getId() == null) {
            return "userlist?faces-redirect=true";
        } else {
            boolean checkEmail = userService.checkUserEmailForUpdate(userId, user.getEmail());
            if (!checkEmail) {
                userService.updateUser(user);
            } else {
                FacesMessage msg = new FacesMessage(bundle.getString("emailbusy"), bundle.getString("emailbusy"));
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        }
        return "userlist?faces-redirect=true";
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

   
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String getRequestParameter(String paramName) {
        String returnValue = null;
        if (externalContext.getRequestParameterMap().containsKey(paramName)) {
            returnValue = (externalContext.getRequestParameterMap().get(paramName));
        }
        return returnValue;
    }

}
