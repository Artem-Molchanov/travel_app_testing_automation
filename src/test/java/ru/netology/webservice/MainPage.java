package ru.netology.webservice;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement paymentButton = $x("//span[text() = 'Купить']");
    private final SelenideElement creditButton = $x("//span[text() = 'Купить в кредит']");
    private final SelenideElement cardNumberInput = $("[maxlength='19']");
    private final SelenideElement cardMonthInput = $x("//span[text() = 'Месяц']//..//input");
    private final SelenideElement cardYearInput = $x("//span[text() = 'Год']//..//input");
    private final SelenideElement cardOwnerInput = $x("//span[text()='Владелец']//..//input");
    private final SelenideElement cardCvcInput = $("[maxlength='3']");
    private final SelenideElement continueButton = $x("//span[@class='spin spin_size_m spin_theme_alfa-on-white']//../..");
    private final SelenideElement successMsg = $(byText("Операция одобрена Банком."));
    private final SelenideElement refuseMsg = $(byText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormatMsg = $(byText("Неверный формат"));
    private final SelenideElement requiredFieldMsg = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement wrongCardDateMsg = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpireMsg = $(byText("Истёк срок действия карты"));


    public void fillFields(DataHelper.CardInfo cardInfo) {
        cardNumberInput.setValue(cardInfo.getCardNumber());
        cardMonthInput.setValue(cardInfo.getMonth());
        cardYearInput.setValue(cardInfo.getYear());
        cardOwnerInput.setValue(cardInfo.getOwner());
        cardCvcInput.setValue(cardInfo.getCvc());
    }

    public void clickPaymentButton() {
        paymentButton.click();
    }

    public void clickCreditButton() {
        creditButton.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void findSuccessMsg() {
        successMsg.shouldHave(Condition.visible, Duration.ofSeconds(15));
    }

    public void findRefuseMsg() {
        refuseMsg.shouldHave(Condition.visible, Duration.ofSeconds(15));
    }

    public void findWrongFormatMsg() {
        wrongFormatMsg.shouldBe(Condition.visible);
    }

    public void findRequiredFieldMsg() {
        requiredFieldMsg.shouldBe(Condition.visible);
    }

    public void findWrongCardDateMsg() {
        wrongCardDateMsg.shouldBe(Condition.visible);
    }

    public void findCardExpireMsg() {
        cardExpireMsg.shouldBe(Condition.visible);
    }
}
