package MainApp;

import DBTools.ConnectionTypes;
import DBTools.DBOperationManager;
import java.sql.SQLException;

/**
 * @author: Archie Gunasekara
 * @date: 2013.07.02
 */
public class AppStateHolder {
    
    private ConnectionTypes connectionType;
    private DBOperationManager dbOperationManagerOne;
    private DBOperationManager dbOperationManagerTwo;   

    public void setConnectionType(ConnectionTypes connectionType) {
	
	this.connectionType = connectionType;
    }

    public ConnectionTypes getConnectionType() {
	
	return connectionType;
    }
    
    public void setDbOperationManagerOne(DBOperationManager dbOperationManagerOne) {
	
	this.dbOperationManagerOne = dbOperationManagerOne;
    }
    
    public DBOperationManager getDbOperationManagerOne() {
    
	return dbOperationManagerOne;
    }
    
    public void setDbOperationManagerTwo(DBOperationManager dbOperationManagerTwo) {
	
	this.dbOperationManagerTwo = dbOperationManagerTwo;
    }
    
    public DBOperationManager getDbOperationManagerTwo() {
    
	return dbOperationManagerTwo;
    }
    
    public void reset() throws SQLException, NullPointerException {
	
	connectionType = null;
	dbOperationManagerOne.close();
	dbOperationManagerOne = null;
	dbOperationManagerTwo.close();
	dbOperationManagerTwo = null;
    }
}
