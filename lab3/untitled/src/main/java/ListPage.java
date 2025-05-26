import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By boardCard = By.cssSelector("#\\33--123-0 h4");
    private By addListBtn = By.cssSelector(".inner");
    private By listNameField = By.id("list_name");
    private By submitButton = By.cssSelector("button");
    private By createdListHeader = By.cssSelector("h4");

    public ListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(boardCard)).click();
    }

    public void clickAddList() {
        wait.until(ExpectedConditions.elementToBeClickable(addListBtn)).click();
    }

    public void enterListName(String listName) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(listNameField));
        nameField.clear();
        nameField.sendKeys(listName);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getCreatedListName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createdListHeader)).getText();
    }
}
