package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    private final SelenideElement bookInCart = $("a.sc-product-title");
    private final SelenideElement proceedToCheckoutButton = $("#sc-buy-box-ptc-button");
    private final List<SelenideElement> incrementBooksAmountButton = $$("button[data-a-selector='increment']");
    private final List<SelenideElement> booksAmountLabel = $$("div[role='spinbutton']");
    private final List<SelenideElement> decrementBooksAmountButton = $$("button[data-a-selector='decrement']");

    public String getBookInCartTittle(){
        return waitForVisibility(bookInCart).getText();
    }

    public void proceedToCheckoutWithAddedBooks(){
        clickElement(proceedToCheckoutButton);
    }

    public void increaseBooksAmountForBookNumber(int booksAmount, int bookIndex){
        for (int increaseIndex = 0; increaseIndex < booksAmount; increaseIndex++) {
            clickElement(incrementBooksAmountButton.get(bookIndex));
        }
    }

    public int getBooksAmountForBookNumber(int bookIndex){
        String amountText = waitForVisibility(booksAmountLabel.get(bookIndex)).getText();
        return Integer.parseInt(amountText);
    }

    public void decrementBooksAmountForBookNumber(int booksAmount, int bookIndex) {
        for (int decreaseIndex = 0; decreaseIndex < booksAmount; decreaseIndex++) {
            clickElement(decrementBooksAmountButton.get(bookIndex));
        }
    }
}
