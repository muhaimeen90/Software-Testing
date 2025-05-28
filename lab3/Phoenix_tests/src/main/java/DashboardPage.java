import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By boardEntry = By.cssSelector("#\\31--123-0 > .inner"); // Existing general board entry
    private By addMemberButton = By.cssSelector("li > .add-new");
    private By memberEmailField = By.id("crawljax_member_email");
    private By errorElement = By.cssSelector(".error");
    private By signOutButton = By.cssSelector("#crawler-sign-out > span");
    private By userNameDisplay = By.cssSelector("span:nth-child(3)");

    // New locator for checking if a member's gravatar is displayed
    private By addedMemberGravatar = By.cssSelector("li:nth-child(2) > .react-gravatar");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(boardEntry)).click();
    }

    /**
     * Clicks a specific board based on its ID.
     * @param boardId The ID of the board to click (e.g., "32-123").
     */
    public void clickBoardById(String boardId) {
        // Construct the CSS selector dynamically using the provided boardId
        By boardLocator = By.cssSelector("#\\" + boardId + " h4");
        wait.until(ExpectedConditions.elementToBeClickable(boardLocator)).click();
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

    public void clickSignOut() {
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton)).click();
    }

    public String getUserNameDisplay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameDisplay)).getText();
    }

    /**
     * Checks if the gravatar for an added member is displayed.
     * @return true if the gravatar element is found, false otherwise.
     */
    public boolean isMemberGravatarDisplayed() {
        // Use findElements to avoid throwing NoSuchElementException if the element is not immediately present.
        // Then check if the size of the returned list is greater than 0.
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addedMemberGravatar)).size() > 0;
    }
}