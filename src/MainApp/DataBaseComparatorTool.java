package MainApp;

import GUI.MainFrame;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

/**
 * @author Archie Gunasekara
 * 2013.07.02
 */
public class DataBaseComparatorTool {

    private MainFrame mf;
    private AppStateHolder appState;
    
    public static void main(String[] args) {
	
	showSplash();
	DataBaseComparatorTool dbct = new DataBaseComparatorTool();
	dbct.startApp();
    }
    
    private void startApp() {
	
	appState = new AppStateHolder();
	mf = new MainFrame(appState);
	mf.setVisible(true);
	mf.setVisible(true);
	mf.toFront();
	mf.initializeApplication();
    }
    
        static void showSplash() {
	
	final SplashScreen splash = SplashScreen.getSplashScreen();
	
        if (splash == null) {
	    
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
	
        if (g == null) {
	    
            System.out.println("g is null");
            return;
        }
        for(int i=0; i<100; i++) {
	    
            renderSplashFrame(g, i);
            splash.update();
	    
            try {
		
                Thread.sleep(30);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
    }
    
    static void renderSplashFrame(Graphics2D g, int frame) {
	
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120,140,200,40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading " + frame +"...", 300, 350);
    }
}
