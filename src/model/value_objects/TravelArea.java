package model.value_objects;

public class TravelArea {

   private  String name;
   private  double perimeter;
   private  double area;
   private int ID;
   private GeoCoordinate[] frontier;


    public TravelArea(String name, double perimeter, double area, int ID, GeoCoordinate[] frontier) {
        this.name = name;
        this.perimeter = perimeter;
        this.area = area;
        this.ID = ID;
        this.frontier = frontier;
    }

    public String getName() {
        return name;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }

    public int getID() {
        return ID;
    }

    public GeoCoordinate[] getFrontier() {
        return frontier;
    }

    @Override
    public String toString() {

        String a =  String.format("%20s %20s  %20s  %20s", "Name", "Perimeter", "Area", "Number of points");
        String b = String.format("%20s %20s  %20s  %20s", name, perimeter, area, frontier.length);

        return a + "\n" + b;
    }

}
