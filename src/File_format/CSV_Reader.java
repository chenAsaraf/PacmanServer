package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GIS.GIS_element;
import GIS.GISelement;
import GIS.GISlayer;
import GameEntities.Fruit;
import GameEntities.Packman;

/**
 * this class reads the CSV files 
 * reads the "WIGGLE WIFI" files, "PACKMAN" or "FRUIT" we got at the first line,
 * every line of the file is sent to GIS element to process it
 * later is sent to the Csv2Kml class to make a KML file out of it.
 *@author Inna and Chen
 */
public class CSV_Reader {

	/**
	 * reads the merged CSV file , converts the  time string into Date format and sends it to the writeCsvFile function
	 * @param files
	 */
	public void readCsvFile(GISlayer gisLayer,String files) {
		BufferedReader fileReader = null;
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(files));
			// Read the CSV file headers to skip them (there is 2 of them)
			fileReader.readLine();
			fileReader.readLine();
			// Reads the file line by line starting from the third line and sends it to GISelement object
			// then adds this element to GISlayer
			while ((line = fileReader.readLine()) != null) {
				GISelement gisElem = new GISelement(line);
				gisLayer.add(gisElem);
			}
		}
		catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * reads the merged csv file , converts the  time string into Date format and sends it to the writeCsvFile function
	 * @param files
	 */
	public ArrayList<GIS_element> readCsvFilePackmans(String files) {
		ArrayList<GIS_element> fruitAndPackmans = new ArrayList<GIS_element>();
		BufferedReader fileReader = null;
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(files));
			// Read the CSV file headers to skip them 
			fileReader.readLine();
			// Reads the file line by line starting from the second line and sends it to GISelement object
			// then adds this element to GISlayer
			while ((line = fileReader.readLine()) != null) {
				if(line.substring(0,1).equals("P")){
					Packman p  = new Packman(line);
					fruitAndPackmans.add(p);
				}
				else if(line.substring(0,1).equals("F")){
					Fruit f = new Fruit(line);
					fruitAndPackmans.add(f);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}
		return fruitAndPackmans;
	}
	
}