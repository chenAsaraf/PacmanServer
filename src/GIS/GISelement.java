package GIS;

import java.time.LocalDateTime;


import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

public class GISelement implements GIS_element {

	private static final String COMMA_DELIMITER = ",";

	private GeomElement geomElement;
	private MetaData metaData;

	/**
	 * Constructor
	 * divides the data of the row to GeomElement that represents the point 
	 * and to MetaData that represent all the rest data of the row
	 * depends on if its a gps csv
	 * @param row
	 */
	public GISelement(String row) {
		makeGps(row);
	}

	/**
	 * makes a gps array
	 * @param row
	 */
	private void makeGps(String row){
		int CSV_TIME_IDX = 3;
		int CSV_LAT_IDX = 6;
		int CSV_LON_IDX = 7;
		int CSV_ALT_IDX = 8;
		String[] tokens = row.split(COMMA_DELIMITER);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");
		if (tokens.length > 0) {
			String row_point = tokens[CSV_LAT_IDX] + "," + tokens[CSV_LON_IDX] + "," + tokens[CSV_ALT_IDX];
			geomElement = new GeomElement(row_point);
			String time = tokens[CSV_TIME_IDX];
			LocalDateTime dateTime = LocalDateTime.parse(time, format);
			long utc = dateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
			metaData = new MetaData(utc);
		}
	}

	@Override
	public Geom_element getGeom() {
		return geomElement;
	}

	@Override
	public Meta_data getData() {
		return metaData;
	}

	@Override
	public void translate(Point3D vec) {

	}
	
	public String toString() {
		String ans = "";
		ans = geomElement.toString() + " " + metaData.toString();
		return ans;
	}
}