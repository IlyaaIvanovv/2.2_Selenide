import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class DeliveryCard {

    GenerateDate date = new GenerateDate();

    @Test
    void shouldCompletedFormDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Вологда");
        String currentDate = date.generateDate(3,"dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='name'] input").setValue("Анна Иванова");
        $("[data-test-id='phone'] input").setValue("+79001001010");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " + currentDate));
    }
}
