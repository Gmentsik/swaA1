package ejb;

import entities.NewsItem;
import entities.NewsReader;
import entities.NewsWriter;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class NewsEJB {
    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    public NewsItem saveNewsItem(NewsItem nItem) {
        return nItem;
    }

    public NewsItem deleteNewsItem(NewsItem nItem) {
        return nItem;
    }

    public boolean readBy(NewsReader iReader, NewsItem nItem){
        return nItem.getReadBy().contains(iReader);
    }

    public NewsItem updateNewsItem(NewsItem nItem) {
        try{
            entityManager.merge(nItem);
        }catch(TransactionRequiredException e){
            System.out.println("Transaction required: " + e.toString());
            return nItem;
        }
        return nItem;
    }

    public NewsItem addNewsItem(NewsItem nItem) {
        try{
            entityManager.persist(nItem);
        }catch(EntityExistsException e){
            System.out.println("NewsItem alreader exists: " + e.toString());
        }catch(TransactionRequiredException e){
            System.out.println("There is no transaction: " + e.toString());
        }
        return nItem;
    }

    public NewsItem getNewsItemByTitle(String title){
        for(NewsItem newsItem: getNewsItems()){
            if(newsItem.getTitle().equals(title)){
                return newsItem;
            }
        }
        return null;
    }

    public List<NewsItem> getNewsItems() {
        TypedQuery<NewsItem> query = entityManager.createNamedQuery("findAllNewsItems", NewsItem.class);
        return query.getResultList();
    }

    public NewsItem getNewsItemByID(long nID)
    {
        ArrayList<NewsItem> newsItems = (ArrayList<NewsItem>) getNewsItems();
        for(NewsItem newsItem: newsItems){
            if(newsItem.getID() == nID ){
                return newsItem;
            }
        }
        return null;
    }

}
