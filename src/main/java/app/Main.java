/*
 *Callie Monroe
 *Maxar Coding Challenge
 *1/30/2022
 * Main handles the execution of the app
 * It will parse and read the json file,
 * find all max & min coordinates,
 * create the return json,
 * and write the final json to a file
 */
package app;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
public class Main {
	
	public static void main(String[] args) {
//		initially parse the polygon.json file and get the x & y coords
		geoJSONParser gjp = new geoJSONParser();
		try {
			gjp.processJSON();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		Find all min & max X & Y coords
		minBoundingRect mbr = new minBoundingRect();
		double minX = mbr.findMinX(gjp.XCoords);
		double maxX = mbr.findMaxX(gjp.XCoords);
		double minY = mbr.findMinY(gjp.YCoords);
		double maxY = mbr.findMaxY(gjp.YCoords);
		
//		create a JSON array of the rectangle coordinates
		JSONArray rectangleCoords = mbr.createRectCoords(minX, minY, maxX, maxY);
		
//		finally create a geo JSONObject w/ a coordinate array and type "polygon"
//		then write that JSON Object to an output file
		try {
			JSONObject minBoundingRectJSON = gjp.createJSON(rectangleCoords);
			System.out.println("Minimum Bounding Rectangle Found: " + minBoundingRectJSON);
			gjp.writeToFile(minBoundingRectJSON);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
