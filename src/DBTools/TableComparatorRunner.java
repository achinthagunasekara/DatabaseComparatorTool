package DBTools;

import GUI.MainFrame;
import java.util.ArrayList;

/**
 * @author: Archie Gunasekara
 * @date: 2013.07.02
 */
public class TableComparatorRunner extends Thread {
    
    private final MainFrame mf;
    private final ArrayList<String[]> dataSetOne;
    private final ArrayList<String[]> dataSetTwo;
    private final ArrayList<String> tableCols;
    private final String tableName;
    private final int[] colsToCompare;
    private final boolean checkServerOne;
    private final boolean checkServerTwo;
    
    @Override
    public void run() {

	mf.getCompData().enableCompareButton(false);
	mf.getTComparatorOne().setInfoLabelText("");
	mf.getTComparatorTwo().setInfoLabelText("");
	
	if(checkServerOne) {
	    
	    ArrayList<String[]> disputesOne = compareSets(dataSetOne, dataSetTwo, 1);
	    mf.getTComparatorOne().displayData(tableCols, disputesOne, tableName);
	    mf.getTComparatorOne().setInfoLabelText(disputesOne.size() + " unique records found!");
	}
	else {
	    
	    mf.getTComparatorOne().displayData(tableCols, new ArrayList<String[]>(), tableName);
	    mf.getTComparatorOne().setInfoLabelText("This server was not scanned for unique records");
	}

	if(checkServerTwo) {
	    
	    ArrayList<String[]> disputesTwo = compareSets(dataSetTwo, dataSetOne, 2);
	    mf.getTComparatorTwo().displayData(tableCols, disputesTwo, tableName);
	    mf.getTComparatorTwo().setInfoLabelText(disputesTwo.size() + " unique records found!");
	}
	else {
	    
	    mf.getTComparatorTwo().displayData(tableCols, new ArrayList<String[]>(), tableName);
	    mf.getTComparatorTwo().setInfoLabelText("This server was not scanned for unique records");
	}
	
	mf.getCompData().enableCompareButton(true);
	mf.getCompData().setProgress(0);
	mf.getCompData().setProgressBarText("");
    }
    
    public TableComparatorRunner(MainFrame mf, ArrayList<String[]> dataSetOne, ArrayList<String[]> dataSetTwo, ArrayList<String> tableCols, String tableName, int[] colsToCompare, boolean checkServerOne, boolean checkServerTwo) {
	
	this.mf = mf;
	this.dataSetOne = dataSetOne;
	this.dataSetTwo = dataSetTwo;
	this.tableCols = tableCols;
	this.tableName = tableName;
	this.colsToCompare = colsToCompare;
	this.checkServerOne = checkServerOne;
	this.checkServerTwo = checkServerTwo;
    }
    
    private ArrayList<String[]> compareSets(ArrayList<String[]> setOne, ArrayList<String[]> setTwo, int checkingRound) {
	
	int progress = 0;
	boolean rowFound;
	ArrayList<Integer> foundRecords = new ArrayList<Integer>();
	ArrayList<String[]> disputes = new ArrayList<String[]>();
	
	for(int i = 0; i < setOne.size(); i++) {
	    
	    String[] setOneRow = setOne.get(i);
	    rowFound = false;
	    
	    for(int j = 0; j < setTwo.size(); j++) {
		
		//this section seemeed to have slow down the process a lot
		//it has been removed from the code for now
		//if a record is marked as found, skip over it
		//if(foundRecords.contains(j)) {
		    
		   // continue;
		//}
		
		String[] setTwoRow = setTwo.get(j);
		
		if(compareStringArray(setOneRow, setTwoRow)) {
		    
		    foundRecords.add(j);
		    rowFound = true;
		    //do not continue when a record is found
		    break;
		}
	    }
	    
	    if(!rowFound) {
		
		disputes.add(setOneRow);
	    }
	    
	    //calculate and update the progress bar
	    if(checkServerOne && checkServerTwo) {
		
		progress = (i*50)/setOne.size();
		progress = (checkingRound == 1) ? progress : progress + 50;
	    }
	    else {

		progress = (i*100)/setOne.size();
	    }
	    
	    mf.getCompData().setProgress(progress);
	    mf.getCompData().setProgressBarText(progress + "%");
	}
	
	return disputes;
    }
    
    private boolean compareStringArray(String[] arrOne, String[] arrTwo) {
	
	for(int i = 0; i < tableCols.size(); i++) {
	    
	    for(int k = 0; k < colsToCompare.length; k++) {

		if(i == colsToCompare[k]) {
		    
		    if(arrOne[i] != null) {
			
			if(!arrOne[i].equals(arrTwo[i])) {
			    
			    return false;
			}
		    }
		}
	    }
	}
	
	return true;
    }
}
