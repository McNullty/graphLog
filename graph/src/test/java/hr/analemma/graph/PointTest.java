package hr.analemma.graph;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;


public class PointTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testConstructor() {
		Point p = new Point("101     ", "       0.0000");
		assertEquals(new Integer(101), p.getSimno());
		assertEquals(new BigDecimal("0.0000"), p.getRecoveryTime());
		
		p = new Point("1001    ", "          0.3965");
		assertEquals(new Integer(1001), p.getSimno());
		assertEquals(new BigDecimal("0.3965"), p.getRecoveryTime());
	}

}
