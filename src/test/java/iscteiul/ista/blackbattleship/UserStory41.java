package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a US-41: acesso a privacidade e termos.
 */
public class UserStory41 {
    private final WebDriver driver;

    public UserStory41(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Confirma que as ligacoes legais existem no rodape.
     *
     * @return verdadeiro se Privacy e Terms &amp; conditions estiverem presentes
     */
    public boolean hasPrivacyAndTermsLinks() {
        return isVisible("a[href='/en/blog/privacy-policy']")
                && isVisible("a[href='/en/blog/terms-conditions']");
    }

    private boolean isVisible(String css) {
        return driver.findElements(By.cssSelector(css)).stream()
                .anyMatch(WebElement::isDisplayed);
    }
}
