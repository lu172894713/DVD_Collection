import java.util.*;

import javax.swing.JOptionPane;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {

public static void main(String[] args) {
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();

		Scanner scan = new Scanner(System.in);

		
		String filename = JOptionPane.showInputDialog("Enter the file name");		
		dl.loadData(filename);
		
		dlInterface = new DVDGUI(dl);
		dlInterface.processCommands();
		
		
		
	}

}
