package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;


@Entity(name="newsWriter")
public class NewsWriter extends User implements Serializable {
    @Column(columnDefinition="LONGVARCHAR")
    ArrayList<NewsItem> pubNews = new ArrayList<>();

    public ArrayList<NewsItem> getPubNews() {
        return pubNews;
    }

    public void setPubNews(ArrayList<NewsItem> pubNews) {
        this.pubNews = pubNews;
    }
}
