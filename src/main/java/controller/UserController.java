package controller;

import ejb.NewsEJB;
import ejb.TopicEJB;
import ejb.UserEJB;
import entities.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

    @EJB
    private UserEJB userEJB = new UserEJB();

    @EJB
    private TopicEJB topicEJB = new TopicEJB();

    @EJB
    private NewsEJB newsEJB = new NewsEJB();

    private NewsReader newsReader = new NewsReader();
    private NewsWriter newsWriter = new NewsWriter();
    private User newUser = new User();
    private User currentUser = null;

    private String usrName;
    private String pWord;
    private ArrayList<NewsItem> newsFeed = new ArrayList<>();

    private List<NewsReader> newsReaderList = new ArrayList<>();
    private List<NewsWriter> newsWriterList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    public boolean isNewsReader(){
        return (currentUser instanceof NewsReader);
    }

    public boolean isNewsWriter(){
        return (currentUser instanceof NewsWriter);
    }

    public boolean loggedIn(){
        return currentUser != null;
    }

    public String login(){
        currentUser = userEJB.getUser(usrName,pWord);

        if(currentUser == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or Password wrong!"));
            return (usrName = pWord = null);
        }else{
            return "index";
        }
    }

    public ArrayList<NewsItem> getNewsFeed() {
        this.newsFeed = new ArrayList<>();
        if(currentUser instanceof NewsWriter){
            System.out.println("get News Writer Feed ID: " + currentUser.getId().toString());
            this.newsFeed = userEJB.getWriterByID(getCurrentUserID()).getPubNews();
            return this.newsFeed;
        }else if(currentUser instanceof NewsReader){
            System.out.println("get News Reader Feed ID: " + currentUser.getId().toString());
            List<Topic> userTopic = ((NewsReader) userEJB.getUserByID(getCurrentUserID())).getSubsribedTopics();
            for(Topic iTopic: userTopic){
                iTopic = topicEJB.getTopicByTitle(iTopic.getTitle());
                System.out.println("Topic: " + iTopic.getNewsItems());
                this.newsFeed.addAll(iTopic.getNewsItems().stream().collect(Collectors.toList()));
                newsFeed.sort(new Comparator<NewsItem>() {
                    @Override
                    public int compare(NewsItem o1, NewsItem o2) {
                        return o1.getDateOfCreation().compareTo(o2.getDateOfCreation());
                    }
                });
            }
            return this.newsFeed;
        }
        return null;
    }

    public void setNewsFeed(ArrayList<NewsItem> newsFeed) {
        this.newsFeed = newsFeed;
    }

    public String getUserType(User iUser){
        if(iUser instanceof NewsReader){
            return "Reader";
        }else if(iUser instanceof NewsWriter){
            return "Writer";
        }else{
            return "User";
        }
    }

    public long getCurrentUserID(){
       return currentUser.getId();
    }

    public User updateUser(User usr){
        return userEJB.updateUser(usr);
    }

    public String addNewsReader(){
        newsReader.setId(newUser.getId());
        newsReader.setUsrName(newUser.getUsrName());
        newsReader.setpWord(newUser.getpWord());

        newsReader = (NewsReader) userEJB.addUser(newsReader);
        userList = userEJB.getUsers();
        return "userList.xhtml";
    }

    public String addNewsWriter(){
        newsWriter.setId(newUser.getId());
        newsWriter.setUsrName(newUser.getUsrName());
        newsWriter.setpWord(newUser.getpWord());
        newsWriter = (NewsWriter) userEJB.addUser(newsWriter);
        userList = userEJB.getUsers();
        return "userList.xhtml";
    }

    public NewsReader getNewsReader() {
        return newsReader;
    }

    public void setNewsReader(NewsReader newsReader) {
        this.newsReader = newsReader;
    }

    public NewsWriter getNewsWriter() {
        return newsWriter;
    }

    public void setNewsWriter(NewsWriter newsWriter) {
        this.newsWriter = newsWriter;
    }

    public List<NewsReader> getNewsReaderList() {
        return newsReaderList;
    }

    public void setNewsReaderList(ArrayList<NewsReader> newsReaderList) {
        this.newsReaderList = newsReaderList;
    }

    public List<NewsWriter> getNewsWriterList() {
        return newsWriterList;
    }

    public void setNewsWriterList(ArrayList<NewsWriter> newsWriterList) {
        this.newsWriterList = newsWriterList;
    }

    public List<User> getUserList() {
        userList = userEJB.getUsers();
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public User getCurrentUser() {
        return newUser;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getpWord() {
        return pWord;
    }

    public void setpWord(String pWord) {
        this.pWord = pWord;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
}
