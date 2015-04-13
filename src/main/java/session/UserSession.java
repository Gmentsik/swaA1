package session;

import entities.User;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@SessionScoped
@Named(value="userSession")
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
