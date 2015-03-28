package mngr;

import dao.userDAO;
import dao.userJPADAO;
import db.entities.user;

/**
 * Created by Gergely on 28.03.2015.
 */

public class userManager {

    private userDAO uDAO = new userJPADAO();

    private static userManager ourInstance = new userManager();
    public static userManager getInstance() {
        return ourInstance;
    }

    private userManager(){ }

    public boolean addUser(user iUser){
        return uDAO.saveUser(iUser);
    }

    public String init(){
        return "success";
    }
}
