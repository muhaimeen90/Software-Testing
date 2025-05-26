import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addNewBoardBtn = By.id("add_new_board");
    private By boardNameField = By.id("board_name");
    private By boardsNav = By.cssSelector("#boards_nav span");
    private By viewAllBoards = By.linkText("View all boards");
    private By firstBoard = By.cssSelector("h4");
    private By boardHeader = By.cssSelector("h3");

    public BoardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickAddNewBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewBoardBtn)).click();
    }

    public void setBoardName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(boardNameField));
        nameField.clear();
        nameField.sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void viewAllBoards() {
        wait.until(ExpectedConditions.elementToBeClickable(boardsNav)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewAllBoards)).click();
    }

    public void openFirstBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(firstBoard)).click();
    }

    public String getBoardTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(boardHeader)).getText();
    }
}
