import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signInButton = By.cssSelector("button");
    private By emailField = By.id("user_email"); // New locator for email field
    private By passwordField = By.id("user_password");
    private By errorMessage = By.cssSelector(".error");
    private By createNewAccountLink = By.linkText("Create new account");

    public SignInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get("http://localhost:4000/sign_in");
    }

    /**
     * Enters the email into the email field.
     * @param email The email to enter.
     */
    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void clickCreateNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewAccountLink)).click();
    }

    public String getEmailValidationMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).clear();
        return wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).getAttribute("validationMessage");
    }
}