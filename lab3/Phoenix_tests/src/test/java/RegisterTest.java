import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.FixMethodOrder; // Needed for ordering in JUnit 4
import org.junit.runners.MethodSorters;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private SignInPage signInPage;
    private CreateAccountPage createAccountPage; // New Page Object

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize(); // Maximize window as seen in other tests
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        signInPage = new SignInPage(driver, wait);
        createAccountPage = new CreateAccountPage(driver, wait); // Initialize new page object
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test1creatingANewAccount() {
        signInPage.open();
        signInPage.clickCreateNewAccount();

        createAccountPage.enterFirstName("12");
        createAccountPage.enterLastName("23");
        createAccountPage.enterEmail("123@m.com");
        createAccountPage.enterPassword("12345");
        createAccountPage.enterPasswordConfirmation("12345");
        createAccountPage.clickSubmit();

        // Assert the displayed name after account creation
        assertEquals("12 23", createAccountPage.getAccountNameDisplay());
    }

    @Test
    public void test2userExists() {
        signInPage.open();
        signInPage.clickCreateNewAccount();

        createAccountPage.enterFirstName("wqd");
        createAccountPage.enterLastName("asd");
        createAccountPage.enterEmail("123@m.com"); // Assuming this email already exists
        createAccountPage.enterPassword("123456");
        createAccountPage.enterPasswordConfirmation("123456");
        createAccountPage.clickSubmit();

        // Assert the error message for existing email
        assertEquals("Email already taken", createAccountPage.getErrorMessage());
    }


    @Test
    public void test3shortPassword() {
        signInPage.open();
        signInPage.clickCreateNewAccount();

        createAccountPage.enterFirstName("sad");
        createAccountPage.enterLastName("sda");
        createAccountPage.enterEmail("1233@1.com");
        createAccountPage.enterPassword("love"); // Password shorter than 5 characters
        createAccountPage.enterPasswordConfirmation("love");
        createAccountPage.clickSubmit();

        // Assert the error message for short password
        assertEquals("should be at least 5 character(s)", createAccountPage.getErrorMessage());
    }

    @Test
    public void test4passwordDontMatch() {
        signInPage.open();
        signInPage.clickCreateNewAccount();

        createAccountPage.enterFirstName("123");
        createAccountPage.enterLastName("123");
        createAccountPage.enterEmail("saa@as");
        createAccountPage.enterPassword("123456");
        createAccountPage.enterPasswordConfirmation("12983"); // Non-matching confirmation
        createAccountPage.clickSubmit();

        // Assert the error message for non-matching passwords
        assertEquals("Password does not match", createAccountPage.getErrorMessage());
    }
}