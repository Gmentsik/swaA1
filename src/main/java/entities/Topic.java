package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="topic")
@NamedQuery(name = "findAllTopics", query = "SELECT e FROM topic e")
public class Topic implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;

    private String title;
    @Column(columnDefinition="LONGVARCHAR")
    private ArrayList<String> keywords = new ArrayList<>();

    @Column(columnDefinition="LONGVARCHAR")
    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<NewsItem> newsItems = new ArrayList<>();

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(ArrayList<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
