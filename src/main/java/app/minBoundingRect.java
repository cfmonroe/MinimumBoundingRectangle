/*
 *Callie Monroe
 *Maxar Coding Challenge
 *1/30/2022
 * the minBoundingRect class will handle finding the correct
 * coordinate points and creating the rectangle
 */
package app;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
public class minBoundingRect {
	
	private JSONArray upperLeftCoord;
	private JSONArray upperRightCoord;
	private JSONArray lowerLeftCoord;
	private JSONArray lowerRightCoord;
	
	public minBoundingRect() {
//		initialize each coordinate point of the bounding rectangle
		this.upperLeftCoord = new JSONArray();
		this.upperRightCoord = new JSONArray();
		this.lowerLeftCoord = new JSONArray();
		this.lowerRightCoord = new JSONArray();
	}
	
	
	public double findMinX(double[] XCoords) {
//		sorts the x coordinates and finds the min
		Arrays.sort(XCoords);
		return XCoords[0];
	}
	
	public double findMaxX(double[] XCoords) {
//		sorts the x coordinates and finds the max
		Arrays.sort(XCoords);
		return XCoords[XCoords.length - 1];
	}
	
	public double findMinY(double[] YCoords) {
//		sorts the y coordinates and finds the min
		Arrays.sort(YCoords);
		return YCoords[0];
	}
	
	public double findMaxY(double[] YCoords) {
//		sorts the y coordinates and finds the max
		Arrays.sort(YCoords);
		return YCoords[YCoords.length - 1];
	}
	
	public JSONArray createRectCoords(double minX, double minY, double maxX, double maxY) {
//		set all of the rectangles coordinates based on the max and min of each x & y
		this.upperLeftCoord.add(minX);
		this.upperLeftCoord.add(maxY);
		this.upperRightCoord.add(maxX);
		this.upperRightCoord.add(maxY);
		this.lowerLeftCoord.add(minX);
		this.lowerLeftCoord.add(minY);
		this.lowerRightCoord.add(maxX);
		this.lowerRightCoord.add(minY);
		
//		add all of the rectangle points to the json array
		JSONArray rectCoordArr = new JSONArray();
		rectCoordArr.add(this.upperLeftCoord);
		rectCoordArr.add(this.upperRightCoord);
		rectCoordArr.add(this.lowerLeftCoord);
		rectCoordArr.add(this.lowerRightCoord);
//		add the left coord one more time to ensure the last coord is the same as the first
		rectCoordArr.add(this.upperLeftCoord);
		return rectCoordArr;
	}
}
