package model.logic;

import com.opencsv.CSVReader;

import model.data_structures.DoublyLinkedList;
import model.data_structures.RedBlackTree;
import model.value_objects.GeoCoordinate;
import model.value_objects.TravelArea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {


    public final static String TRAVEL_AREAS_FILE = "./data/bogota_cadastral.json";



    public void loadTravelAreas(RedBlackTree<Integer, TravelArea> data) {

        FileReader file;
        try {
            file = new FileReader(TRAVEL_AREAS_FILE);
            JSONParser parser = new JSONParser();

            JSONObject o = (JSONObject) parser.parse(file);
            JSONArray arr = (JSONArray) o.get("features");

             loadArrayOfAreas(arr, data);

            file.close();
        } catch (IOException | ParseException e) {

            e.printStackTrace();
        }
    }


    private void loadArrayOfAreas(JSONArray areas, RedBlackTree<Integer, TravelArea> data) {

        if(areas != null) {
            for(int i = 0; i < areas.size(); i++) {
                JSONObject areaJS = (JSONObject) areas.get(i);
                TravelArea area = loadAreaData(areaJS);
                data.put(area.getID(), area);
            }
        }
    }

    private TravelArea loadAreaData(JSONObject areaJS) {
        TravelArea areaVO = null;

        JSONObject properties = (JSONObject)areaJS.get("properties");
        JSONObject geometry = (JSONObject)areaJS.get("geometry");

        String name = (String)properties.get("scanombre");
        double perimeter = (Double) properties.get("shape_leng");
        double area = (Double) properties.get("shape_area");
        int ID = Integer.parseInt((String) properties.get("MOVEMENT_ID"));

        JSONArray arr = (JSONArray)geometry.get("coordinates");
        JSONArray coordinates = (JSONArray) ((JSONArray) arr.get(0)).get(0);

        GeoCoordinate[] frontier = loadCoordinates(coordinates);

        areaVO = new TravelArea(name, perimeter,area,ID, frontier);

        return areaVO;
    }

    private GeoCoordinate[] loadCoordinates(JSONArray coordinatesJS) {


        GeoCoordinate[] coordinatesArray = new GeoCoordinate[coordinatesJS.size()];

        for (int i = 0; i < coordinatesArray.length; i++) {
            coordinatesArray[i] = loadCoordinate((JSONArray) coordinatesJS.get(i));
        }

        return coordinatesArray;
    }

    private GeoCoordinate loadCoordinate(JSONArray coordinateJS){
        return  new GeoCoordinate(((Double) coordinateJS.get(0)), (Double) coordinateJS.get(1));
    }

}
