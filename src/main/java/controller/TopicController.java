package controller;

import ejb.TopicEJB;
import entities.Topic;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name="topic")
@SessionScoped
public class TopicController {
    @EJB
    private TopicEJB topicEJB = new TopicEJB();

    private String topicTitle;
    private String[] assignTags;

    public String addTopic(){
        Topic newTopic = new Topic();
        newTopic.setTitle(this.topicTitle);
        topicEJB.addTopic(newTopic);
        return "";
    }



    public List<Topic> getTopics(){
        return topicEJB.getTopics();
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {

        this.topicTitle = topicTitle;
    }

    public String[] getAssignTags() {
        return assignTags;
    }

    public void setAssignTags(String[] assignTags) {
        for(String title: assignTags){

        }
        this.assignTags = assignTags;
    }
}
