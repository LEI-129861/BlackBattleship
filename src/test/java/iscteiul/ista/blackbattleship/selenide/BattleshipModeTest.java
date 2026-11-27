package iscteiul.ista.blackbattleship.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

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
        page(BattleshipModePage.class).shouldShowAllGameModes();
    }

    @Test
    @Story("US-36")
    @Description("Valida a interface em uma dimensao de ecrã de telemovel.")
    void userStoryTest36() {
        Configuration.browserSize = "390x844";
        Selenide.open(URL);
        page(BattleshipModePage.class).shouldShowMainInterface();
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
