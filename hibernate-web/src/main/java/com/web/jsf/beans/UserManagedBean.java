package com.web.jsf.beans;

import com.project.entities.User;
import com.project.services.UserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "userManagedBean")
@ViewScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty("#{userService}")
    private UserService userService;
    private Long userCount;
    @ManagedProperty("#{i18n}")
    private ResourceBundle bundle = null;
    private User user = new User();

    public UserManagedBean() {

    }

    @PostConstruct
    public void init() {
        user = new User();
    }

    public Long getUserCount() {
        userCount = userService.getUsersCount();
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public List<User> getUserList() {
        List<User> userList = userService.getUserList(0, 1000);
        return userList;
    }

    public String saveUser() {
        User u = userService.getUserByEmail(user.getEmail());
        if (u != null) {
            //  System.out.println("User is not null, return  ");
            FacesMessage msg = new FacesMessage(bundle.getString("emailbusy"), bundle.getString("emailbusy"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else {
            userService.saveUser(user);
            return "userlist";
        }
        //return null;
    }

    public String updateUser() {
        if (user != null) {
            userService.updateUser(user);
        }
        return "userlist?faces-redirect=true";
    }

    public String deleteUser(Long id) {
        if (id != null) {
            userService.deleteUser(id);
        }
        return "userlist";
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
