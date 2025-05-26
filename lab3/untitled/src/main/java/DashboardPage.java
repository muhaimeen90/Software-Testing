import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By boardEntry = By.cssSelector("#\\31--123-0 > .inner");
    private By addMemberButton = By.cssSelector("li > .add-new");
    private By memberEmailField = By.id("crawljax_member_email");
    private By errorElement = By.cssSelector(".error");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(boardEntry)).click();
    }

    public void clickAddMember() {
        wait.until(ExpectedConditions.elementToBeClickable(addMemberButton)).click();
    }

    public void addMemberEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(memberEmailField));
        emailField.clear();
        emailField.sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public String getAddMemberError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorElement)).getText();
    }
}
