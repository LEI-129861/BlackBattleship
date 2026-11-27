package iscteiul.ista.blackbattleship.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Suite Selenide das historias atribuidas: US-07, US-36, US-37 e US-41.
 */
@Feature("Batalha Naval - elementos de interacao")
class BattleshipModeTest {
    private static final String URL = "https://papergames.io/en/battleship";

    @BeforeAll
    static void configureAllure() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserSize = "1280x900";
        Configuration.timeout = 10_000;
        open(URL);
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @Story("US-07")
    @Description("Valida a apresentacao dos modos de jogo no ecra inicial.")
    void userStoryTest07() {
        BattleshipModePage page = page(BattleshipModePage.class).shouldShowAllGameModes();
        assertTrue(page.areAllGameModesVisibleWithoutScrolling());
    }

    @Test
    @Story("US-36")
    @Description("Valida a interface em uma dimensao de ecrã de telemovel.")
    void userStoryTest36() {
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(390, 844));
        Selenide.open(URL);
        BattleshipModePage page = page(BattleshipModePage.class).shouldShowMainInterface();
        assertTrue(page.hasNoHorizontalOverflow());
        assertTrue(page.areAllGameModesVisibleWithoutScrolling());
    }

    @Test
    @Story("US-37")
    @Description("Valida as ligacoes Android e iOS presentes na pagina.")
    void userStoryTest37() {
        page(BattleshipModePage.class).shouldShowMobileAppLinks();
    }

    @Test
    @Story("US-41")
    @Description("Valida as ligacoes de privacidade e termos no rodape.")
    void userStoryTest41() {
        page(BattleshipModePage.class).shouldShowPrivacyAndTerms();
    }
}
