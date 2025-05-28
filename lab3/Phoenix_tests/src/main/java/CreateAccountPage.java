import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for elements on the create account page
    private By firstNameField = By.id("user_first_name");
    private By lastNameField = By.id("user_last_name");
    private By emailField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By passwordConfirmationField = By.id("user_password_confirmation");
    private By submitButton = By.cssSelector("button");
    private By accountNameDisplay = By.cssSelector("span:nth-child(3)"); // Locator for the displayed name after creation
    private By errorMessage = By.cssSelector(".error"); // New locator for error messages on this page

    public CreateAccountPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Enters the first name into the first name field.
     * @param firstName The first name to enter.
     */
    public void enterFirstName(String firstName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        field.clear();
        field.sendKeys(firstName);
    }

    /**
     * Enters the last name into the last name field.
     * @param lastName The last name to enter.
     */
    public void enterLastName(String lastName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        field.clear();
        field.sendKeys(lastName);
    }

    /**
     * Enters the email into the email field.
     * @param email The email to enter.
     */
    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        field.clear();
        field.sendKeys(email);
    }

    /**
     * Enters the password into the password field.
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    /**
     * Enters the password confirmation into the password confirmation field.
     * @param passwordConfirmation The password confirmation to enter.
     */
    public void enterPasswordConfirmation(String passwordConfirmation) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordConfirmationField));
        field.clear();
        field.sendKeys(passwordConfirmation);
    }

    /**
     * Clicks the submit button to create the account.
     */
    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    /**
     * Retrieves the displayed account name after successful creation.
     * @return The text of the account name display element.
     */
    public String getAccountNameDisplay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountNameDisplay)).getText();
    }

    /**
     * Retrieves the error message displayed on the account creation page.
     * @return The text of the error message element.
     */
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}