package iscteiul.ista.blackbattleship.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

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

    @FindBy(xpath = "//main//a[contains(normalize-space(.), 'Create tournament')]")
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

    public boolean areAllGameModesVisibleWithoutScrolling() {
        return isWithinViewport(playWithFriend)
                && isWithinViewport(playVsRobot)
                && isWithinViewport(playOnline)
                && isWithinViewport(createTournament);
    }

    public boolean hasNoHorizontalOverflow() {
        return Boolean.TRUE.equals(executeJavaScript(
                "return document.documentElement.scrollWidth <= window.innerWidth;"));
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

    private boolean isWithinViewport(SelenideElement element) {
        return Boolean.TRUE.equals(executeJavaScript(
                "const rect = arguments[0].getBoundingClientRect();"
                        + "return rect.width > 0 && rect.height > 0"
                        + " && rect.top >= 0 && rect.left >= 0"
                        + " && rect.bottom <= window.innerHeight"
                        + " && rect.right <= window.innerWidth;",
                element));
    }
}
