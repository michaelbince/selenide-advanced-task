package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public abstract class BasePage {
    public SelenideElement waitForVisibility(SelenideElement element) {
        return element.shouldBe(Condition.visible);
    }

    public SelenideElement waitForClickAbility(SelenideElement element) {
        return element.shouldBe(Condition.enabled);
    }

    public void scrollToElement(SelenideElement element) {
        element.scrollIntoView(true);
    }

    public void clickElement(SelenideElement element) {
        waitForVisibility(element);
        waitForClickAbility(element).click();
    }

    public void forceClickElementWhenFound(SelenideElement element) {
        try {
            element.scrollTo();
            Selenide.executeJavaScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            System.out.println("Element state changed, but it worked");
        }
    }


    public void navigateBack() {
        WebDriverRunner.getWebDriver().navigate().back();
    }
}

