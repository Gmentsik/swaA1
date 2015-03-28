import db.entities.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Date;


/**
 * Created by Gergely on 27.03.2015.
 */
@ManagedBean
@SessionScoped
public class hibernateTest {
    @PersistenceContext(unitName = "myUnit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    private EntityTransaction etr;
    //private EntityManagerFactory entityManagerFactory;

    public String testH(){
        //EntityManager em = entityManagerFactory.createEntityManager();
        user myUser = new user();

        myUser.setBirth(new Date(2014, 02, 03));
        myUser.setId((long) 1);
        myUser.setEmail("blah@blah.ba");
        myUser.setlName("Mentsik");
        myUser.setfName("Gergely");

        etr = em.getTransaction();
        etr.begin();
        etr.commit();
        return myUser.getfName();

    }
}
