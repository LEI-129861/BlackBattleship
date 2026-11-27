package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStoryTest10Test extends SeleniumTestBase {
    @Test
    @DisplayName("US-10: firing marks a cell and prevents firing at it twice")
    void firingMarksCellAndPreventsRepeatedShot() {
        GamePage gamePage = new HomePage(driver)
                .open()
                .playAgainstRobot("US10-Test");

        String markedCellState = gamePage.fireAtFirstAvailableCell();

        assertTrue(markedCellState.contains("cell-"),
                "The fired cell should expose its updated grid state");
        assertTrue(gamePage.cellRemainsUnchangedAfterRepeatClick(markedCellState),
                "A cell that has already been fired at should not change again");
    }
}
