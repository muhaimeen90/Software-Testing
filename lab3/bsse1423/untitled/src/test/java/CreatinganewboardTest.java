import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CreatinganewboardTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private SignInPage signInPage;
  private BoardPage boardPage;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    signInPage = new SignInPage(driver, wait);
    boardPage = new BoardPage(driver, wait);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testCreatingANewBoard() {
    signInPage.open();
    signInPage.clickSignIn();

    boardPage.clickAddNewBoard();
    boardPage.setBoardName("@123!{0");
    boardPage.viewAllBoards();
    boardPage.openFirstBoard();

    assertEquals("@123!{0", boardPage.getBoardTitle());
  }
}
