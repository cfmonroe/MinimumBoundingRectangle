package app.test;
import org.junit.Test;

import app.Main;
import app.geoJSONParser;
import app.minBoundingRect;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
public class TestMinBoundingRectangleApp {

	private JSONArray rectangleCoords;
	private geoJSONParser gjp;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	@Before
	public void findMinBoundingRect() {
		this.gjp = new geoJSONParser();
		try {
			this.gjp.processJSON();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		Find all min & max X & Y coords
		minBoundingRect mbr = new minBoundingRect();
		this.minX = mbr.findMinX(this.gjp.XCoords);
		this.maxX = mbr.findMaxX(this.gjp.XCoords);
		this.minY = mbr.findMinY(this.gjp.YCoords);
		this.maxY = mbr.findMaxY(this.gjp.YCoords);
		
//		create a JSON array of the rectangle coordinates
		this.rectangleCoords = mbr.createRectCoords(this.minX, this.minY, this.maxX, this.maxY);
		
//		finally create a geo JSONObject w/ a coordinate array and type "polygon"
//		then write that JSON Object to an output file
		try {
			JSONObject minBoundingRectJSON = this.gjp.createJSON(rectangleCoords);
			System.out.println("Minimum Bounding Rectangle Found: " + minBoundingRectJSON);
			this.gjp.writeToFile(minBoundingRectJSON);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@Test
	public void outputFiveCoordinates() throws Exception {
		if(rectangleCoords.size() == 5){
			 System.out.println("Five coordinates found!");
		}else {
			throw new Exception("There are not five coordinates in the completed JSON");
		}
	}
	@Test
	public void noCoordsOutOfBounds() throws Exception {
		/*
		 * future test idea
		 */
	}
}
