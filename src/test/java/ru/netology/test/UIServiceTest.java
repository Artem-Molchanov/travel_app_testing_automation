package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.webservice.MainPage;

import static com.codeborne.selenide.Selenide.open;


public class UIServiceTest {

    MainPage mainPage = new MainPage();
    DataHelper.CardInfo cardInfo = new DataHelper.CardInfo(
            "4444444444444441",
            DataHelper.genMonth(0),
            DataHelper.genYear(0),
            DataHelper.genValidCardOwner(),
            DataHelper.generateValidCvc()
    );

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");

    }


    @Test
    void shouldTestPaymentHappyPathOne() {
        mainPage.clickPaymentButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    //не проходит
    @Test
    void shouldTestPaymentHappyPathTwo() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findRefuseMsg();
    }

    @Test
    void shouldTestCreditHappyPathOne() {
        mainPage.clickCreditButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    //не проходит
    @Test
    void shouldTestCreditHappyPathTwo() {
        mainPage.clickCreditButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findRefuseMsg();
    }

    @Test
    void shouldTestCardNumberEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestMonthEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setMonth("");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestYearEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setYear("");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestCardOwnerFieldMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setOwner("");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findRequiredFieldMsg();
    }

    @Test
    void shouldTestCvcEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCvc("");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestInvalidCardNumberMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber(DataHelper.genRandomDigits("##########"));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestInvalidMonthMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setMonth(DataHelper.genRandomDigits("#"));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }


    @Test
    void shouldTestInvalidYearMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setYear(DataHelper.genRandomDigits("#"));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    //не проходит
    @Test
    void shouldTestInvalidOwnerNameQuote() {
        mainPage.clickPaymentButton();
        cardInfo.setOwner(DataHelper.genInvalidOwnerNameQuote());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    //не проходит
    @Test
    void shouldTestInvalidOwnerNameInDigit() {
        mainPage.clickPaymentButton();
        cardInfo.setOwner(DataHelper.genRandomDigits("##################"));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }


    @Test
    void shouldTestInvalidCvcMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCvc(DataHelper.genRandomDigits("#"));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    @Test
    void shouldTestMonthCardExpireMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("4444444444444441");
        cardInfo.setMonth(DataHelper.genMonth(1));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongCardDateMsg();
        mainPage.clickContinueButton();
        mainPage.findWrongCardDateMsg();

    }

    @Test
    void shouldTestYearCardExpireMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setYear(DataHelper.genYear(1));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findCardExpireMsg();
    }
}
