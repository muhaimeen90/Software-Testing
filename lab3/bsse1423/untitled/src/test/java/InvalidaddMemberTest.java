// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class InvalidaddMemberTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private SignInPage signInPage;
  private DashboardPage dashboardPage;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    signInPage = new SignInPage(driver, wait);
    dashboardPage = new DashboardPage(driver, wait);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testInvalidAddMember() {
    signInPage.open();
    signInPage.clickSignIn();
    dashboardPage.clickBoard();
    dashboardPage.clickAddMember();
    dashboardPage.addMemberEmail("user@123.com");
    assertEquals("User does not exist", dashboardPage.getAddMemberError());
  }
}
