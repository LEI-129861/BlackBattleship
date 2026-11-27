package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStoryTest6Test extends SeleniumTestBase {
    @Test
    @DisplayName("US-06: starts a game against the robot with two 10x10 boards")
    void startsRobotGameWithTwoBoards() {
        GamePage gamePage = new HomePage(driver)
                .open()
                .playAgainstRobot("US06-Test");

        assertTrue(gamePage.getCurrentUrl().matches(".*/en/r/[^/]+$"),
                "The browser should navigate to the robot game room");
        assertTrue(gamePage.hasTwoVisibleTenByTenBoards(),
                "Both player boards should contain 100 visible cells");
    }
}
