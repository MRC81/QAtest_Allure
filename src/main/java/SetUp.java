import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.TextReport;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;

import static io.qameta.allure.Allure.step;


@Listeners({TextReport.class})
public class SetUp {

    @BeforeClass
    public void launchBrowserAndOpenSite() {
        String browser = System.getProperty("selenide.browser", "chrome");

        Configuration.browser = browser;

        String host = "https://www.investing.com";

        Configuration.holdBrowserOpen = false;
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        Configuration.baseUrl = host;
        Configuration.headless = false;
        //Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "reports";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        step("Launch the " + browser.substring(0, 1).toUpperCase() + browser.substring(1)
                + " browser and open the " + host + " site", () ->
                Selenide.open(host)
        );
    }

    @AfterClass
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        WebDriverRunner.closeWebDriver();
    }


}