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
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void invalidAddMember() {
    driver.get("http://localhost:4000/sign_in");
    driver.manage().window().setSize(new Dimension(1013, 691));

    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#\\31--123-0 > .inner"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li > .add-new"))).click();

    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crawljax_member_email")));
    emailField.click();
    emailField.sendKeys("user@123.com");

    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();

    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error")));
    assertThat(errorElement.getText(), is("User does not exist"));



  }
}
