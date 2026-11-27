package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    private static final By PLAY_VS_ROBOT = By.xpath("//button[normalize-space(.)='Play vs robot']");
    private static final By NICKNAME_INPUT = By.cssSelector("input[placeholder='Nickname']");
    private static final By CONTINUE_BUTTON = By.xpath("//button[normalize-space(.)='Continue']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(APP_URL + "/battleship");
        wait.until(ExpectedConditions.elementToBeClickable(PLAY_VS_ROBOT));
        return this;
    }

    public GamePage playAgainstRobot(String nickname) {
        clickWithConsentHandling(PLAY_VS_ROBOT);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> hasVisibleNicknamePrompt()
                        || d.getCurrentUrl().matches(".*/en/r/[^/]+$"));

        List<WebElement> nicknameInputs = driver.findElements(NICKNAME_INPUT);
        if (!nicknameInputs.isEmpty() && nicknameInputs.get(0).isDisplayed()) {
            WebElement nicknameInput = nicknameInputs.get(0);
            nicknameInput.clear();
            nicknameInput.sendKeys(nickname);
            clickWithConsentHandling(CONTINUE_BUTTON);
            wait.until(d -> d.getCurrentUrl().matches(".*/en/r/[^/]+$"));
        }

        GamePage gamePage = new GamePage(driver);
        gamePage.waitForBoards();
        return gamePage;
    }

    private boolean hasVisibleNicknamePrompt() {
        List<WebElement> nicknameInputs = driver.findElements(NICKNAME_INPUT);
        return !nicknameInputs.isEmpty() && nicknameInputs.get(0).isDisplayed();
    }
}
