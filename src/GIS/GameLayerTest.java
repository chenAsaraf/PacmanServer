package GIS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameEntities.Packman;

class GameLayerTest {

	@Test
	public void test() {
		Packman ge = new Packman("P,2,32.10394266,35.20724539,0,10,1");
		GameLayer gl = new GameLayer();
		gl.add(ge);
		assertEquals("P,2,32.10394266,35.20724539,0,10,1", gl.toString());
	
	}


}
