package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a US-37: ligações para aplicacoes Android e iOS.
 */
public class UserStory37 {
    private final WebDriver driver;

    public UserStory37(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Confirma a existencia das duas lojas moveis.
     *
     * @return verdadeiro quando Google Play e App Store estiverem presentes
     */
    public boolean hasMobileAppLinks() {
        boolean googlePlay = driver.findElements(
                        By.cssSelector("a[href*='play.google.com/store/apps/details?id=io.papergames.app']"))
                .stream().anyMatch(org.openqa.selenium.WebElement::isDisplayed);
        boolean appStore = driver.findElements(
                        By.cssSelector("a[href*='apps.apple.com/app/papergames-io']"))
                .stream().anyMatch(org.openqa.selenium.WebElement::isDisplayed);
        return googlePlay && appStore;
    }
}
