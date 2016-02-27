package com.web.jsf.handlers;

import com.project.entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Armen Arzumanyan
 */
@ManagedBean(name = "sessionContext")
@SessionScoped
public class SessionContext implements Serializable {

    private FacesContext context;
    private ExternalContext ex;
    private User user;

    public SessionContext() {
        context = FacesContext.getCurrentInstance();
        ex = context.getExternalContext();
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
