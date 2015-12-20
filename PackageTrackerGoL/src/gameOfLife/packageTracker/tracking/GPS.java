package gameOfLife.packageTracker.tracking;

import java.util.ArrayList;

import gameOfLife.packageTracker.shipping.Point;

public class GPS
{
   private static long nextID = 0;
   private final long id;
   private ArrayList<Point> points, wayPoints;
   
   public GPS()
   {
      id = ++nextID;
      points = new ArrayList<>();
      wayPoints = new ArrayList<>();
   }
   
   public Point getPoint(int i)
   {
      return points.get(i);
   }
   
   public Point getWayPoint(int i)
   {
      return wayPoints.get(i);
   }
   
   public void addPoint(Point point)
   {
      points.add(point);
   }
   
   public void addWayPoint(Point point)
   {
      wayPoints.add(point);
   }
   
   @Override
   public String toString()
   {
      return "GPS " + id + ":\nPoints: " + points + "\nWayPoints: " + wayPoints;
   }
}