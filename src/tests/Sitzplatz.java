package tests;
import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.TestBase;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sitzplatz extends TestBase{


	/**
	 * Mit logger.info gibt man 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		initLoggger(null,Level.INFO);
		logger.info("setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info("tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception {
		logger.info("SetUp");
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown");
	}

	@Test
	public void test() {
		assertEquals("Test 1 will work",1,2);
	}


}
