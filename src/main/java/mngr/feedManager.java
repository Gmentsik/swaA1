package mngr;

/**
 * Created by Gergely on 28.03.2015.
 */
public class feedManager {
    private static feedManager ourInstance = new feedManager();

    public static feedManager getInstance() {
        return ourInstance;
    }

    private feedManager() {
    }
}
