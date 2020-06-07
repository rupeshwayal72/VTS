package com.vts.qa.util;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vts.qa.base.TestBase;

public class TestUtil extends TestBase {

	static String defaultdir = System.getProperty("user.dir");

	public static void takeScreenshotAtEndOfTest() throws IOException {
		String dateName = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());

		File scrFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		String currentDir = (defaultdir + "/screenshots/" + dateName);
		FileUtils.copyFile(scrFile, new File(currentDir + System.currentTimeMillis() + ".PNG"));

	}

	public static void scrollDown() {

		for (int i = 1; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) d;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		}
	}

	public static void scrollRight() {

		for (int i = 1; i <= 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) d;
			js.executeScript("window.scrollBy(2000,0)");

		}
	}

	public static int getRandomInteger(int maximum, int minimum)

	{
		return ((int) (Math.random() * (maximum - minimum))) + minimum;

	}

	public static void verifyLinkActive(WebDriver d) {
		List<WebElement> links = d.findElements(By.tagName("a"));

		System.out.println("Total links are " + links.size());

		for (int i = 0; i < links.size(); i++) {

			WebElement ele = links.get(i);

			String url = ele.getAttribute("href");

			verifyLinkActive(url);

		}

	}

	public static void verifyLinkActive(String linkUrl) {

		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				log.info(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}
