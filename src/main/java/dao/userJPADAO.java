package dao;

import db.entities.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Gergely on 25.03.2015.
 */
public class userJPADAO implements userDAO {
    @PersistenceContext(unitName = "myUnit")
    private EntityManager em;

    @Override
    public boolean saveUser(user iUser) {
        em.persist(iUser);
        return true;
    }

    @Override
    public boolean deleteUser(user iUser) {
        return false;
    }

    @Override
    public boolean updateUser(user iUser) {
        return false;
    }

    @Override
    public user getUser() {
        return null;
    }
}
