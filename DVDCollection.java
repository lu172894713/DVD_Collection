import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class DVDCollection {

	// Data fields
	
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.
		String dvdlist = null, number = null, len = null, tlist = null;
		number = String.valueOf(numdvds);
		len = String.valueOf(dvdArray.length);
		dvdlist = "\n" + "numdvds = " + number + "\n" + "dvdArray.length = " + len + "\n";
		addOrModifyDVD("ZZ", "ZZ", "0");
		int oldsize = numdvds;
		for(int i1 = 0; i1 < oldsize-1; i1++)
		{
			for(int i2 = 0; i2 < numdvds; i2++)
			{
				if(dvdArray[i2].getTitle().compareTo(dvdArray[i1].getTitle())>0)
				{
					DVD temp = dvdArray[i1];
		            dvdArray[i1] = dvdArray[i2];
		            dvdArray[i2] = temp;
				}
			}
		}
		removeDVD("ZZ");
		for(int i = 0; i < numdvds; i++)
		{
			if(tlist == null)
			{
				tlist = "dvdArray[" + i + "]" + " = " + dvdArray[i].toString();
				
			}
			else
			{
				tlist =  tlist + "dvdArray[" + i + "]" + " = " + dvdArray[i].toString();
				
			}
			
		}
		
		dvdlist = dvdlist + tlist;
		return dvdlist;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime){
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
	
		int rt = 0, olddvd = 0;
		boolean newdvd = true;
		DVD temp = new DVD(null,null,0);
		
		for(int i = 0; i < numdvds; i++)
		{
			if(title.equals(dvdArray[i].getTitle()))
			{
				
				newdvd = false;
				olddvd = i;
				break;
			}
		}
		if(newdvd == true && numdvds < dvdArray.length)
		{
			temp.setTitle(title);
			temp.setRating(rating);
			rt = Integer.parseInt(runningTime);
			temp.setRunningTime(rt);
			dvdArray[numdvds] = temp;
			numdvds++;
		}
		else if(newdvd == false )
		{
			temp.setTitle(title);
			temp.setRating(rating);
			rt = Integer.parseInt(runningTime);
			temp.setRunningTime(rt);
			dvdArray[olddvd] = temp;
			System.out.println(olddvd);
		}
		else if(newdvd == true && numdvds >= dvdArray.length)
		{
			int doublesize = dvdArray.length * 2;
			if(doublesize < numdvds)
			{
				doublesize *= 2;
			}
			DVD[] temparray = new DVD[doublesize];
			temp.setTitle(title);
			temp.setRating(rating);
			rt = Integer.parseInt(runningTime);
			temp.setRunningTime(rt);
			temparray = Arrays.copyOf(dvdArray, doublesize);
			temparray[numdvds] = temp;
			dvdArray = temparray;
			numdvds++;
		}

	}
	
	public void removeDVD(String title) {
		int tindex = 0;
		boolean target = false;
		for(int i = 0; i < numdvds; i++)
		{
			
			if(title.equals(dvdArray[i].getTitle()))
			{
				
				target = true;
				tindex = i;
				break;
			}
		}
		if(target == true)
		{
			DVD[] copyarray = new DVD[dvdArray.length];
			
			System.arraycopy(dvdArray, 0, copyarray, 0, tindex);
			System.arraycopy(dvdArray, tindex+1, copyarray, tindex, numdvds-tindex-1);
			numdvds--;
			 for (int i=0; i<numdvds; i++) 
			 {
				 
		            dvdArray[i] = copyarray[i]; 
			 }
		}
		else
		{
			System.out.println("Target not in the list.\n");
		}
		

	}
	
	public String getDVDsByRating(String rating) {
		int[] dvdindex = new int[numdvds];
		int di = 0;
		String alltitle = null;
		boolean found = false;
		for(int i = 0; i < numdvds; i++)
		{
			if(rating.equals(dvdArray[i].getRating()))
			{
				
				dvdindex[di] = i;
				di++;
				found = true;
			}
		}
		if(found)
		{
			for(int x= 0; x < di; x++)
			{
				if(alltitle == null)
				{
					alltitle = dvdArray[dvdindex[x]].getTitle() + "\n";
				}
				else
				{
					alltitle = dvdArray[dvdindex[x]].getTitle() + "\n" + alltitle +"\n";
				}
				
			}
		}
		else
		{
			alltitle = "Target not in the list.\n";
		}

		return alltitle;	

	}

	public int getTotalRunningTime() {
		int totaltime = 0;
		for(int i = 0; i < numdvds; i++)
		{
		
			totaltime = totaltime + dvdArray[i].getRunningTime();
		}
		return totaltime;	

	}

	
	public void loadData(String filename) {
		int x = 0;
		Scanner filescan = null;
		String[] strarray = new String[dvdArray.length],  arrSplit = null;
		File file = new File(filename);
		try {
			filescan = new Scanner(file);
			while(filescan.hasNextLine())
			{
				strarray[x] = filescan.nextLine();
				x++;
			}
			for(int i1 = 0; i1 < strarray.length; i1++)
			{
				if(strarray[i1] != null )
				{
					strarray[i1] = strarray[i1]. replaceAll("[a-z],", "");	
					strarray[i1] = strarray[i1]. replaceAll("[a-z]", "");
					arrSplit = strarray[i1].split(",");
					for(int i2= 0; i2 < arrSplit.length; i2++)
					{					
						addOrModifyDVD(arrSplit[i2], arrSplit[i2+1], arrSplit[i2+2]);
						i2 += 2;
					}
				}
			}	
		} catch (Exception e) {
			System.out.println("File not exist.\n");
		}			
	}
	
	public void save() {
		try {
			  
		      FileWriter dvdwriter = new FileWriter("dvddata.txt");
		      for(int i = 0; i < numdvds; i++)
		      {
		    	  dvdwriter.write(dvdArray[i].getTitle() + "," + dvdArray[i].getRating() + "," + dvdArray[i].getRunningTime() + "\n");
		      }
		      dvdwriter.close();
		      System.out.println("Done.");
		    } catch (IOException e) {
		      System.out.println("Error.");
		      e.printStackTrace();
		    }
	}

	// Additional private helper methods go here:
	public int getnumdvds()
	{
		return numdvds;
	}
	
	public DVD[] getdvdarray()
	{
		return dvdArray;
	}

	
}
