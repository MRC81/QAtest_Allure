import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TestClass extends SetUp{

    @Test
    public void weeks_test() {
        double lastPrice = Double.parseDouble($("span[class^=instrument-price_last]")
                .shouldBe(visible)
                .getText());

        String weekRange = $("[data-test='weekRange']")
                .shouldBe(visible)
                .getText();

        String[] values = weekRange.split("-");

        double startWeeksValue = Double.parseDouble(values[0]);
        double endWeeksValue = Double.parseDouble(values[1]);

        Assertions.assertTrue(startWeeksValue < lastPrice && lastPrice < endWeeksValue,
                "The lastPrice value: " + lastPrice + " is not within the expected range: " + weekRange);
    }

}
