import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Created by Gergely on 27.03.2015.
 */
@ManagedBean
@SessionScoped
public class hibernateTest {
    //@PersistenceContext(unitName = "myUnit", type = PersistenceContextType.EXTENDED)
   // private EntityManager em;
    //private EntityTransaction etr;
    //private EntityManagerFactory entityManagerFactory;

    public String testH(){
        return "a";
    }
}
