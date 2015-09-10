package GUI;

import Information.HelpBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author Archie Gunasekara
 * 2013.07.03
 */
public class AppMenu extends JMenuBar implements ActionListener, ItemListener {
    
    private final MainFrame mf;
    private final JMenu menuFile, help;
    private final JMenuItem newCon, exit, showHelp;
    
    public AppMenu(MainFrame mf) {
	
	super();
	this.mf = mf;
	
	menuFile = new JMenu("File");
	help = new JMenu("Help");
	
	newCon = new JMenuItem("New Connection", KeyEvent.VK_T);
	newCon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
	newCon.addActionListener(this);
	menuFile.add(newCon);
	
	exit = new JMenuItem("Exit", KeyEvent.VK_T);
	exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
	exit.addActionListener(this);
	menuFile.add(exit);
	
	showHelp = new JMenuItem("View Help", KeyEvent.VK_T);
	showHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
	showHelp.addActionListener(this);
	help.add(showHelp);
	
	add(menuFile);
	add(help);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
	if(e.getSource() == newCon) {
	    
	    mf.reset();
	}
	if(e.getSource() == exit) {
	    
	    //exit application
	    mf.disposeAllConnections();
	    System.exit(0);
	}
	if(e.getSource() == showHelp) {
	    
	    //exit application
	    HelpBox helpBox = new HelpBox();
	    helpBox.showHelp();
	}
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
