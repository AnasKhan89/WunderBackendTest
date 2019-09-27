package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MySQLJDBCUtility {

    private static final Logger logger = LogManager.getLogger(MySQLJDBCUtility.class);
    private Connection conn = null;
    private ResultSet currentRS = null;
    private String DbURL = new PropertyReader().readProperty("DbURL");
    private String DbUsername = new PropertyReader().readProperty("DbUsername");
    private String DbPassword = new PropertyReader().readProperty("DbPassword");


    private void initConnection(String DbURL, String DbUsername, String DbPassword) {
        // Create connection
        logger.info("Database Credentials: " + DbURL + DbPassword);
        try {

            conn = DriverManager.getConnection(DbURL, DbUsername, DbPassword);
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            logger.error("Coult not Initiate Connection to the Database. Please refer to the trace below: ");
            e.printStackTrace();

        }
    }

    public MySQLJDBCUtility() {
        PropertyReader properties = new PropertyReader();

        //QATestProperties qaTestProperties = script.getQATestProperties();
        this.initConnection(DbURL, DbUsername, DbPassword);

    }

    public Connection getConn() {
        return conn;
    }

    public ResultSet execute(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            currentRS = rs;
        } catch (SQLException e) {
            logger.error("ERROR WHILE EXECUTING QUERY - PLEASE CHECK CONNECTION AND QUERY");
            currentRS = null;
        }

        return rs;
    }

    public boolean executeUpdate(String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public void closeConnection() throws SQLException {

        conn.close();

    }


}
