package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchComponentPage extends BasePage {

    private final SelenideElement searchDropdown = $("#searchDropdownBox");
    private final SelenideElement searchInput = $("#twotabsearchtextbox");
    private final SelenideElement searchButton = $("#nav-search-submit-button");
    private final SelenideElement cartButton = $("#nav-cart");

    public void selectCategory(String category) {
        searchDropdown.shouldBe(visible, enabled).selectOptionContainingText(category);
    }

    public void search(String text) {
        searchInput.shouldBe(visible, enabled).clear();
        searchInput.setValue(text);
        clickSearchButton();
    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public void clickCartButton() {
        clickElement(cartButton);
    }
}
