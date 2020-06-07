package com.vts.qa.testcases;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import com.vts.qa.pages.*;
import com.vts.qa.util.TestUtil;

public class homePageTest extends TestUtil {

	HomePage homepage;

	public homePageTest() {
		super();// Calls the TestBase class
		log.info("************************** VTS Home Page TEST  *****************************");
	}

	@BeforeTest
	public void setUp() throws MalformedURLException, InterruptedException {
		try {
			log.info("Initalizing the HomePage with all fields");
			intialization();
			homepage = new HomePage(d);

		} catch (NullPointerException e) {
			log.error("Unable to load homepage" + e);

		}
	}

	@Test
	public void HomePageTest() throws InterruptedException, IOException {
		try {
			log.info("Executing Accept Cookies Test Scenario");
			homepage.acceptCookie();

			log.info("Executing Verify VTS Logo Test Scenario");
			homepage.verifyVTSLogo();

			log.info("Executing HomePage Menu Mouse Hover Test Scenario");
			homepage.hoverVTSMenu();

			log.info("Executing Verify VTS menu options Test Scenario");
			homepage.verifyVTSMenu();

			log.info("Executing Free Demo Test Scenario");
			homepage.freeDemo("Rupesh", "Wayal", "rwayal@gmail.com");
			homepage.verifyFreeDemo();

			log.info("Executing Verify ChatBot Test Scenario");
			homepage.verifyChatBot();

			log.info("Executing Verify Broken links Test Scenario");
			homepage.verifyBrokenLinks();

		} catch (Exception e) {
			log.error("HomePage Test Failed");

		}
	}

	@AfterClass
	public void Quit_Browser_Test() {
		try {

			log.info("Closing the current browser session");
			d.quit();
			log.info("******************END**********************");
			log.info("browser closed");
		} catch (Exception e) {
			log.error("browser not closed");
		}
	}
}
