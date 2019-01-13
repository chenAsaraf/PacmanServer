package GIS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GISlayerTest {

	@Test
	public void testGISlayer() {
		GISelement ge = new GISelement("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-03 10:49:00,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI");		GISlayer gl = new GISlayer();
		gl.add(ge);
		System.out.println(gl.toString());
		assertEquals("32.17218268,34.81446402,13.65040889 the UTC of this metaData is: 1489315740000\r\n", gl.toString());
	}





}
