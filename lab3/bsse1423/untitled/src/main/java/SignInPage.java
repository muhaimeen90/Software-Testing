import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signInButton = By.cssSelector("button");
    private By passwordField = By.id("user_password");
    private By errorMessage = By.cssSelector(".error");

    public SignInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get("http://localhost:4000/sign_in");
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
}
