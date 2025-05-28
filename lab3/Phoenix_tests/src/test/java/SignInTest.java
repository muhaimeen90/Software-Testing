import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
public class SignInTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private SignInPage signInPage;
    private DashboardPage dashboardPage; // Assuming DashboardPage is shown after sign-in

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        signInPage = new SignInPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void signInWithNewAccount() {
        signInPage.open();
        signInPage.enterEmail("123@m.com"); // Use the email from the new account created in previous test
        signInPage.enterPassword("12345"); // Use the password from the new account
        signInPage.clickSignIn();

        // Verify that the user's name is displayed after successful sign-in
        assertEquals("12 23", dashboardPage.getUserNameDisplay());

        // Click on the sign out button
        dashboardPage.clickSignOut();
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
    @Test
    public void defaultSignIn() {
        signInPage.open();
        signInPage.clickSignIn(); // Click sign-in without entering credentials
        // Assert that "John Doe" is displayed as the user's name after default sign-in
        assertEquals("John Doe", dashboardPage.getUserNameDisplay());
    }

    @Test
    public void signInWithoutEmail() {
        driver.get("http://localhost:4000/sign_in");
        assertThat(signInPage.getEmailValidationMessage(), is("Please fill out this field."));
    }
}