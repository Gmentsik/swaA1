package controller;

import ejb.NewsEJB;
import ejb.TopicEJB;
import ejb.UserEJB;
import entities.NewsItem;
import entities.NewsReader;
import entities.NewsWriter;
import entities.Topic;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Date;


@ManagedBean(name = "newsController")
public class NewsController {
    @EJB
    private NewsEJB newsEJB = new NewsEJB();
    @EJB
    private TopicEJB topicEJB = new TopicEJB();
    @EJB
    private UserEJB userEJB = new UserEJB();

    private boolean inited = true;

    private String newTopicTitle;
    private String[] assignedTopics;

    private String title;
    private String content;

    public String addNews(long uID){
        NewsItem freshNews = new NewsItem();
        NewsWriter publisher = userEJB.getWriterByID(uID);

        System.out.println("Adding freshNews!");

        freshNews.setTitle(this.title);
        freshNews.setContent(this.content);
        freshNews.setOwner(publisher);
        freshNews.setDateOfCreation(new Date());

        for(String topicTitle: assignedTopics){
            freshNews.getAssTopics().add(topicEJB.getTopicByTitle(topicTitle));
            System.out.println("Adding freshNews to Topic: " + topicTitle);
        }

        for(String topicTitle: assignedTopics){
            Topic iTopic = topicEJB.getTopicByTitle(topicTitle);
            if(iTopic != null){
                iTopic.getNewsItems().add(freshNews);
                topicEJB.updateTopic(iTopic);
            }
        }

        System.out.println("Adding freshNews to publishers Feed!");

        publisher.getPubNews().add( newsEJB.getNewsItemByTitle(this.title));
        userEJB.updateUser(publisher);

        System.out.println("User is: " + publisher.getUsrName());
        System.out.println("His feed: " + publisher.getPubNews().toString());
        System.out.println("Finished, redirecting to preview!");
        return "index.xhtml";
    }


    public void init() {
        if(topicEJB.getTopicByTitle("Global Topic") == null){
            System.out.println("Init...");
            NewsItem firstNews = new NewsItem();

            Topic globalTopic = new Topic();
            Topic topic1 = new Topic();
            Topic topic2 = new Topic();
            Topic topic3 = new Topic();
            Topic topic4 = new Topic();

            topic1.setTitle("Topic 1");
            topic2.setTitle("Topic 2");
            topic3.setTitle("Topic 3");
            topic4.setTitle("Topic 4");

            NewsReader initReader = new NewsReader();
            NewsWriter initWriter = new NewsWriter();

            initReader.setUsrName("testreader");
            initReader.setpWord("A1test");

            initWriter.setUsrName("testwriter");
            initWriter.setpWord("A1test");

            globalTopic.setTitle("Global Topic");
            globalTopic.getNewsItems().add(firstNews);

            firstNews.setTitle("Welcome");
            firstNews.setContent("Welcome to SWA! This is <b>Your initial News Item in bold!</b>. <br/> This News Item is assigned to Topic <b>GLOBAL</b>");
            firstNews.getAssTopics().add(globalTopic);
            firstNews.setOwner(initWriter);
            firstNews.setDateOfCreation(new Date());

            initWriter.getPubNews().add(firstNews);
            initReader.getSubsribedTopics().add(globalTopic);

            //newsEJB.addNewsItem(firstNews);
            topicEJB.addTopic(globalTopic);
            topicEJB.addTopic(topic1);
            topicEJB.addTopic(topic2);
            topicEJB.addTopic(topic3);
            topicEJB.addTopic(topic4);

            userEJB.addUser(initWriter);
            userEJB.addUser(initReader);

        }
    }

    public void addNewTopic(){
        String result;
        Topic newTopic = null;
        newTopic = topicEJB.getTopicByTitle(this.newTopicTitle);
        if(newTopic == null){
            newTopic = new Topic();
            newTopic.setTitle(this.newTopicTitle);
            topicEJB.addTopic(newTopic);

            result = "Topic added!";
        }else{
            result = "Topic already exitsts!";
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result,  null);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }



    public String[] getAssignedTopics() {
        return assignedTopics;
    }

    public void setAssignedTopics(String[] assignedTopics) {
        this.assignedTopics = assignedTopics;
    }



    public String getNewTopicTitle() {
        return newTopicTitle;
    }

    public void setNewTopicTitle(String newTopicTitle) {
        this.newTopicTitle = newTopicTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
