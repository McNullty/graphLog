
package hr.analemma.graph;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import hr.analemma.graph.FileParser.ParseData;

import org.junit.Test;

public class FileParserTest {

	@Test
	public void testParsingFile() {

		try {
			ClassLoader classloader =
				Thread.currentThread().getContextClassLoader();
			InputStream is =
				classloader.getResourceAsStream("140110-103424_10.67.29.180_ipRec_MHW02022_Lock_Root_2.log");

			ParseData pd = FileParser.parseFile(is);
			assertNotNull(pd);

			List<Integer> x = pd.getAxisData().getxAxis();
			List<BigDecimal> y = pd.getAxisData().getyAxis();

			assertNotNull(x);
			assertNotNull(y);

			assertEquals(
				"IPEPB REQUESTED RECOVERY TIME is: 0.05 sec (50 msec)\n"
					+ "IPEPB AVERAGE RECOVERY TIME is: 0.6088 sec (608.7809 msec)\n"
					+ "IPEPB MAXIMUM RECOVERY TIME is: 4.0975 sec (4097.4935 msec)\n"
					+ "IPEPB MINIMUM RECOVERY TIME is: 0.0000 sec (0.0054 msec)\n"
					+ "IPEPB RECOVERY TIME STANDARD DEVIATION is: 0.6360 sec (635.9836 msec)\n",
				pd.getSummary());
		}
		catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSwitchOff() {

		try {
			ClassLoader classloader =
				Thread.currentThread().getContextClassLoader();
			InputStream is =
				classloader.getResourceAsStream("140210-151425_10.67.29.180_ipRec_EXTDIST00009_SwitchOff.log");

			ParseData pd = FileParser.parseFile(is);
			assertNotNull(pd);
		}
		catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testChangeRootSwitch() {

		try {
			ClassLoader classloader =
				Thread.currentThread().getContextClassLoader();
			InputStream is =
				classloader.getResourceAsStream("140226-112237_10.67.29.180_ipRec_CFG02017_ChangeRootSwitch.log");

			ParseData pd = FileParser.parseFile(is);
			assertNotNull(pd);
		}
		catch (ParseException e) {
			fail(e.getMessage());
		}
	}
}
