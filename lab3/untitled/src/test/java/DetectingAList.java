import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class DetectingAList {
    private WebDriver driver;
    private WebDriverWait wait;
    private SignInPage signInPage;
    private ListPage listPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        signInPage = new SignInPage(driver, wait);
        listPage = new ListPage(driver, wait);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAddingAList() {
        signInPage.open();
        signInPage.clickSignIn();

        listPage.openBoard();
        listPage.clickAddList();
        listPage.enterListName("123");

        assertEquals("123", listPage.getCreatedListName());
    }
}
