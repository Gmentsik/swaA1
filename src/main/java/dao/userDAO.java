package dao;

import db.entities.user;

/**
 * Created by Gergely on 25.03.2015.
 */
public interface userDAO {
    boolean saveUser(user iUser);
    boolean deleteUser(user iUser);
    boolean updateUser(user iUser);
    user getUser();
}
