package db.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Gergely on 25.03.2015.
 */
@Entity
public class user {

    @Id
    private Long id;
    private String fName;
    private String lName;
    private Date birth;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
