package gameOfLife.packageTracker.main;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import gameOfLife.packageTracker.exceptions.InvalidUserNameException;
import gameOfLife.packageTracker.shipping.Package;
import gameOfLife.packageTracker.shipping.User;
import gameOfLife.packageTracker.tracking.GPS;
import gameOfLife.packageTracker.util.GPXReader;

/**
 * Main method only<br>
 * Starts the application
 * 
 * @author Krishna
 *         
 */
public class Main
{
   /**
    * <b><i>Only</b></i> to start the application
    * 
    * @param args
    */
   public static void main(String[] args)
   {
      try
      {
         User user = new User("Krishna", "Ozaren", "Password");
//       for the gpx files, if you want to test other ones just put in the name of the file instead of the <name>.gpx
         String gpxLocation = "..\\..\\..\\..\\gpx_files\\Annapolis_to_WestPoint_10sec.gpx";
         GPS gps = GPXReader.load(gpxLocation);
         Package pack = new Package(user, "Music CD", 153481438, gps);
         for(int i = 0; i < gps.getNumberOfPoints(); i++)
         {
            System.out.println(pack.getGps().getPoint(i));
         }
      }
      catch(InvalidUserNameException | FileNotFoundException | XMLStreamException e)
      {
         e.printStackTrace();
         return;
      }
   }
}
