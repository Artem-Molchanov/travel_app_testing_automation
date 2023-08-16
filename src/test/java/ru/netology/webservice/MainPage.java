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
    private final SelenideElement successMsg = $x("//div[text() ='Успешно']");
    private final SelenideElement wrongFormatMsg = $(byText("Неверный формат"));
    private final SelenideElement requiredFieldMsg = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement wrongCardDateMsg = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpireMsg = $(byText("Истёк срок действия карты"));

    public void fillAllFields(DataHelper.HappyPathOneInfo happyPathInfo) {
        cardNumberInput.setValue(happyPathInfo.getCardNumber());
        cardMonthInput.setValue(happyPathInfo.getMonth());
        cardYearInput.setValue(happyPathInfo.getYear());
        cardOwnerInput.setValue(happyPathInfo.getOwner());
        cardCvcInput.setValue(happyPathInfo.getCvc());
    }

    public void fillAllFields(DataHelper.HappyPathTwoInfo happyPathInfo) {
        cardNumberInput.setValue(happyPathInfo.getCardNumber());
        cardMonthInput.setValue(happyPathInfo.getMonth());
        cardYearInput.setValue(happyPathInfo.getYear());
        cardOwnerInput.setValue(happyPathInfo.getOwner());
        cardCvcInput.setValue(happyPathInfo.getCvc());
    }

    public void fillAllFieldsExceptCardNumber(DataHelper.HappyPathOneInfo happyPathInfo) {
        cardMonthInput.setValue(happyPathInfo.getMonth());
        cardYearInput.setValue(happyPathInfo.getYear());
        cardOwnerInput.setValue(happyPathInfo.getOwner());
        cardCvcInput.setValue(happyPathInfo.getCvc());
    }

    public void fillAllFieldsExceptMonth(DataHelper.HappyPathOneInfo happyPathInfo) {
        cardNumberInput.setValue(happyPathInfo.getCardNumber());
        cardYearInput.setValue(happyPathInfo.getYear());
        cardOwnerInput.setValue(happyPathInfo.getOwner());
        cardCvcInput.setValue(happyPathInfo.getCvc());
    }

    public void fillAllFieldsExceptYear(DataHelper.HappyPathOneInfo happyPathInfo) {
        cardNumberInput.setValue(happyPathInfo.getCardNumber());
        cardMonthInput.setValue(happyPathInfo.getMonth());
        cardOwnerInput.setValue(happyPathInfo.getOwner());
        cardCvcInput.setValue(happyPathInfo.getCvc());
    }

    public void fillAllFieldsExceptOwner(DataHelper.HappyPathOneInfo happyPathInfo) {
        cardNumberInput.setValue(happyPathInfo.getCardNumber());
        cardMonthInput.setValue(happyPathInfo.getMonth());
        cardYearInput.setValue(happyPathInfo.getYear());
        cardCvcInput.setValue(happyPathInfo.getCvc());
    }

    public void fillAllFieldsExceptCvc(DataHelper.HappyPathOneInfo happyPathInfo) {
        cardNumberInput.setValue(happyPathInfo.getCardNumber());
        cardMonthInput.setValue(happyPathInfo.getMonth());
        cardYearInput.setValue(happyPathInfo.getYear());
        cardOwnerInput.setValue(happyPathInfo.getOwner());
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
        successMsg.shouldHave(Condition.text("Успешно"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
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

    public void getInvalidCardNumber() {
        long invalidCardNumber = DataHelper.generateInvalidCardNumber();
        cardNumberInput.setValue(String.valueOf(invalidCardNumber));
    }

    public void getInvalidMonth() {
        long invalidMonthOrYear = DataHelper.generateInvalidMonthOrYear();
        cardMonthInput.setValue(String.valueOf(invalidMonthOrYear));
    }

    public void getInvalidYear() {
        long invalidMonthOrYear = DataHelper.generateInvalidMonthOrYear();
        cardYearInput.setValue(String.valueOf(invalidMonthOrYear));
    }

    public void getInvalidOwnerNameQuote() {
        String invalidOwnerNameQuote = DataHelper.generateInvalidOwnerNameQuote();
        cardOwnerInput.setValue(String.valueOf(invalidOwnerNameQuote));
    }

    public void getInvalidOwnerNameDigit() {
        long invalidCardNumber = DataHelper.generateInvalidCardNumber();
        cardOwnerInput.setValue(String.valueOf(invalidCardNumber));
    }

    public void getInvalidCvc() {
        int invalidCvc = DataHelper.generateInvalidCvc();
        cardCvcInput.setValue(String.valueOf(invalidCvc));
    }

    public void getPrevMonth() {
        String prevMonth = DataHelper.generateMonth(1);
        cardMonthInput.setValue(prevMonth);
    }

    public void getPrevYear() {
        String prevYear = DataHelper.generateYear(1);
        cardYearInput.setValue(prevYear);
    }

}
