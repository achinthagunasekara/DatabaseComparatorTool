package DBTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Archie Gunasekara
 * 2013.07.02
 */
public class DBConnector {
 
    private String dbUrl;
    private final String server;
    private final String port;
    private final String user;
    private final String pass;
    private String dbClass;
    private final ConnectionTypes dbServerType;
    
    public DBConnector(ConnectionTypes dbServerType, String server, String port, String user, String pass) throws Exception {
	
	this.server = server;
	this.port = port;
	this.user = user;
	this.pass = pass;
        this.dbServerType = dbServerType;
	
	if(dbServerType == ConnectionTypes.SYBASE) {
	    
	    dbClass = "com.sybase.jdbc4.jdbc.SybDriver";
	    dbUrl = "jdbc:sybase:Tds:" + server + ":" + port;
	}
        else if(dbServerType == ConnectionTypes.MicrosoftSQLServer) {
            
            dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            dbUrl = "jdbc:sqlserver://" + server + ":" + port;
        }
	else {
	    
	    throw new Exception("Database server type is not supported! - " + dbServerType);
	}
	
	Class.forName(dbClass).newInstance();
    }
    
    public ArrayList<String> getDatabases() throws SQLException {
	
	ArrayList<String> list = new ArrayList<>();
	
	Connection con = DriverManager.getConnection(dbUrl, user, pass);
	ResultSet rs = con.getMetaData().getCatalogs();
	
	while (rs.next()) {
	    
	    list.add(rs.getString("TABLE_CAT"));
	}
	
	con.close();
	return list;
    }
    
    public Connection createDbConnection(String database) throws SQLException {
	
        if(dbServerType == ConnectionTypes.SYBASE) {
	
            dbUrl = "jdbc:sybase:Tds:" + server + ":" + port + "/" + database;
        }
        else if(dbServerType == ConnectionTypes.MicrosoftSQLServer) {
            
            dbUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";";
        }
        else {
	    
	    throw new SQLException("Database server type is not supported! - " + dbServerType);
	}
        
	return DriverManager.getConnection(dbUrl, user, pass);
    }
}