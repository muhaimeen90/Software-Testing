import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder; // Needed for ordering in JUnit 4

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private SignInPage signInPage;
    private ListPage listPage;
    private DashboardPage dashboardPage;
    private BoardPage boardPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize(); // Maximize window for consistency
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        signInPage = new SignInPage(driver, wait);
        listPage = new ListPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
        boardPage = new BoardPage(driver, wait);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test01creatingACard() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in

        listPage.openBoard(); // Open an existing board (assuming one is present)

        listPage.clickAddNewCard(); // Click "Add a new card..."
        listPage.enterCardName("1234"); // Enter card name and submit

        listPage.clickCardContent(); // Click the newly created card to open its details

        // Assert that the card title is displayed correctly in the details view
        assertEquals("1234", listPage.getCardInfoTitle());
    }

    @Test
    public void test02addMember() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in

        listPage.openBoard(); // Click on the specific board by ID

        dashboardPage.clickAddMember(); // Click the add member button
        dashboardPage.addMemberEmail("123@m.com"); // Enter the member's email

        // Assert that the new member's gravatar is displayed, indicating successful addition
        assertTrue("Member gravatar should be displayed after adding a member", dashboardPage.isMemberGravatarDisplayed());
    }


    @Test
    public void test03addingMemberOnCard() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in

       listPage.openBoard();
        listPage.clickCardContent(); // Click the newly created card to open its details

        listPage.clickMembersLink(); // Click the "Members" link in card details
        listPage.selectFirstMemberToAdd(); // Select the first available member

        // Assert that the member's gravatar is displayed on the card
        assertTrue("Member gravatar should be displayed on the card", listPage.isCardMemberGravatarDisplayed());
    }


    @Test
    public void test04addingATag() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in
        listPage.openBoard();

        listPage.clickCardContent(); // Click the newly created card to open its details

        listPage.clickTagsLink(); // Click the "Tags" link in card details
        listPage.clickGreenTag(); // Click the green tag option

        // Assert that the tag is displayed on the card
        assertTrue("Green tag should be displayed on the card", listPage.isTagDisplayed());
    }

    @Test
    public void test05addDescription() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in

       listPage.openBoard();

        listPage.clickCardContent(); // Click the newly created card to open its details

        listPage.clickEditDescriptionLink(); // Click the "Edit" link
        listPage.enterDescription("hello world"); // Enter the description text
        listPage.clickSaveDescriptionButton(); // Click the save button

        // Assert that the description is displayed correctly
        assertEquals("hello world", listPage.getDisplayedDescription());
    }

    @Test
    public void test06addComment() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in

        listPage.openBoard();

        listPage.clickCardContent(); // Click the newly created card to open its details

        listPage.enterComment("hi"); // Enter the comment text
        listPage.clickCommentSubmitButton(); // Click the comment submit button

        // Assert that the comment is displayed correctly
        assertEquals("hi", listPage.getDisplayedCommentText());
    }
    @Test
    public void test07deleteTag() {
        signInPage.open();
        signInPage.clickSignIn(); // Perform default sign-in
        listPage.openBoard();

        listPage.clickCardContent(); // Click the newly created card to open its details

        listPage.clickTagsLink(); // Click the "Tags" link in card details
        listPage.clickTagDelete(); // Click the green tag option// Click the green tag again to deselect/remove it

        // Step 3: Verify the tag is no longer displayed
        assertFalse("Green tag should NOT be displayed after deletion", listPage.isTagDisplayed());
    }

}