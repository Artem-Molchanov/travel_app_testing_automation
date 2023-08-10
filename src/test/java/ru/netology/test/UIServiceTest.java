package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.webservice.MainPage;

import static com.codeborne.selenide.Selenide.open;


public class UIServiceTest {

    MainPage mainPage = new MainPage();


    @BeforeEach
    void setup() {
        open("http://localhost:8080/");

    }

    @Test
    void shouldTestBuyHappyPathOne() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestBuyHappyPathTwo() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestBuyCreditHappyPathOne() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestBuyCreditHappyPathTwo() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestCardNumberEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFieldsExceptCardNumber(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestMonthEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFieldsExceptMonth(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestYearEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFieldsExceptYear(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestCardOwnerFieldMsg() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFieldsExceptOwner(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findRequiredFieldMsg();
    }

    @Test
    void shouldTestCvcEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFieldsExceptCvc(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestInvalidCardNumberMsg() {
        mainPage.clickPaymentButton();
        mainPage.getInvalidCardNumber();
        mainPage.fillAllFieldsExceptCardNumber(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestInvalidMonthMsg() {
        mainPage.clickPaymentButton();
        mainPage.getInvalidMonth();
        mainPage.fillAllFieldsExceptMonth(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }


    @Test
    void shouldTestInvalidYearMsg() {
        mainPage.clickPaymentButton();
        mainPage.getInvalidYear();
        mainPage.fillAllFieldsExceptYear(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    //не проходит
    @Test
    void shouldTestInvalidOwnerNameQuote() {
        mainPage.clickPaymentButton();
        mainPage.getInvalidOwnerNameQuote();
        mainPage.fillAllFieldsExceptOwner(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    //не проходит
    @Test
    void shouldTestInvalidOwnerNameDigit() {
        mainPage.clickPaymentButton();
        mainPage.getInvalidOwnerNameDigit();
        mainPage.fillAllFieldsExceptOwner(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }


    @Test
    void shouldTestInvalidCvcMsg() {
        mainPage.clickPaymentButton();
        mainPage.getInvalidCvc();
        mainPage.fillAllFieldsExceptCvc(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    @Test
    void shouldTestMonthCardExpireMsg() {
        mainPage.clickPaymentButton();
        mainPage.getPrevMonth();
        mainPage.fillAllFieldsExceptMonth(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongCardDateMsg();

    }


    @Test
    void shouldTestYearCardExpireMsg() {
        mainPage.clickPaymentButton();
        mainPage.getPrevYear();
        mainPage.fillAllFieldsExceptYear(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findCardExpireMsg();

    }
}
