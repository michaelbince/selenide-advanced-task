package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage extends BasePage {

    private final SelenideElement signInTitle = $("[name='signIn'] h1");

    public boolean isSignInPageTitleDisplayed(){
        return waitForVisibility(signInTitle).isDisplayed();
    }
}
