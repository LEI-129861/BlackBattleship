package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a US-07: escolha do modo de jogo no ecra inicial.
 */
public class UserStory07 {
    private final WebDriver driver;

    public UserStory07(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifica se os modos de jogo principais ficam visiveis na pagina.
     *
     * @return verdadeiro se os quatro modos estiverem apresentados
     */
    public boolean hasAllGameModes() {
        return isDisplayed("//button[contains(normalize-space(.), 'Play with a friend')]")
                && isDisplayed("//button[contains(normalize-space(.), 'Play vs robot')]")
                && isDisplayed("//button[contains(normalize-space(.), 'Play online')]")
                && isDisplayed("//main//a[contains(normalize-space(.), 'Create tournament')]");
    }

    public boolean areAllGameModesVisibleWithoutScrolling() {
        return isWithinViewport("//button[contains(normalize-space(.), 'Play with a friend')]")
                && isWithinViewport("//button[contains(normalize-space(.), 'Play vs robot')]")
                && isWithinViewport("//button[contains(normalize-space(.), 'Play online')]")
                && isWithinViewport("//main//a[contains(normalize-space(.), 'Create tournament')]");
    }

    private boolean isDisplayed(String xpath) {
        return driver.findElements(By.xpath(xpath)).stream()
                .anyMatch(WebElement::isDisplayed);
    }

    private boolean isWithinViewport(String xpath) {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        return driver.findElements(By.xpath(xpath)).stream()
                .filter(WebElement::isDisplayed)
                .anyMatch(element -> Boolean.TRUE.equals(javascript.executeScript(
                        "const rect = arguments[0].getBoundingClientRect();"
                                + "return rect.width > 0 && rect.height > 0"
                                + " && rect.top >= 0 && rect.left >= 0"
                                + " && rect.bottom <= window.innerHeight"
                                + " && rect.right <= window.innerWidth;",
                        element)));
    }
}
