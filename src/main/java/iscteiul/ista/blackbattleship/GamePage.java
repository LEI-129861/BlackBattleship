package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class GamePage extends BasePage {
    private static final By BOARDS = By.cssSelector("table.table-board");
    private static final By ATTACK_CELLS = By.cssSelector("table.table-board[id^='board_ai'] td");
    private static final By MY_TURN_HINT = By.xpath("//*[normalize-space(.)='Attack your opponent!']");

    public GamePage(WebDriver driver) {
        super(driver);
    }

    public void waitForBoards() {
        wait.until(d -> {
            List<WebElement> boards = d.findElements(BOARDS);
            if (boards.size() != 2) {
                return false;
            }
            for (WebElement board : boards) {
                if (!board.isDisplayed() || board.findElements(By.cssSelector("td")).size() != 100) {
                    return false;
                }
            }
            return true;
        });
    }

    public boolean hasTwoVisibleTenByTenBoards() {
        List<WebElement> boards = driver.findElements(BOARDS);
        if (boards.size() != 2) {
            return false;
        }
        for (WebElement board : boards) {
            if (!board.isDisplayed() || board.findElements(By.cssSelector("td")).size() != 100) {
                return false;
            }
        }
        return true;
    }

    public String fireAtFirstAvailableCell() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MY_TURN_HINT));
        WebElement cell = wait.until(d -> {
            List<WebElement> cells = d.findElements(ATTACK_CELLS);
            for (WebElement candidate : cells) {
                String state = cellState(candidate);
                if (!state.contains("fire") && !state.contains("water")
                        && !state.contains("hit") && !state.contains("miss")) {
                    return candidate;
                }
            }
            return null;
        });

        By cellLocator = locatorForCell(cell);
        String initialState = cellState(cell);
        clickWithConsentHandling(cellLocator);
        return wait.until(d -> {
            List<WebElement> updatedCells = d.findElements(cellLocator);
            if (updatedCells.isEmpty()) {
                return null;
            }
            String updatedState = cellState(updatedCells.get(0));
            return !Objects.equals(initialState, updatedState) ? updatedState : null;
        });
    }

    public boolean cellRemainsUnchangedAfterRepeatClick(String updatedState) {
        List<WebElement> cells = driver.findElements(ATTACK_CELLS);
        for (WebElement cell : cells) {
            if (Objects.equals(updatedState, cellState(cell))) {
                By cellLocator = locatorForCell(cell);
                clickWithConsentHandling(cellLocator);
                try {
                    new WebDriverWait(driver, Duration.ofSeconds(1))
                            .until(d -> !Objects.equals(updatedState, cellState(d.findElement(cellLocator))));
                    return false;
                } catch (TimeoutException expected) {
                    return true;
                }
            }
        }
        return false;
    }

    private static By locatorForCell(WebElement cell) {
        String coordinateClass = cell.getAttribute("class").split(" ")[0];
        return By.cssSelector("table.table-board[id^='board_ai'] td." + coordinateClass);
    }

    private static String cellState(WebElement cell) {
        return cell.getAttribute("class") + "|" + cell.getAttribute("innerHTML");
    }
}
