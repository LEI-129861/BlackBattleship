package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StorePage extends BasePage {
    private static final By[] CATEGORY_LINKS = {
            By.cssSelector("a[href='/en/shop/virtual-coins']"),
            By.cssSelector("a[href='/en/shop/avatars']"),
            By.cssSelector("a[href='/en/shop/emojis']"),
            By.cssSelector("a[href='/en/shop/boosters']")
    };

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public boolean hasAllCategories() {
        return wait.until(d -> {
            for (By categoryLink : CATEGORY_LINKS) {
                List<WebElement> links = d.findElements(categoryLink);
                if (links.isEmpty() || !links.get(0).isDisplayed()) {
                    return false;
                }
            }
            return true;
        });
    }
}
