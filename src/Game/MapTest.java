package Game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Coords.Pixel;
import Geom.Point3D;

class MapTest {
	String mapData = "B,0,32.101898,35.202369,0.0,32.105728,35.212416,0.0,1.0";
	String path = "C:\\Users\\owner\\Desktop\\Ariel1.png";
	Map test = new Map(path , mapData);
	Point3D gpsCicar = new Point3D(35.207501,32.102461, 0);

	@Test
	void testconvertToPixel() {
		Point3D pixel = Map.convertToPixel(gpsCicar);
		assertEquals("782.9746875660499,557.6751296374651,0.0",pixel.toString());
	}

	@Test
	void testConvertToPolar() {
		Pixel pixelLibrary = new Pixel(647, 96, 0);
		Point3D gps = Map.convertToPolar(pixelLibrary);
		assertEquals("35.20660506629842,32.10639119626126,641.0", gps.toString());
	}


	@Test
	void testAdd() {
		Pixel pixelLibrary = new Pixel(647, 96, 0);
		Point3D local_vector_in_meter = new Point3D(50, 50 ,0);
		pixelLibrary = Map.add(pixelLibrary, local_vector_in_meter);
		assertEquals("715.2444323915245,4.473637014007238,0.0", pixelLibrary.toString());
	}	



	@Test
	void testVector3Dmeter() {
		Point3D pixelCicar = Map.convertToPixel(gpsCicar);
		Pixel pixelLibrary = new Pixel(647, 96, 0);
		Point3D vector = Map.vector3Dmeter((Point3D)pixelLibrary, pixelCicar);
		assertEquals("99.62328266278986,252.20882518194244,0.0", vector.toString());
	}
	
	
	@Test
	void testIs_Valid_Point() {
		Point3D NotValid = new Point3D(1600, 0, 0);
		boolean answer = Map.is_Valid_Point(NotValid);
		assertFalse(answer);
	}


}
