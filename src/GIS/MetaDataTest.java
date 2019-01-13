package GIS;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

class MetaDataTest {

	@Test
	void testMetaData() {
		String time = "2017-12-03 10:49:00";
		DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(time, parseFormatter);
		long utc = dateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
		Meta_data md = new MetaData(utc);
		assertEquals("the UTC of this metaData is: 1489315740000" , md.toString());
	}

}
