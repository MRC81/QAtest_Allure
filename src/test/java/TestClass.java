import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

@Report
public class TestClass extends SetUp{

    @Test
    public void weeks_test() {
        open("/equities/gamestop-corp");

        double lastPrice = Double.parseDouble($("span[class^=instrument-price_last]")
                .shouldBe(visible)
                .getText());

        String weekRange = $("[data-test='weekRange']")
                .shouldBe(visible)
                .getText();

        String[] values = weekRange.split("-");

        double startWeeksValue = Double.parseDouble(values[0]);
        double endWeeksValue = Double.parseDouble(values[1]);

        assertTrue(startWeeksValue < lastPrice && lastPrice < endWeeksValue,
                "The lastPrice value: " + lastPrice + " is not within the expected range: " + weekRange);
    }

}
