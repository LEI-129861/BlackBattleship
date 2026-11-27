package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
                && isDisplayed("//a[contains(normalize-space(.), 'Create tournament')]");
    }

    private boolean isDisplayed(String xpath) {
        return driver.findElements(By.xpath(xpath)).stream()
                .anyMatch(element -> element.isDisplayed());
    }
}
