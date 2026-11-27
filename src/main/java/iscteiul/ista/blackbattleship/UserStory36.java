package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a US-36: utilizacao no navegador em computador e telemovel.
 */
public class UserStory36 {
    private final WebDriver driver;

    public UserStory36(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifica se a pagina principal permanece utilizavel numa largura responsiva.
     *
     * @return verdadeiro se o titulo principal estiver visivel
     */
    public boolean isMainInterfaceVisible() {
        return !driver.findElements(By.xpath("//h1[normalize-space()='Battleship Online']")).isEmpty()
                && driver.findElement(By.xpath("//h1[normalize-space()='Battleship Online']")).isDisplayed();
    }
}
