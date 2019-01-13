package Geom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeomElementTest {


		@Test
		public void testGeomElement() {
			GeomElement ge = new GeomElement("3.2, 5.2, 6.2");
			assertEquals("3.2,5.2,6.2" , ge.getPoint3D());
		}

}
