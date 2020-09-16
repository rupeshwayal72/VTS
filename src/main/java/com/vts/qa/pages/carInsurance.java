package com.vts.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.vts.qa.base.TestBase;

public class carInsurance extends TestBase {
	WebDriverWait wait = new WebDriverWait(d, 30);
	Actions actions = new Actions(d);
	Keyboard keyboard = ((HasInputDevices) d).getKeyboard();
	JavascriptExecutor executor = (JavascriptExecutor) d;

	// Don't know car number click here link
	@CacheLookup
	@FindBy(xpath = "//*[@class='flow-links__item']")
	List<WebElement> noCarNumberLink;

	// Car brands displayed on popup window(After clicking on Don't know car number
	// click here link)
	@CacheLookup
	@FindBy(xpath = "//*[@class='top-make__item ']")
	List<WebElement> topCarBrands;

	// Car models displayed on popup window(After selecting Car brands)
	@CacheLookup
	@FindBy(xpath = "//*[@class='top-models__item ']")
	List<WebElement> topCarModels;

	// Fuel Type radio buttons
	@CacheLookup
	@FindBy(xpath = "//*[@id='content']/div/div[2]/span/div/div/div[1]/div/div/div[3]/div[3]/span/div/div/div/div[2]/ul/li")
	List<WebElement> carFuelType;

	// Car model variant displayed on popup window(After selecting Car fuel type)
	@CacheLookup
	@FindBy(xpath = "//*[@class='variants-list__item ']")
	List<WebElement> modelVariant;

	// car registered location displayed on popup window
	@CacheLookup
	@FindBy(xpath = "(//*[@class='dd_placeholder'])[1]")
	WebElement carRegLocation;

	// car registered year displayed on popup window
	@CacheLookup
	@FindBy(xpath = "(//*[@class='dd_placeholder'])[2]")
	WebElement carRegYear;

	// Searchbox list of car policy registeration details
	@CacheLookup
	@FindBy(xpath = "//*[@id='SearchBox']")
	List<WebElement> searchBox;

	// car expired/not expired radio buttons displayed on popup window
	@CacheLookup
	@FindBy(xpath = "//*[@class='w--radio__option ']")
	List<WebElement> radioButtons;

	// mobile number field displayed on popup window(optional)
	@CacheLookup
	@FindBy(xpath = "//*[@type='tel' and @maxlength='10']")
	WebElement mobileNumber;

	// view quotes button
	@CacheLookup
	@FindBy(xpath = "//*[@title='View Quotes']")
	WebElement viewQuotesBtn;

	public carInsurance(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public void clickHere(int linkId) {
		try {
			log.info("Clicking on Click here link");
			wait.until(ExpectedConditions.elementToBeClickable(noCarNumberLink.get(linkId)));
			noCarNumberLink.get(linkId).click();
		} catch (Exception e) {
			Assert.fail("Failed to click on Click here link");
		}
	}

	public void selectCarBrand(int brand) {
		try {
			log.info("selecting car brand");
			wait.until(ExpectedConditions.elementToBeClickable(topCarBrands.get(brand)));
			topCarBrands.get(brand).click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		} catch (Exception e) {
			Assert.fail("selecting car brand failed...!!");
		}
	}

	public void selectCarModel(int model) {
		try {
			log.info("selecting car model");
			wait.until(ExpectedConditions.elementToBeClickable(topCarModels.get(model)));
			topCarModels.get(model).click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		} catch (Exception e) {
			Assert.fail("selecting car model failed...!!");
		}
	}

	public void selectCarFuelType(int fuelType) {
		try {
			log.info("selecting car fuel type");
			wait.until(ExpectedConditions.elementToBeClickable(carFuelType.get(fuelType)));
			carFuelType.get(fuelType).click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		} catch (Exception e) {
			Assert.fail("selecting car fuel type failed...!!");
		}
	}

	public void selectCarVariant(int variant) {
		try {
			log.info("selecting car variant");
			wait.until(ExpectedConditions.elementToBeClickable(modelVariant.get(variant)));
			modelVariant.get(variant).click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		} catch (Exception e) {
			Assert.fail("selecting car variant failed...!!");
		}
	}

	public void carRegisterationDetails(String regLocn, String regYear) {
		try {
			log.info("selecting car registeration location");
			wait.until(ExpectedConditions.elementToBeClickable(searchBox.get(0)));

			actions.moveToElement(searchBox.get(0)).click().perform();
			searchBox.get(0).sendKeys(regLocn, Keys.ENTER);
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");

				log.info("selecting car registeration year");
				wait.until(ExpectedConditions.elementToBeClickable(searchBox.get(1)));
				actions.moveToElement(searchBox.get(1)).click().perform();
				searchBox.get(1).sendKeys(regYear, Keys.ENTER);

				executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
	

		} catch (

		Exception e) {
			Assert.fail("selecting car Registeration Details failed...!!");
		}
	}

	public void selectPolicyExpiry(String expiry, String claim, String expiredPolicyDetails) {
		try {
			log.info("selecting car policy expiry");
			WebElement expiryInfo = d.findElement(By.xpath("//*[text()='" + expiry + "']"));
			wait.until(ExpectedConditions.elementToBeClickable(expiryInfo));
			expiryInfo.click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

			if (expiry.contentEquals("Not Expired")) {
				madeAClaim(claim);
				executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

			}

			else if (expiry.contentEquals("Expired")) {
				log.info("Selecting poicy expired info as: " + expiredPolicyDetails);
				WebElement expiredPolicyInfo = d.findElement(By.xpath("//*[text()='" + expiredPolicyDetails + "']"));
				wait.until(ExpectedConditions.elementToBeClickable(expiredPolicyInfo));
				expiredPolicyInfo.click();
				executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

				if (expiredPolicyDetails.contentEquals("Yes")) {
					madeAClaim(claim);
					executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

				} else {
					log.info("Previous policy might be expired before before 18th June 2020");
				}
			}

		} catch (Exception e) {
			Assert.fail("selecting car previous poicy expiry details failed...!!");
		}
	}

	public void madeAClaim(String claimDecision) {
		try {
			log.info("Selecting car made a claim option as: " + claimDecision);
			WebElement claim = d.findElement(By.xpath("(//*[text()='" + claimDecision + "'])[2]"));
			wait.until(ExpectedConditions.elementToBeClickable(claim));
			claim.click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		} catch (Exception e) {
			Assert.fail("failed to select car made a claim radio button...!!");
		}
	}

	public void viewQuotes() {
		try {
			log.info("Clicking on view quotes button");
			wait.until(ExpectedConditions.elementToBeClickable(viewQuotesBtn));
			viewQuotesBtn.click();
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2500);");

		} catch (Exception e) {
			Assert.fail("Clicking on view quotes button failed...!!");
		}
	}

}