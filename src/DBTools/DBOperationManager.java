package DBTools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Archie Gunasekara
 * 2013.07.03
 */
public class DBOperationManager {
    
    private Connection con;
    
    public DBOperationManager(Connection con) {
	
	this.con = con;
    }
    
    public ArrayList<String> getTablesList() throws SQLException {
	
	ArrayList<String> list = new ArrayList<String>();
	DatabaseMetaData md = con.getMetaData();
	
	ResultSet rs = md.getTables(null, null, "%", null);
	
	while (rs.next()) {
	    
	    list.add(rs.getString(3));
	}
	
	return list;
    }
    
    public void close() throws SQLException {
	
	con.close();
    }
    
    public HashMap<String, ArrayList> getTableInfo(String tableName) throws SQLException {
	
	HashMap<String, ArrayList> tableInfo = new HashMap<String, ArrayList>();
	ArrayList<String> colNames = new ArrayList<String>();
	ArrayList<String> colTypes = new ArrayList<String>();
	tableInfo.put("COL_NAMES", colNames);
	tableInfo.put("COL_TYPES", colTypes);
	
	Statement st = con.createStatement();
	ResultSet rsColumns = null;
	DatabaseMetaData meta = con.getMetaData();
	rsColumns = meta.getColumns(null, null, tableName, null);
	
	while (rsColumns.next()) {
	    
	    colNames.add(rsColumns.getString("COLUMN_NAME"));
	    colTypes.add(rsColumns.getString("TYPE_NAME"));
	}
	
	st.close();
	return tableInfo;
    }
    
    public ArrayList<String[]> getAllDataFromTable(String tableName, int colCount, String wherePart) throws SQLException {
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String query = "SELECT * FROM " + tableName + " WHERE " + wherePart;
	PreparedStatement statement = con.prepareStatement(query);
	ResultSet rs = (ResultSet) statement.executeQuery();
	
	while(rs.next()) {
	    
	    String[] s = new String[colCount];
	    
	    for(int i = 0; i < colCount; i++) {
		
		s[i] = rs.getString(i+1);
	    }
	    
	    list.add(s);
	}
	
	return list;
    }
}
