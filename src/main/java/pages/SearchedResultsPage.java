package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchedResultsPage extends BasePage {

    private final SelenideElement englishLanguageCheckbox = $("[id*='p_n_feature_nine_browse-bin/'] input");
    private final SelenideElement kindleUnlimitedEligibleCheckbox = $("li[id*='p_n_feature_twenty_browse-bin/'] input");
    private final SelenideElement paperbackCheckbox = $("li[id='p_n_feature_browse-bin/2656022011']");
    private final List<SelenideElement> bookTitles = $$("div[data-component-type='s-search-result'] h2");
    private final List<SelenideElement> formatOfBooks = $$("div.puisg-row [data-cy='price-recipe'] a.a-size-base");
    private final List<SelenideElement> clearFilterLinks = $$("a.s-navigation-clear-link");

    public long numberOfBooksByTitle(String searchText) {
        return bookTitles.stream()
                .filter(title -> title.getText().toLowerCase().contains(searchText.toLowerCase()))
                .count();
    }

    public List<String> getBookTitles() {
        return bookTitles.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public void selectEnglishLanguageFilter(){
        forceClickElementWhenFound(englishLanguageCheckbox);
    }

    public void selectKindleFilter(){
        forceClickElementWhenFound(kindleUnlimitedEligibleCheckbox);
    }

    public void selectPaperbackFilter() {
        forceClickElementWhenFound(paperbackCheckbox);
    }

    public void clickBookTitleByIndex(int index) {
        if (index < 1 || index > bookTitles.size()) {
            throw new IllegalArgumentException("Index " + index + " is out of bounds. Total books: " + bookTitles.size());
        }
        SelenideElement bookToClick = bookTitles.get(index - 1);
        scrollToElement(bookToClick);
        forceClickElementWhenFound(bookToClick);
    }

    public List<String> getMainFormatOfBooks() {
        return formatOfBooks.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isAllBooksOfFormat(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }
        return getMainFormatOfBooks().stream()
                .allMatch(currentFormat -> currentFormat.contains(format));
    }

    public void clearAllFilters() {
        clearFilterLinks.forEach(filterLink -> {
            try {
                filterLink.shouldBe(visible).click();
            } catch (Exception e) {
                System.out.println("Failed to click the filter link: " + e.getMessage());
            }
        });
    }

    public List<String[]> getPairsOfBookFormatsForEachOne() {
        List<String[]> textPairs = new ArrayList<>();
        List<SelenideElement> generalFormatOfBooks = $$("div.puisg-row a.a-text-bold");
        waitForVisibility(generalFormatOfBooks.get(0));

        for (int index = 0; index < generalFormatOfBooks.size(); index += 2) {
            int nextBook = index + 1;
            SelenideElement firstBook = generalFormatOfBooks.get(index);
            SelenideElement secondBook = nextBook < generalFormatOfBooks.size() ? generalFormatOfBooks.get(nextBook) : null;
            textPairs.add(new String[]{
                    firstBook.getText(),
                    secondBook != null ? secondBook.getText() : null
            });
        }
        return textPairs;
    }

    public boolean isAllBooksOfFormatForTheTwoAvailable(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }
        List<String[]> bookFormats = getPairsOfBookFormatsForEachOne();
        return bookFormats.stream()
                .allMatch(currentFormat ->
                        (currentFormat[0] != null && currentFormat[0].contains(format)) ||
                                (currentFormat[1] != null && currentFormat[1].contains(format))
                );

    }

    public String getBookFormatsAsString(List<String[]> bookFormats) {
        StringBuilder result = new StringBuilder();

        bookFormats.forEach(bookFormat -> result.append(Arrays.toString(bookFormat)).append("\n"));

        return result.toString();
    }
}
