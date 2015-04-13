package ejb;

import entities.NewsReader;
import entities.NewsWriter;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class UserEJB{
    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;




    public User saveUser(User iUser) {
        return iUser;
    }

    public User deleteUser(User iUser) {
        return iUser;
    }

    public User updateUser(User iUser) {
        try{
            entityManager.merge(iUser);
        }catch(TransactionRequiredException e){
            System.out.println("Transaction required: " + e.toString());
            return iUser;
        }
        return iUser;
    }

    public User addUser(User iUser) {
        try{
            entityManager.persist(iUser);
        }catch(EntityExistsException e){
            System.out.println("User alreader exists: " + e.toString());
        }catch(TransactionRequiredException e){
            System.out.println("There is no transaction: " + e.toString());
        }
        return iUser;
    }

    public ArrayList<NewsReader> getNewsReaders() {
        return null;
    }
    public ArrayList<NewsWriter> getNewsWriters() {
        return null;
    }


    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createNamedQuery("findAllUsers", User.class);
        return query.getResultList();
    }


    public NewsReader getNewsReader() {
        return null;
    }

    public NewsWriter getNewsWriter() {
        return null;
    }

    public User getUser(String uName, String uPWord) {
        for(User usr: this.getUsers()){
            if(uName.equals(usr.getUsrName())){
                if(uPWord.equals(usr.getpWord())){
                    System.out.println("H2.getUser("+uName+","+uPWord+") = " + usr);
                    return usr;
                }
            }
        }
        System.out.println("H2.getUser("+uName+","+uPWord+") = null");
        return null;
    }

    public User getUserByID(long id){
        for(User usr: this.getUsers()){
            if(usr.getId() == id){
                return usr;
            }
        }
        System.out.println("H2.getUser() = null");
        return null;
    }
    public NewsWriter getWriterByID(long id){
        for(User usr: this.getUsers()){
            if((usr instanceof NewsWriter) && (usr.getId() == id)){
                return (NewsWriter) usr;
            }
        }
        System.out.println("H2.getWriterByID() = null");
        return null;
    }

}
