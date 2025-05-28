import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for existing functionalities
    private By boardCard = By.cssSelector("h4"); // More generic selector for a board title
    private By addListBtn = By.cssSelector(".inner");
    private By listNameField = By.id("list_name");
    private By submitButton = By.cssSelector("button"); // Generic submit button
    private By createdListHeader = By.cssSelector("h4"); // Used for list name assertion
    private By specificListToOpen = By.cssSelector("#\\32-123 h4"); // To click a specific list by ID for card view
    private By addNewCardLink = By.linkText("Add a new card...");
    private By cardNameField = By.id("card_name");
    private By cardContent = By.cssSelector(".card-content"); // To click the created card
    private By cardInfoTitle = By.cssSelector(".info h3"); // To get the title from the card details
    private By membersLink = By.linkText("Members");
    private By firstMemberToAdd = By.cssSelector("ul:nth-child(2) > li:nth-child(1) > a");
    private By cardMemberGravatar = By.cssSelector(".react-gravatar:nth-child(2)");
    private By tagsLink = By.linkText("Tags");
    private By greenTag = By.cssSelector(".green"); // Selector for the green tag
    private By addedTag = By.cssSelector(".tag:nth-child(2)"); // Selector for the added tag on card
    private By cardContentFooter = By.cssSelector(".card-content > footer"); // Element to click to reveal edit link
    private By editDescriptionLink = By.linkText("Edit");
    private By descriptionTextarea = By.cssSelector("textarea:nth-child(2)"); // The textarea for description input
    private By saveDescriptionButton = By.cssSelector("button:nth-child(3)"); // The save button for description
    private By displayedDescription = By.cssSelector("p"); // The element displaying the saved description

    // New locators for adding comments
    private By commentTextarea = By.cssSelector("textarea"); // The textarea for comment input
    private By commentSubmitButton = By.cssSelector("button"); // The submit button for comments (assuming it's generic)
    private By displayedCommentText = By.cssSelector(".text"); // The element displaying the saved comment

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

    public void clickListHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inner > header"))).click();
    }

    public void openListForCards() {
        wait.until(ExpectedConditions.elementToBeClickable(specificListToOpen)).click();
    }

    public void clickAddNewCard() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewCardLink)).click();
    }

    public void enterCardName(String cardName) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNameField));
        nameField.clear();
        nameField.sendKeys(cardName);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void clickCardContent() {
        wait.until(ExpectedConditions.elementToBeClickable(cardContent)).click();
    }

    public String getCardInfoTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardInfoTitle)).getText();
    }

    public void clickMembersLink() {
        wait.until(ExpectedConditions.elementToBeClickable(membersLink)).click();
    }

    public void selectFirstMemberToAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(firstMemberToAdd)).click();
    }

    public boolean isCardMemberGravatarDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cardMemberGravatar)).size() > 0;
    }

    public void clickTagsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(tagsLink)).click();
    }

    public void clickGreenTag() {
        wait.until(ExpectedConditions.elementToBeClickable(greenTag)).click();
    }

    public boolean isTagDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addedTag)).size() > 0;
    }

    public void clickCardContentFooter() {
        wait.until(ExpectedConditions.elementToBeClickable(cardContentFooter)).click();
    }

    public void clickEditDescriptionLink() {
        wait.until(ExpectedConditions.elementToBeClickable(editDescriptionLink)).click();
    }

    public void enterDescription(String description) {
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea));
        textarea.clear();
        textarea.sendKeys(description);
    }

    public void clickSaveDescriptionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveDescriptionButton)).click();
    }

    public String getDisplayedDescription() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(displayedDescription)).getText();
    }

    /**
     * Enters a comment into the comment textarea.
     * @param comment The comment text to enter.
     */
    public void enterComment(String comment) {
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea));
        textarea.clear();
        textarea.sendKeys(comment);
    }

    /**
     * Clicks the submit button for the comment.
     */
    public void clickCommentSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(commentSubmitButton)).click();
    }

    /**
     * Retrieves the text of the displayed comment.
     * @return The text of the comment element.
     */
    public String getDisplayedCommentText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(displayedCommentText)).getText();
    }
}