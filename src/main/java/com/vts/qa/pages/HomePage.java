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
import com.vts.qa.util.TestUtil;

public class HomePage extends TestBase {
	WebDriverWait wait = new WebDriverWait(d, 10);
	Actions actions = new Actions(d);
	Keyboard keyboard = ((HasInputDevices) d).getKeyboard();
	JavascriptExecutor executor = (JavascriptExecutor) d;

	// Accept Cookies Button
	@CacheLookup
	@FindBy(xpath = "//*[text()='ACCEPT']")
	WebElement acceptCookies;

	// VTS logo
	@CacheLookup
	@FindBy(xpath = "//*[@class='header-logo']")
	WebElement vtsLogo;

	// VTS menu tabs displayed on homepage
	@CacheLookup
	@FindBy(xpath = "//*[@class='header-navigation__link']")
	List<WebElement> vtsMenu;

	// VTS menu submenu displayed under menu on homepage
	@CacheLookup
	@FindBy(xpath = "//*[@class='header-nav__item']")
	List<WebElement> vtsSubMenu;

	// VTS market review option under product tab
	@CacheLookup
	@FindBy(xpath = "(//*[@href='https://www.vts.com/market-view'])[1]")
	WebElement marketReview;

	// VTS market review logo on VTS market review page
	@CacheLookup
	@FindBy(xpath = "//*[@class='intro-headline__icon']")
	WebElement marketReviewLogo;

	// request demo button
	@CacheLookup
	@FindBy(xpath = "(//*[@href='#request-demo-popup'])[1]")
	WebElement requestDemo;

	// request demo first name
	@CacheLookup
	@FindBy(xpath = "//*[@id='FirstName']")
	WebElement firstName;

	// request demo last name
	@CacheLookup
	@FindBy(xpath = "//*[@id='LastName']")
	WebElement lastName;

	// request demo work email
	@CacheLookup
	@FindBy(xpath = "//*[@id='Email']")
	WebElement workEmail;

	// send promotional mail confirmation checkbox
	@CacheLookup
	@FindBy(xpath = "// *[@id='GDPR_Opt_In__c']")
	WebElement confirmSendMail;

	// submit button of request demo registration popup
	@CacheLookup
	@FindBy(xpath = "//*[@type='submit']")
	WebElement submitButton;

	// request demo registration success message
	@CacheLookup
	@FindBy(xpath = "(//*[@class='section-head__title section-head__title_contact_headline section-head__title_center'])[2]")
	WebElement successDemoMsg1;

	public HomePage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	public void acceptCookie() {
		try {
			log.info("Verify Accept Cookies Scenario");
			WebElement frame = d.findElement(By.id("ifrmCookieBanner"));
			d.switchTo().frame(frame);
			acceptCookies.click();
			log.info("Clicked on Accept Cookies Button");
			d.switchTo().defaultContent();
		} catch (Exception e) {
			Assert.fail("Failed to click on Accept Cookies Button");
		}
	}

	public void verifyVTSLogo() {
		try {
			log.info("Verify VTS logo");
			wait.until(ExpectedConditions.visibilityOf(vtsLogo));
			vtsLogo.isDisplayed();
			log.info("VTS logo displayed on homepage");
		} catch (Exception e) {
			Assert.fail("VTS logo not found!!!!");
		}
	}

	public void hoverVTSMenu() {
		try {
			log.info("Mouse Hover over VTS Menu");
			wait.until(ExpectedConditions.visibilityOfAllElements(vtsMenu));
			for (WebElement menu : vtsMenu) {
				actions.moveToElement(menu).build().perform();
				log.info("Hovering mouse over: " + menu.getText());
				Thread.sleep(300);

				if (menu.getText().contentEquals("PRODUCT")) {
					for (int i = 0; i <= 2; i++) {
						actions.moveToElement(vtsSubMenu.get(i)).build().perform();
						log.info("Hovering mouse over: " + vtsSubMenu.get(i).getText());
						Thread.sleep(300);
					}
				}

				else if (menu.getText().contentEquals("SOLUTIONS")) {
					for (int i = 3; i <= 7; i++) {
						actions.moveToElement(vtsSubMenu.get(i)).build().perform();
						log.info("Hovering mouse over: " + vtsSubMenu.get(i).getText());
						Thread.sleep(300);
					}

				}

				else if (menu.getText().contentEquals("SERVICES")) {
					for (int i = 8; i <= 9; i++) {
						actions.moveToElement(vtsSubMenu.get(i)).build().perform();
						log.info("Hovering mouse over: " + vtsSubMenu.get(i).getText());
						Thread.sleep(500);
					}
				} else if (menu.getText().contentEquals("RESOURCES")) {
					for (int i = 10; i <= 13; i++) {
						actions.moveToElement(vtsSubMenu.get(i)).build().perform();
						log.info("Hovering mouse over: " + vtsSubMenu.get(i).getText());
						Thread.sleep(300);
					}

				} else if (menu.getText().contentEquals("EVENTS")) {
					actions.moveToElement(vtsSubMenu.get(14)).build().perform();
					log.info("Hovering mouse over: " + vtsSubMenu.get(14).getText());
					Thread.sleep(300);
				}
			}

		} catch (Exception e) {
			Assert.fail("Failed to mouse hover over VTS homepage menu tab");
		}
	}

