package com.rivetsystems.seleniumjunit;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.rivetsystems.seleniumjunit.pages.AmazonHome;

/**
 * Page Object pattern implementation of the default class generated by the
 * Selenium IDE
 * 
 * The test performed here is to go to amazon.com, search for "nigel slater",
 * click on the first link in the search results and verify that an ISBN is
 * found on the page with the book details
 */
public class AmazonTests {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void bookSearchTestISBN() {
		assertTrue(new AmazonHome(driver).searchFor("nigel slater").clickOnBookLink().isISBNFoundOnPage("1607740370"));
	}

	/**
	 * Take a screen shot after any failure (either assertion or exception)
	 */
	@Rule
	public TestWatcher watchman = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description d) {
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenShot, new File("c:\\tmp\\failedTest.png"));
			} catch (IOException ioe) {

			}
		}
	};
}
