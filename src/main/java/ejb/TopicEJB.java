package ejb;

import entities.Topic;
import javafx.beans.Observable;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class TopicEJB{
    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    public Topic addTopic(Topic iTopic){
        try{
            entityManager.persist(iTopic);
        }catch(EntityExistsException e){
            System.out.println("User alreader exists: " + e.toString());
        }catch(TransactionRequiredException e){
            System.out.println("There is no transaction: " + e.toString());
        }
        return iTopic;
    }

    public boolean deleteTopic(Topic iTopic){

        return false;
    }

    public Topic updateTopic(Topic iTopic){
        try{
            entityManager.merge(iTopic);
        }catch(TransactionRequiredException e){
            System.out.println("Transaction required: " + e.toString());
            return iTopic;
        }
        return iTopic;
    }

    public Topic getTopicByTitle(String title){
        List<Topic> topics = new ArrayList<>();
        topics = getTopics();
        for(Topic topic: topics){
            if(topic.getTitle().equals(title)){
                return topic;
            }
        }
        return null;
    }

    public List<Topic> getTopics(){
        TypedQuery<Topic> query = entityManager.createNamedQuery("findAllTopics",Topic.class);
        return query.getResultList();
    }
}
