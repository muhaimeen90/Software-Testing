import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class InvalidsigninTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private SignInPage signInPage;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    signInPage = new SignInPage(driver, wait);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testInvalidSignin() {
    signInPage.open();

    // First attempt with wrong password
    signInPage.enterPassword("123");
    signInPage.clickSignIn();

    // Second attempt with another wrong password
    signInPage.enterPassword("admin");
    signInPage.clickSignIn();

    // Verify error
    assertEquals("Invalid email or password", signInPage.getErrorMessage());
  }
}
