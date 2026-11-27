package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de caixa preta para a US-07.
 */
class UserStory07Test {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1280,900");
        driver = new ChromeDriver(options);
        driver.get("https://papergames.io/en/battleship");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void userStoryTest07() {
        UserStory07 page = new UserStory07(driver);
        assertTrue(page.hasAllGameModes());
        assertTrue(page.areAllGameModesVisibleWithoutScrolling());

        driver.manage().window().setSize(new Dimension(390, 844));
        assertTrue(page.areAllGameModesVisibleWithoutScrolling());
    }
}
