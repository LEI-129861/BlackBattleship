package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStoryTest38Test extends SeleniumTestBase {
    @Test
    @DisplayName("US-38: Battleship rules include weapons and strategy guidance")
    void rulesPageShowsRulesWeaponsAndStrategy() {
        RulesPage rulesPage = new RulesPage(driver).open();

        assertTrue(rulesPage.hasRulesWeaponsAndStrategy(),
                "The page should show the Rules, Weapons, and Strategy sections");
        assertTrue(rulesPage.hasCenterAndCheckerboardAdvice(),
                "Strategy guidance should mention the center and checkerboard pattern");
    }
}
