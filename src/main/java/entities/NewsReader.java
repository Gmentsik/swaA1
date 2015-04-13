package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;



@Entity(name="newsReader")
public class NewsReader extends User implements Serializable {
    @Column(columnDefinition="LONGVARCHAR")
    ArrayList<Topic> subsribedTopics = new ArrayList<Topic>();

    public ArrayList<Topic> getSubsribedTopics() {
        return subsribedTopics;
    }

    public void setSubsribedTopics(ArrayList<Topic> subsribedTopics) {
        this.subsribedTopics = subsribedTopics;
    }

}
