package iscteiul.ista.blackbattleship.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;

/**
 * Page Object Selenide para as US-07, US-36, US-37 e US-41.
 */
public class BattleshipModePage {
    @FindBy(xpath = "//h1[normalize-space()='Battleship Online']")
    private SelenideElement heading;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Play with a friend')]")
    private SelenideElement playWithFriend;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Play vs robot')]")
    private SelenideElement playVsRobot;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Play online')]")
    private SelenideElement playOnline;

    @FindBy(xpath = "//a[contains(normalize-space(.), 'Create tournament')]")
    private SelenideElement createTournament;

    @FindBy(css = "a[href*='play.google.com/store/apps/details?id=io.papergames.app']")
    private SelenideElement googlePlayLink;

    @FindBy(css = "a[href*='apps.apple.com/app/papergames-io']")
    private SelenideElement appStoreLink;

    @FindBy(css = "a[href='/en/blog/privacy-policy']")
    private SelenideElement privacyLink;

    @FindBy(css = "a[href='/en/blog/terms-conditions']")
    private SelenideElement termsLink;

    public BattleshipModePage shouldShowMainInterface() {
        heading.shouldBe(visible);
        return this;
    }

    public BattleshipModePage shouldShowAllGameModes() {
        playWithFriend.shouldBe(visible);
        playVsRobot.shouldBe(visible);
        playOnline.shouldBe(visible);
        createTournament.shouldBe(visible);
        return this;
    }

    public BattleshipModePage shouldShowMobileAppLinks() {
        googlePlayLink.shouldBe(visible);
        appStoreLink.shouldBe(visible);
        return this;
    }

    public BattleshipModePage shouldShowPrivacyAndTerms() {
        privacyLink.shouldBe(visible);
        termsLink.shouldBe(visible);
        return this;
    }
}
