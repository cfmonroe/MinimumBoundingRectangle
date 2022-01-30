/*
 *Callie Monroe
 *Maxar Coding Challenge
 *1/30/2022
 * geoJSONParser will handle everything involving the reading,
 * creating, and writing of JSON objects
 */
package app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class geoJSONParser {

	private String file = "";
	public double[] YCoords;
	public double[] XCoords;
	public JSONArray originalCoords;
	
	public geoJSONParser() {
		this.file = "src/main/resources/polygon.json";
	}
	
	public void processJSON() throws IOException, ParseException {
		/*
		 * read in the file that stores the geoJSON polygon data
		 * process the data and separate the x & y cooridnates into two subsequent arrays
		 */
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader(this.file, Charset.forName("UTF-8"));
		
//		read in data into a string
		String data = "";
		int ch;
		while((ch=reader.read()) != -1)
		{
			data += (char)ch;
		}

//		turn string into JSONObject
		JSONObject geoJSONObject = (JSONObject)parser.parse(data);
		
//		close reader
		reader.close();

//		get the coordinates as a JSON Array
		this.originalCoords = (JSONArray) parser.parse(geoJSONObject.get("coordinates").toString());
		
//		initiate our coordinate array sizes after we know how many coordinates there are
		YCoords = new double[this.originalCoords.size()];
		XCoords = new double[this.originalCoords.size()];
		
		for(int i = 0; i < this.originalCoords.size(); i++)
		{
			JSONArray xyCoord = (JSONArray) parser.parse(this.originalCoords.get(i).toString());
//			store each x and y coordinate in their respective array
			XCoords[i] = (Double) xyCoord.get(0);
			YCoords[i] = (Double) xyCoord.get(1);
		}	
	}
	
	public JSONObject createJSON(JSONArray rectangleCoords) throws ParseException {
		/*
		 * This creates the official json object using the array of 
		 * rectangle coordinates & sets type to Polygon
		 */
		JSONParser parser = new JSONParser();
		JSONObject polygonGeoJSON = new JSONObject();
		polygonGeoJSON.put("type", "Polygon");
		polygonGeoJSON.put("coordinates", rectangleCoords);
		
		return polygonGeoJSON;
	}
	
	public void writeToFile(JSONObject minBoundingRectJSON) throws IOException {
		/*
		 * This method writes the JSONObject we created to a file
		 */
		File file = new File("src/main/resources/minBoundingRect.json");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		
		fw.write(minBoundingRectJSON.toString());
		fw.close();	
	}
}
