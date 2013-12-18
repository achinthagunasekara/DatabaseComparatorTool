package DBTools;

import java.util.ArrayList;

/**
 * @author Archie Gunasekara
 * 2013.07.05
 */
public class SqlStatementGenerator {
    
    private ArrayList<String> columnNames;
    private ArrayList<String[]> data;
    private String tableName;
    private StringBuilder sb;
    
    public SqlStatementGenerator(ArrayList<String> columnNames, ArrayList<String[]> data, String tableName) {
	
	this.columnNames = columnNames;
	this.data = data;
	this.tableName = tableName;
	generateSql();
    }
    
    public String getSql() {
	
	return sb.toString();
    }
    
    private void generateSql() {
	
	sb = new StringBuilder();
	
	for(String[] arr : data) {
	    
	    sb.append("INSERT INTO ");
	    sb.append(tableName);
	    sb.append(" \n(");
	    sb.append(getSqlCols());
	    sb.append(")\n");
	    sb.append(" VALUES(");
	    sb.append(getSqlVals(arr));
	    sb.append(")");
	    sb.append(("\n\n"));
	}
    }
    
    private String getSqlCols() {
	
	StringBuilder sb = new StringBuilder();
	
	for(int i = 0; i < columnNames.size(); i++) {
	    
	    sb.append(columnNames.get(i));
	    
	    if(i < (columnNames.size() - 1)) {
		
		sb.append(", ");
	    }
	}
	
	return sb.toString();
    }
    
    private String getSqlVals(String[] arr) {
	
	StringBuilder sb = new StringBuilder();
	
	for(int i = 0; i < arr.length; i++) {
	    
	    sb.append("'");
	    sb.append(arr[i]);
	    sb.append("'");
	    
	    if(i < (arr.length - 1)) {
		
		sb.append(", ");
	    }
	}
	
	return sb.toString();
    }
}