package controller.databaseaccess;

import org.apache.log4j.Logger;

/**
 * Created by ivand on 22.03.2018.
 */
public class DBUtilities {

    private final static Logger log = Logger.getLogger(DBUtilities.class);

    private static final String DATABASE_URL = Authorisation.getUrl();
    private static final String USER_NAME = Authorisation.getUser();
    private static final String PASSWORD = Authorisation.getPassword();


    public static String getDatabaseUrl() {
        log.info("DATABASE_URL: " + DATABASE_URL);
        return DATABASE_URL;
    }

    public static String getUserName() {
        log.info("USER_NAME: " + USER_NAME);
        return USER_NAME;
    }

    public static String getPassword() {
        log.info("PASSWORD: " + PASSWORD);
        return PASSWORD;
    }
}