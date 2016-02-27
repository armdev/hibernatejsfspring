package com.web.jsf.beans.auth;

import com.project.entities.User;
import com.project.services.UserService;
import com.web.jsf.handlers.SessionContext;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


/**
 *
 * @author armena
 */
@ManagedBean(name = "userLogin")
@RequestScoped
public class UserLogin {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String email;
    private String password;

    @ManagedProperty("#{i18n}")
    private ResourceBundle bundle = null;

    @ManagedProperty("#{sessionContext}")
    private SessionContext sessionContext = null;

    public UserLogin() {
    }

    public String loginUser() {
        User user = userService.userLogin(email, password);
        if (user != null) {
            sessionContext.setUser(user);
            return "userlist";
        } else {
            FacesMessage msg = new FacesMessage(bundle.getString("nouser"), bundle.getString("nouser"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
//
//    @Produces
//    public PropertyResourceBundle getBundle() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        return context.getApplication().evaluateExpressionGet(context, "#{i18n}", PropertyResourceBundle.class);
//    }
//
//    @Produces
//    public SessionContext getSessionContext() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        return context.getApplication().evaluateExpressionGet(context, "#{sessionContext}", SessionContext.class);
//    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    
    
    
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
