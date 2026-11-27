package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RulesPage extends BasePage {
    private static final By RULES_HEADING = headingContaining("rules");
    private static final By WEAPONS_HEADING = headingContaining("weapons");
    private static final By STRATEGY_HEADING = headingContaining("strategy");

    public RulesPage(WebDriver driver) {
        super(driver);
    }

    public RulesPage open() {
        driver.get(APP_URL + "/battleship");
        wait.until(ExpectedConditions.visibilityOfElementLocated(RULES_HEADING));
        return this;
    }

    public boolean hasRulesWeaponsAndStrategy() {
        return wait.until(d -> d.findElement(RULES_HEADING).isDisplayed()
                && d.findElement(WEAPONS_HEADING).isDisplayed()
                && d.findElement(STRATEGY_HEADING).isDisplayed());
    }

    public boolean hasCenterAndCheckerboardAdvice() {
        String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();
        return pageText.contains("center") && pageText.contains("every other square");
    }

    private static By headingContaining(String text) {
        return By.xpath("//*[self::h2 or self::h3][contains(translate(normalize-space(.),"
                + " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
                + text + "')]");
    }
}
