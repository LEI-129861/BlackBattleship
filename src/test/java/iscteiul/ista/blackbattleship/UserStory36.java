package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object para a US-36: utilizacao no navegador em computador e telemovel.
 */
public class UserStory36 {
    private static final By MAIN_HEADING = By.xpath("//h1[normalize-space()='Battleship Online']");

    private final WebDriver driver;

    public UserStory36(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifica se a pagina principal permanece utilizavel numa largura responsiva.
     *
     * @return verdadeiro se o titulo estiver visivel e nao existir overflow horizontal
     */
    public boolean isMainInterfaceVisible() {
        List<WebElement> headings = driver.findElements(MAIN_HEADING);
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        return headings.stream().anyMatch(WebElement::isDisplayed)
                && Boolean.TRUE.equals(javascript.executeScript(
                        "return document.documentElement.scrollWidth <= window.innerWidth;"));
    }
}