	public void verifyVTSMenu() {
		try {
			log.info("Verify Verify product VTS marketReview ");
			log.info("Clicking on VTS market review option under product.");
			actions.moveToElement(vtsMenu.get(0)).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(marketReview));
			marketReview.click();
			log.info("Clicked on VTS market review option under product.");
			executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

			log.info("Verify displayed market review logo on VTS marketReview page");
			wait.until(ExpectedConditions.visibilityOf(marketReviewLogo));
			marketReviewLogo.isDisplayed();
			log.info("Market review logo is displayed on VTS marketReview page");
			d.navigate().back();
		} catch (Exception e) {
			Assert.fail("Verify product VTS marketReview test Failed");
		}
	}

	public void verifyBrokenLinks() {
		try {
			log.info("Identifying all active/broken hyperlinks on homepage");
			TestUtil.verifyLinkActive(d);
		} catch (Exception e) {
			Assert.fail("Failed to identify active/broken hyperlinks on homepage");
		}
	}

	public void freeDemo(String fName, String lName, String eMail) {
		try {
			log.info("Verify Request Demo feature");
			log.info("Clicking on Free demo button");
			wait.until(ExpectedConditions.elementToBeClickable(requestDemo));
			requestDemo.click();

			log.info("Clicked on Free demo button");
			d.switchTo().activeElement();

			log.info("Enter First Name");
			wait.until(ExpectedConditions.elementToBeClickable(firstName));
			firstName.sendKeys(fName);
			log.info("Entered First Name");

			log.info("Enter Last Name");
			wait.until(ExpectedConditions.elementToBeClickable(lastName));
			lastName.sendKeys(lName);
			log.info("Entered Last Name");

			log.info("Enter workspace email");
			wait.until(ExpectedConditions.elementToBeClickable(workEmail));
			workEmail.sendKeys(eMail);
			log.info("Entered Work Email");

			log.info("Clicking on confirm send news letter,emails checkbox");
			wait.until(ExpectedConditions.elementToBeClickable(confirmSendMail));
			confirmSendMail.click();
			log.info("Clicked on confirm send news letter,emails checkbox");

			log.info("Clicking on Submit Button");
			wait.until(ExpectedConditions.elementToBeClickable(submitButton));
			submitButton.click();
			log.info("Clicked on Submit Button");

		} catch (Exception e) {
			Assert.fail("Verify Request Demo Scenario failed");
		}
	}

	public void verifyFreeDemo() {
		try {
			log.info("Verify Request Demo success message");
			wait.until(ExpectedConditions.elementToBeClickable(successDemoMsg1));
			if (successDemoMsg1.getText().contentEquals("Thank you for your demo request.")) {
				log.info("Free Demo request submitted successfully...!!!!");
				actions.sendKeys(Keys.ESCAPE).perform();
			} else {
				Assert.fail("Free Demo request submittion Failed...!!!!");

			}

		} catch (Exception e) {
			Assert.fail("Free Demo request submittion Failed...!!!!");
		}
	}

	public void verifyChatBot() {
		try {
			log.info("Verify chatBot Scenario");
			log.info("Clicking on chatBot Icon");
			WebElement botFrame = d.findElement(By.id("drift-widget"));
			d.switchTo().frame(botFrame);

			WebElement chatBotIcon = d.findElement(By.id("widgetButton"));
			wait.until(ExpectedConditions.elementToBeClickable(chatBotIcon));
			chatBotIcon.click();
			log.info("Clicked on ChatBot Icon");

			d.switchTo().activeElement();
			WebElement botMsg1 = d.findElement(By.xpath("//*[@aria-label='Sure, go for it! - Button']"));
			log.info("Clicking on Sure, go for it!");
			wait.until(ExpectedConditions.elementToBeClickable(botMsg1));
			botMsg1.click();

			WebElement botMsg2 = d
					.findElement(By.xpath("(//*[@class='MessageBodyWrapper MessageBodyWrapper--left'])[2]"));
			wait.until(ExpectedConditions.elementToBeClickable(botMsg2));

			if (botMsg2.getText().contentEquals(
					"What if I told you that you could easily convert twice the amount of leads to leases 41% faster?")) {

				log.info("ChatBot Responding to choices as expected");

				WebElement botMsg3 = d.findElement(By.xpath("//*[@aria-label='That would be great! - Button']"));
				log.info("clicking on That would be great! - Button");
				botMsg3.click();
			}

			else {
				Assert.fail("ChatBot is not working properly");
				log.error("Verify ChatBot scenario failed");
			}
		} catch (Exception e) {
			Assert.fail("ChatBot is not working properly");
			log.error("Verify ChatBot scenario failed");

		}
	}

}