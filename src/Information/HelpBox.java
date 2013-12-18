package Information;

import GUI.TextDisplayFrame;

/**
 * @author Archie Gunasekara
 * 2013.07.02
 */
public class HelpBox {
    
    public HelpBox() { }
    
    public void showHelp() {
	
	TextDisplayFrame tDisplay = new TextDisplayFrame("Help", getHelp());
	tDisplay.setVisible(true);
    }
    
    private String getHelp() {
	
	StringBuilder sb = new StringBuilder();
	
	sb.append("This application can be used to compare data on two tables. Currently only Sybase database is supported. However by loading JDBC drivers, this application is compatible with any database server.");
	sb.append("\n\n");
	sb.append("First, enter with your credentials. If credentials are valid for a server, it will display a list of databases available on the server. Select a database and click “Connect” to connect to it. You must connect to both databases you wish to compare before continuing.");
	sb.append("\n\n");
	sb.append("Then the application will display a list of tables on the database. Select a table and fields list will be updated automatically. Select a field and enter a where condition.");
	sb.append("\n\n");
	sb.append("Select a server or servers which you would like to scan for unique records.");
	sb.append("\n\n");
	sb.append("Also from the list box select columns you would like to compare from tables. Click “Compare” to continue.");
	sb.append("\n\n");
	sb.append("Ones the application finish comparing the data, it will display any disputes. You can generate SQL statements for these disputes by clicking “Generate SQL” button.");
	sb.append("\n\n");
	
	return sb.toString();
    }
}