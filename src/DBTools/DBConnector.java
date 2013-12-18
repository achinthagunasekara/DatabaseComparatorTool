package DBTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Archie Gunasekara
 * 2013.07.02
 */
public class DBConnector {
 
    private String dbUrl;
    private String server;
    private String port;
    private String user;
    private String pass;
    private String dbClass;
    
    public DBConnector(ConnectionTypes dbServerType, String server, String port, String user, String pass) throws Exception {
	
	this.server = server;
	this.port = port;
	this.user = user;
	this.pass = pass;
	
	if(dbServerType == ConnectionTypes.SYBASE) {
	    
	    dbClass = "com.sybase.jdbc4.jdbc.SybDriver";
	    dbUrl = "jdbc:sybase:Tds:" + server + ":" + port;
	}
	else {
	    
	    throw new Exception("Database server type is not supported!");
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
	
	dbUrl = "jdbc:sybase:Tds:" + server + ":" + port + "/" + database;
	return DriverManager.getConnection(dbUrl, user, pass);
    }
}