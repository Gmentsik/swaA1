package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


@Entity(name="newsItem")
@NamedQuery(name = "findAllNewsItems", query = "SELECT e FROM newsItem e")
public class NewsItem implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;
    @Column(columnDefinition="LONGVARCHAR")
    private String content;
    private String title;
    @Column(columnDefinition="LONGVARCHAR")
    private ArrayList<String> keywords = new ArrayList<>();

    @Column(columnDefinition="LONGVARCHAR")
    private ArrayList<Topic> assTopics = new ArrayList<>();

    @Temporal(TemporalType.DATE)      //signifies a date attribute
    private Date dateOfCreation;

    @Column(columnDefinition="LONGVARCHAR")
    private NewsWriter owner;

    @Column(columnDefinition="LONGVARCHAR")
    private ArrayList<NewsReader> readBy = new ArrayList<>();

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<Topic> getAssTopics() {
        return assTopics;
    }

    public void setAssTopics(ArrayList<Topic> assTopics) {
        this.assTopics = assTopics;
    }

    public NewsWriter getOwner() {
        return owner;
    }

    public void setOwner(NewsWriter owner) {
        this.owner = owner;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public ArrayList<NewsReader> getReadBy() {
        return readBy;
    }

    public void setReadBy(ArrayList<NewsReader> readBy) {
        this.readBy = readBy;
    }
}
