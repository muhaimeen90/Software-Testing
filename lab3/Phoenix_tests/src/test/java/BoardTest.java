import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions; // Import FirefoxOptions
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import org.junit.FixMethodOrder; // Needed for ordering in JUnit 4

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private SignInPage signInPage;
  private BoardPage boardPage;
  private ListPage listPage; // Declared here
  private DashboardPage dashboardPage;

  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();

    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

    driver = new FirefoxDriver(options);

    driver.manage().window().maximize();

    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    signInPage = new SignInPage(driver, wait);
    boardPage = new BoardPage(driver, wait);
    listPage = new ListPage(driver, wait); // <--- ADDED: Initialization for listPage
    dashboardPage = new DashboardPage(driver, wait);
  }

  @After
  public void tearDown() {
    // Ensure the driver is not null before quitting, in case setUp failed
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void test01creatingANewBoard() {
    signInPage.open();
    signInPage.clickSignIn(); // Perform default sign-in as per the original test

    boardPage.clickAddNewBoard();
    boardPage.setBoardName("123"); // Set the board name

    // Assert that the board title is displayed correctly
    assertEquals("123", boardPage.getBoardTitle());
  }
  @Test
  public void test02addingAList() {
    signInPage.open();
    signInPage.clickSignIn(); // Perform default sign-in

    listPage.openBoard(); // Opens an available board, consistent with existing tests
    listPage.clickAddList();
    listPage.enterListName("123");

    // Assert that the created list's name is displayed correctly
    assertEquals("123", listPage.getCreatedListName());
  }

  @Test
  public void test03updatingList() {
    signInPage.open();
    signInPage.clickSignIn(); // Perform default sign-in

    listPage.openBoard(); // Open an existing board
    // Now, click the list header to enable editing and update the name
    listPage.clickListHeader();
    listPage.enterListName("1234"); // Update the list name

    // Assert that the list name has been updated correctly
    assertEquals("1234", listPage.getCreatedListName());
  }


}