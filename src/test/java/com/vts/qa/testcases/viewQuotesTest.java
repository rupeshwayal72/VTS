package com.vts.qa.testcases;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import com.vts.qa.pages.*;
import com.vts.qa.util.TestUtil;

public class viewQuotesTest extends TestUtil {

	carInsurance carInsurance;

	public viewQuotesTest() {
		super();// Calls the TestBase class
		log.info("************************** Coverfox Car Insurance View Quotes TEST  *****************************");
	}

	@BeforeTest
	public void setUp() throws MalformedURLException, InterruptedException {
		try {
			log.info("Initalizing the coverfox car insurance with all fields");
			intialization();
			carInsurance = new carInsurance(d);

		} catch (NullPointerException e) {
			log.error("Unable to load coverfox car insurance" + e);

		}
	}

	@Test
	public void viewQuoteTest() throws InterruptedException, IOException {
		try {
			carInsurance.clickHere(0);
			carInsurance.selectCarBrand(0);
			
			d.navigate().refresh();
			carInsurance.selectCarModel(0);
			carInsurance.selectCarFuelType(0);

			carInsurance.selectCarVariant(0);

			carInsurance.carRegisterationDetails("MH-04  Thane", "2019");
			carInsurance.selectPolicyExpiry("Expired", "Yes", "Yes");
			carInsurance.viewQuotes();
		} catch (Exception e) {
			log.error("coverfox car insurance view quotes Test Failed");

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
