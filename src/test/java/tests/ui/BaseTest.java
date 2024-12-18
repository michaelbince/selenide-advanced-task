package tests.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.open;
import static utils.DriverManager.configureDriver;

public abstract class BaseTest {
    protected final static String baseURL = "https://www.bookdepository.com/";

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        configureDriver(browser);
        open(baseURL);
    }

}
