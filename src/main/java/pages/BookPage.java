package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class BookPage extends BasePage {

    private final SelenideElement addToCartButton = $("#add-to-cart-button");
    private final SelenideElement bookAddedTitle = $("#productTitle");

    public String getBookAddedTitle() {
        return waitForVisibility(bookAddedTitle).getText();
    }

    public void addBookToCart() {
        clickElement(addToCartButton);
    }
}
