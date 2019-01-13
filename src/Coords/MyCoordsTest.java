package Coords;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {
	MyCoords test = new MyCoords();

	@Test
	void testAdd() {
		double lat = 32.103315;
		double lon = 35.209039;
		double alt = 670;
		Point3D gps = new Point3D(lat, lon, alt);
		Point3D local_vector_in_meter = new Point3D(337.699, -359.249, -20);

		Point3D ans = new Point3D(test.add(gps, local_vector_in_meter));
		assertEquals("32.106352000071396,35.20522500219698,650.0",ans.toString());
	}


	@Test
	void testDistance3d() {
		Point3D gps0 = new Point3D(32.103315, 35.209039, 670);
		Point3D gps1 = new Point3D(32.106352, 35.205225, 650);
		double answer = test.distance3d(gps0, gps1);
		String expected = "493.0523321340352";
		assertEquals(expected, ""+answer);
	}
	
	@Test
	void testVector3D() {
		Point3D gps0 = new Point3D(32.103315, 35.209039, 670);
		Point3D gps1 = new Point3D(32.106352, 35.205225, 650);
		Point3D answer = test.vector3D(gps0, gps1);
		Point3D expected = new Point3D(337.6989920612881, -359.24920693881893, -20);
		assertEquals(answer.toString(), expected.toString());
	}


	@Test
	void testAzimuth_elevation_dist() {
		Point3D gps0 = new Point3D(32.103315, 35.209039, 670);
		Point3D gps1 = new Point3D(32.106352, 35.205225, 650);
		double answer[] = test.azimuth_elevation_dist(gps0, gps1);
		String expected = "[313.22946941268225, -2.324164382280958, 493.0523321340352]";
		assertEquals(expected, Arrays.toString(answer));
	}

	@Test
	void test1isValid_GPS_Point() {
		Point3D notValid = new Point3D(91, 0, 0);
		boolean answer = test.isValid_GPS_Point(notValid);
		assertFalse(answer);
	}

	@Test
	void  test2isValid_GPS_Point() {
		Point3D Valid = new Point3D(35, 32, -400);
		boolean answer = test.isValid_GPS_Point(Valid);
		assertTrue(answer);
	}



}
