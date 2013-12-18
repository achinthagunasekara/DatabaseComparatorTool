package Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * @author Archie Gunasekara
 * 2013-05-31
 */
public class ConfigFileReader {
 
    private Properties prop;
    private static ConfigFileReader instance;
    
    public static ConfigFileReader getConfigFileReaderInstance() {
        
        if(instance == null) {
            
            instance = new ConfigFileReader();
        }
        
        return instance;
    }
    
    //make sure there is only one instance of this object per run
    private ConfigFileReader() {
        
        readFile();
    }
    
    //read file
    private void readFile() {
        
        prop = new Properties();

        try {
            //load a properties file
            prop.load(new FileInputStream("config.properties"));
 
        } catch (IOException ex) {
            
            System.out.println("Error in Config File Reader - " + ex.toString());
        }
    }
    
    public String getPropertyVal(String property) {
        
        return prop.getProperty(property);
    }
    
    //split comma seperated values
    public String[] splitVals(String s) {
        
        return s.split(",");
    }
}