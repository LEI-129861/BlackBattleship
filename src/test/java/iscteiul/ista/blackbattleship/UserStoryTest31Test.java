package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStoryTest31Test extends SeleniumTestBase {
    @Test
    @DisplayName("US-31: shop displays Coins, Monsters, Emojis, and Boosters")
    void shopDisplaysAllCategories() {
        StorePage storePage = new HomePage(driver).open().openStore();

        assertTrue(storePage.getCurrentUrl().contains("/en/shop"),
                "The Shop navigation link should open the store page: " + storePage.getCurrentUrl());
        assertTrue(storePage.hasAllCategories(),
                "The shop should display all four product categories");
    }
}
