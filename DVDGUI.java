import java.awt.*;
import java.util.Scanner;

import javax.swing.*;

/**
 *  This class is an implementation of DVDUserInterface
 *  that uses JOptionPane to display the menu of command choices. 
 */

public class DVDGUI extends JFrame implements DVDUserInterface  {
	 
	 private DVDCollection dvdlist;
	 private DVD[] dvdArray;
	 private int numdvds;
	 private String newname;
	 private String newrating;
	 private int newrunningtime;
	 private int totalruntime;
	 private ImageIcon[] imagearray;
	 
	 public DVDGUI(DVDCollection dl)
	 {
		 dvdlist = dl;
		 dvdArray = new DVD[7];
		 numdvds = 0;
		 newname = null;
		 newrating = null;
		 newrunningtime = 0;
		 imagearray = new ImageIcon[7];
		 totalruntime = 0;
	 }
	 
	 public void processCommands()
	 {
		 DVD temp = new DVD(null, null, 0);
		 Scanner scan = new Scanner(System.in);
		 dvdArray = dvdlist.getdvdarray();
		 numdvds = dvdlist.getnumdvds();
		 String[] commands = new String[numdvds+2], commands2 = new String[4];
		 int choice, choice2;
		 do {
			 for(int i = 0; i < numdvds; i++)
			 {
				 commands[i] = dvdArray[i].getTitle();
			 }
			 imagearray[0] = new ImageIcon("ANGELS AND DEMONS.jpg");
			 imagearray[1] = new ImageIcon("STAR TREK.png");
			 imagearray[2] = new ImageIcon("Up.png");
			 commands [numdvds] = "Get Total Run Time(new feature)";
			 commands [numdvds+1] = "Save & Exit";
			 choice = JOptionPane.showOptionDialog(null,
					 "Select a DVD to edit:", 
					 "List of DVDs", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 commands,
					 commands[commands.length - 1]);
			 
			
			for(int n = 0; n < numdvds; n++)
			{
				if(n == choice)
				{
					
					commands2[0] = dvdArray[choice].getTitle();
					commands2[1] = dvdArray[choice].getRating();
					commands2[2] = Integer.toString(dvdArray[choice].getRunningTime());
					commands2[3] = "Save & Exit";
					JOptionPane.showMessageDialog(null, "","DVD image",
							JOptionPane.INFORMATION_MESSAGE,imagearray[choice]);
					choice2 =  JOptionPane.showOptionDialog(null,
							 "Select a object to edit:", 
							 "DVD Information",
							 JOptionPane.YES_NO_CANCEL_OPTION, 
							 JOptionPane.QUESTION_MESSAGE, 
							 null, 
							 commands2,
							 commands2[commands2.length - 1]);
					if(choice2 == 0)
					{
						temp = dvdArray[choice];
						newname = JOptionPane.showInputDialog("Enter the new title");
						temp.setTitle(newname);
						dvdArray[choice] = temp;
						
					}
					if(choice2 == 1)
					{
						temp = dvdArray[choice];
						newrating = JOptionPane.showInputDialog("Enter the new rating");
						temp.setRating(newrating);
						dvdArray[choice] = temp;
					}
					if(choice2 == 2)
					{
						String runt;
						temp = dvdArray[choice];
						runt = JOptionPane.showInputDialog("Enter the new running time");
						newrunningtime = Integer.valueOf(runt);
						temp.setRunningTime(newrunningtime);
						dvdArray[choice] = temp;
					}
					if(choice2 == 3)
					{
						choice = numdvds+1;
					}
				}
				else if(choice == numdvds)
				{
					String trt;
					totalruntime = dvdlist.getTotalRunningTime();
					trt = String.valueOf(totalruntime); 
					System.out.println(totalruntime);
					JOptionPane.showMessageDialog(null,
						    "Total Runtime is " + trt + " minute(s)");
					break;
				}
			}
			
			 
		 } while (choice != numdvds+1);
		 dvdlist.save();
		 JOptionPane.showMessageDialog(null, "Saved!");
		 System.exit(0);
	 }

	

	
	
		
}
