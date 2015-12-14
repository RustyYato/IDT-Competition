package gameOfLife.packageTracker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import gameOfLife.packageTracker.shipping.GPS;
import gameOfLife.packageTracker.shipping.Point;

public class GPXReader
{
   public static void main(String[] args) throws FileNotFoundException, XMLStreamException
   {
      load("C:\\Users\\Krishna\\Desktop\\testGPX.gpx");
   }
   
   private static XMLStreamReader reader;
   private static File            xmlfile;
   private static XMLInputFactory factory;
                                  
   public static void load(String xmlloc) throws FileNotFoundException, XMLStreamException
   {
      load(new File(xmlloc));
   }
   
   private static void load(File file) throws FileNotFoundException, XMLStreamException
   {
      xmlfile = file;
      factory = XMLInputFactory.newFactory();
      load();
   }
   
   private static class Tag
   {
      static String longtitude, latitude, timeStamp;
   }
   
   private static void load() throws FileNotFoundException, XMLStreamException
   {
      GPS gps = new GPS();
      reader = factory.createXMLStreamReader(new FileInputStream(xmlfile));
      int event;
      String text = null;
      while(reader.hasNext())
      {
         event = reader.next();
         switch(event)
         {
            case XMLStreamConstants.START_ELEMENT:
               Tag.timeStamp = null;
               switch(reader.getLocalName())
               {
                  case "trkpt":
                  case "wpt":
                     for(int i = 0; i < reader.getAttributeCount(); i++)
                     {
                        text = reader.getAttributeValue(i);
                        switch(reader.getAttributeLocalName(i))
                        {
                           case "lat":
                              Tag.latitude = text;
                              break;
                           case "lon":
                              Tag.longtitude = text;
                              break;
                        }
                     }
                     break;
               }
               break;
            case XMLStreamConstants.CHARACTERS:
               text = reader.getText().trim();
               break;
            case XMLStreamConstants.END_ELEMENT:
               switch(reader.getLocalName())
               {
                  case "trkpt":
                     Tag.timeStamp = text;
                     gps.addPoint(new Point(Double.valueOf(Tag.latitude), Double.valueOf(Tag.longtitude), Tag.timeStamp));
                     break;
               }
               System.out.println(gps + "\n");
               break;
         }
      }
   }
}
