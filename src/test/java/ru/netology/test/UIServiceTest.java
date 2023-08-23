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
import static com.codeborne.selenide.Selenide.sleep;


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

    @Test
    void shouldTestPaymentHappyPathTwo() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("4444444444444442");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestCreditHappyPathOne() {
        mainPage.clickCreditButton();
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestCreditHappyPathTwo() {
        mainPage.clickCreditButton();
        cardInfo.setCardNumber("4444444444444442");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
    }

    @Test
    void shouldTestCardNumberEmptyFieldMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestMonthEmptyFieldMsg() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
        cardInfo.setMonth("");
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestYearEmptyFieldMsg() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
        cardInfo.setYear("");
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestCardOwnerFieldMsg() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
        cardInfo.setOwner("");
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findRequiredFieldMsg();
    }

    @Test
    void shouldTestCvcEmptyFieldMsg() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
        cardInfo.setCvc("");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestInvalidCardNumberMsg() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber(DataHelper.genRandomDigits("##########"));
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();


//
//        mainPage.getInvalidCardNumber();
//        mainPage.fillAllFieldsExceptCardNumber(DataHelper.getHappyPathOneInfo());
//        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }

    @Test
    void shouldTestInvalidMonthMsg() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
        cardInfo.setMonth(DataHelper.genRandomDigits("#"));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();
    }


    @Test
    void shouldTestInvalidYearMsg() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
        cardInfo.setYear(DataHelper.genRandomDigits("#"));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    //не проходит
    @Test
    void shouldTestInvalidOwnerNameQuote() {
        mainPage.clickPaymentButton();
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
        cardInfo.setOwner(DataHelper.genInvalidOwnerNameQuote());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }

    //не проходит
    @Test
    void shouldTestInvalidOwnerNameInDigit() {
        mainPage.clickPaymentButton();
//
//
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
        cardInfo.setOwner(DataHelper.genRandomDigits("##################"));
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
//
//        mainPage.getInvalidOwnerNameDigit();
//        mainPage.fillAllFieldsExceptOwner(DataHelper.getHappyPathOneInfo());
//        mainPage.clickContinueButton();
        mainPage.findWrongFormatMsg();

    }


    @Test
    void shouldTestInvalidCvcMsg() {
        mainPage.clickPaymentButton();
//
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
        cardInfo.setCvc(DataHelper.genRandomDigits("#"));
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
//
//        mainPage.getInvalidCvc();
//        mainPage.fillAllFieldsExceptCvc(DataHelper.getHappyPathOneInfo());
        mainPage.findWrongFormatMsg();

    }

    @Test
    void shouldTestMonthCardExpireMsg() {
        mainPage.clickPaymentButton();

        cardInfo.setCardNumber("4444444444444441");
        cardInfo.setMonth(DataHelper.genMonth(1));
//        cardInfo.setYear(DataHelper.genYear(0));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findWrongCardDateMsg();
        sleep(7000);



//        mainPage.getPrevMonth();
//        mainPage.fillAllFieldsExceptMonth(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findWrongCardDateMsg();

    }


    @Test
    void shouldTestYearCardExpireMsg() {
        mainPage.clickPaymentButton();
//
//        cardInfo.setCardNumber("4444444444444441");
//        cardInfo.setMonth(DataHelper.genMonth(0));
        cardInfo.setYear(DataHelper.genYear(1));
//        cardInfo.setOwner(DataHelper.genValidCardOwner());
//        cardInfo.setCvc(DataHelper.generateValidCvc());
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();

//        mainPage.getPrevYear();
//        mainPage.fillAllFieldsExceptYear(DataHelper.getHappyPathOneInfo());
//        mainPage.clickContinueButton();
        mainPage.findCardExpireMsg();
        sleep( 7000);

    }
}
