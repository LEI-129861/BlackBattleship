package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected static final String APP_URL = "https://papergames.io/en";

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    private static final By CONSENT_OVERLAY = By.cssSelector(".fc-dialog-overlay");
    private static final By MANAGE_CONSENT = By.xpath("//button[normalize-space(.)='Manage options']");
    private static final By CONFIRM_CONSENT = By.xpath("//button[normalize-space(.)='Confirm choices']");

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public StorePage openStore() {
        By shopLink = By.cssSelector("a[href='/en/shop']");
        clickWithConsentHandling(shopLink);
        wait.until(d -> d.getCurrentUrl().contains("/en/shop"));
        return new StorePage(driver);
    }

    protected void clickWithConsentHandling(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException clickException) {
            if (!dismissConsentDialog()) {
                throw clickException;
            }
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }
    }

    private boolean dismissConsentDialog() {
        if (driver.findElements(CONSENT_OVERLAY).isEmpty()) {
            return false;
        }

        List<WebElement> manageButtons = driver.findElements(MANAGE_CONSENT);
        if (manageButtons.isEmpty()) {
            return false;
        }

        wait.until(ExpectedConditions.elementToBeClickable(MANAGE_CONSENT)).click();
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_CONSENT)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CONSENT_OVERLAY));
        return true;
    }
}
