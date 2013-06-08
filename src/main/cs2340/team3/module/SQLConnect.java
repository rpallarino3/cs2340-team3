package cs2340.team3.module;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * This class is to hold the database name and connection method
 * @author khanhnguyen
 */
public class SQLConnect {
    //THE MAIN STABLE INSTANCE VARS
    private String host; //name of the host
    private String dbname; //name of database
    private String user; //name of the user
    private String password; //name of password
    //following are default value
    private String driver;
    private String url;
    private boolean connected = false;
    
    //THE FOLLOWING DEPENDENCE ON THE CONNECTION METHOD
    private Connection conn; //connection
    private Statement stm; //query intepreter
    private PreparedStatement pstm; //safer query intepreter
    private ResultSet rs; //container for return results
    
    public SQLConnect(String host, String dbname, String user, String password) {
        this.host = host;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://"+host+"/"+dbname+"?characterEncoding=UTF-8&useUnicode=true";
        conn = null;
        stm = null;
        pstm = null;
        rs = null;
    }
    /**
     * This method will do the connection based on the current connection method
     * 
     * @return Return a boolean value to see if the connection was successful
     */
    public boolean connect() {
        try {
            Class.forName(driver);
            System.out.println("Driver loaded");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            conn.setAutoCommit(false); //we don't get penalized for messing up :P
            stm = conn.createStatement();
            connected = true;
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Error caught in DBConnect connect");
            return false;
        } catch (ExceptionInInitializerError eiie) {
            System.out.println(eiie);
            eiie.printStackTrace();
            return false;
        }
    }
    
    /**
     * A call to disconnect
     * 
     * @return Return a boolean for successful disconnect
     */
    public boolean disconnect() {
        try {
            conn.close();
            stm.close();
            pstm.close();
            System.out.println("Disconnect successful!");
            connected = false;
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Commit
     */
    public void commit() {
        try {
            conn.commit();
        } catch(Exception e) {
            System.err.println("Failed to commit: "+ e);
        }
    }
    
    /**
     * Rollback
     */
    public void rollback() {
        try {
            conn.rollback();
        } catch(Exception e) {
            System.err.println("Failed to rollback: "+ e);
        }
    } 
    
    /**
     * Run the query and put the result in resultset
     * 
     * @return Return true if query executed successfully
     */
    public boolean get(String query) {
        try {
//            pstm = conn.prepareStatement(query);
//            rs = pstm.executeQuery();
            stm.execute(query);
            conn.commit();
            rs = stm.getResultSet();
            return true;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Simply run a query without getting results
     * 
     * @return Return true if query executed successfully
     */
    public boolean run(String query) {
        try {
//            pstm = conn.prepareStatement(query);
//            pstm.clearBatch();
//            pstm.addBatch(query);
//            pstm.executeBatch();
            stm.execute(query, Statement.RETURN_GENERATED_KEYS);
            conn.commit();
            return true;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }      
    }
    
    private void addToBatch(String query) {
        try {
            stm.addBatch(query);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    private boolean runBatch() {
        try {
            stm.executeBatch();
            conn.commit();
            return true;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    } 
    
    /**
     * Use the Statement (insecure) to run a batch SQL commands.
     * @param query The batch SQL commands, each command is separated by a semicolon (;)
     * @return true if successfully ran, false otherwise
     */
    public boolean runMulti(String query) {
        if(query.indexOf(";")>5) {
            parseAndAdd(query);
            return runBatch();
        } else { return false; }
    }
    
    private void parseAndAdd(String query) {
        int i = -1, j;
        do {
            j=i;
            i = query.indexOf(";",i+1);
            addToBatch(query.substring(j+1, i));
        } while(query.indexOf(";",i+1)!=-1);
    }
    
    /**
     * Create a PreparedStatement (secure) using the current SQL connection.
     * @param query The command to be passed to the SQL to create a PreparedStatement that will be secure and faster.
     * @return PreparedStatement object of the query.
     */
    public PreparedStatement createPreparedStatement(String query) {
        if(connected) {
            try {
                return conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            } catch(Exception e) { return null; }
        }
        else {
            return null;
        }
    }
    
    /**
     * Get the REFERECE to the ResultSet used by this SQLConnect object.
     * @return ResultSet REFERENCE of the one used by the current SQLConnect object. This means that it's not a copy of the ResultSet, but actual thing.
     */
    public ResultSet getResult() {
        return rs;
    }
    
    /**
     * Return a ResultSet containing the keys generated by SQL commands that can return keys.
     * @return ResultSet contain keys generated by previous run() or get() methods.
     */
    public ResultSet getGeneratedKeys() {
        try {
            return stm.getGeneratedKeys();
        } catch (Exception e) {
            System.err.println("Cannot get generated keys");
            return null;
        }
    }
}
