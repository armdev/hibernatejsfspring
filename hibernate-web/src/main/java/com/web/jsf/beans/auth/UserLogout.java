package com.web.jsf.beans.auth;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Armen Arzumanyan
 */
@ManagedBean(name = "userLogout")
@RequestScoped
public class UserLogout implements Serializable {

    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public UserLogout() {
    }

    public String doLogout() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
      //  externalContext.getSessionMap().remove("userContext");
        externalContext.getSessionMap().remove("sessionContext");
        externalContext.getSessionMap().remove("terminalBean");
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "logout";
    }
}
