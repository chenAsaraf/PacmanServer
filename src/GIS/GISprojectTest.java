package GIS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GISprojectTest {

	@Test
	void testGISproject() {
		GISelement ge = new GISelement("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],12/1/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI");
		GISelement ge2 = new GISelement("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],12/1/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI");
		GISlayer gl = new GISlayer();
		GISproject gp = new GISproject();
		gl.add(ge);
		gl.add(ge2);
		gp.add(gl);
		assertEquals("[40:65:a3:35:4c:c4, 12/1/2017 10:49],32.17218268,34.81446402,13.65040889\n,[40:65:a3:35:4c:c4, 12/1/2017 10:49],32.17218268,34.81446402,13.65040889\n\n", gp.toString());
	}

}
