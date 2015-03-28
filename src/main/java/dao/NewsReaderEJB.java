package dao;

import db.entities.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Gergely on 28.03.2015.
 */
@Stateless
public class NewsReaderEJB {
    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    public List<user> findNewsReaders(){
        TypedQuery<user> query = entityManager.createNamedQuery("findAllUsers", user.class);
        return query.getResultList();
    }

    public user addNew(user employee) {
        entityManager.persist(employee);
        return employee;
    }
}
