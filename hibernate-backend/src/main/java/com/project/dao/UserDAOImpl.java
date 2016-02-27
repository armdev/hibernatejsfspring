package com.project.dao;

import com.project.entities.User;
import com.project.utils.CommonUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Home
 */
@Component
@Repository("userDAO")
public class UserDAOImpl extends AbstractDao implements UserDAO {

    public UserDAOImpl() {
        System.out.println("user Service INIT ");
    }

    @Override
    public Long getUsersCount() {
        Long count = 0L;
        try {
            Query query = getSession().createQuery("SELECT count(*) FROM User");
            count = (Long) query.uniqueResult();

        } catch (Exception e) {
            return null;
        }
        return count;
    }

    @Override
    public List<User> getUserList(Integer start, Integer max) {
        List<User> finalList = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.id > 0 ORDER BY c.id DESC");
            if (start != null) {
                query.setFirstResult(start);
            }
            if (max != null) {
                query.setMaxResults(max);
            }
            finalList = query.list();

            if (finalList == null) {
                return null;
            }
            //   System.out.println("finalList " + finalList.size());

        } catch (Exception e) {
        }

        return finalList;
    }

    @Override
    public Long saveUser(User user) {
        Long id = 0L;
        try {
            String hashedPasswd = CommonUtils.hashPassword(user.getPassword());
            user.setPassword(hashedPasswd);
            user.setRegisteredDate(new Date(System.currentTimeMillis()));
            user.setUsername(user.getEmail());
            getSession().save(user);
            getSession().flush();
            id = user.getId();
        } catch (Exception e) {

        }
        return id;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id != null) {
            User user = this.getUserById(id);
            if (user != null) {
                getSession().delete(user);
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        if (user != null) {
            getSession().update(user);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(Long id) {
        System.out.println("received id " + id );
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.id=:id").setParameter("id", id);
            entity = (User) query.uniqueResult();
            System.out.println("entity " + entity );
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return entity;
    }

    @Override
    public User getUserByEmail(String email) {
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email").setParameter("email", email);
            entity = (User) query.uniqueResult();

            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return entity;
    }

    @Override
    public boolean checkUserEmailForUpdate(Long id, String email) {
        boolean retValue = false;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email and c.id != :id").setParameter("email", email).setParameter("id", id);
            User user = (User) query.uniqueResult();
            if (user != null) {
                retValue = true;
            }
        } catch (Exception e) {
        }
        return retValue;
    }

    @Override
    public User loadUser(Long id) {
        User entity = null;
        try {
            entity = (User) getSession().load(User.class, id);//return proxy        
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return entity;
    }

    @Override
    public User getUser(Long id) {
        User entity = null;
        try {
            entity = (User) getSession().get(User.class, id);//get user from database
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return entity;
    }

    @Override
    public User userLogin(String email, String password) {

        User user = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email and c.password=:password")
                    .setParameter("email", email).setParameter("password", CommonUtils.hashPassword(password));
            user = (User) query.uniqueResult();

        } catch (Exception e) {
        }
        return user;
    }

}
