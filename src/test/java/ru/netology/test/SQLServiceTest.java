package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.webservice.MainPage;

import static com.codeborne.selenide.Selenide.open;


public class SQLServiceTest {

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
        SQLHelper.cleanDatabase();
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");

    }

    @Test
    void shouldTestPaymentHappyPathOneDatabasePayStatus() {
        mainPage.clickPaymentButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusInfoPayment());

    }

    @Test
    void shouldTestPaymentHappyPathTwoDatabasePayStatus() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusInfoPayment());

    }


    @Test
    void shouldTestCreditRequestHappyPathOnePayStatus() {
        mainPage.clickCreditButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusInfoCredit());
    }


    @Test
    void shouldTestCreditRequestHappyPathTwoPayStatus() {
        mainPage.clickCreditButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusInfoCredit());
    }


    @Test
    void shouldTestPaymentHappyPathOneOrderEntityIdComparison() {
        mainPage.clickPaymentButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getPaymentTransId(), SQLHelper.getOrderPaymentId());

    }

    @Test
    void shouldTestPaymentHappyPathTwoOrderEntityIdComparison() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getPaymentTransId(), SQLHelper.getOrderPaymentId());

    }

    //не проходит
    @Test
    void shouldTestCreditRequestHappyPathOneOrderEntityIdComparison() {
        mainPage.clickCreditButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getCreditReqBankId(), SQLHelper.getOrderCreditId());

    }

    //не проходит
    @Test
    void shouldTestCreditRequestHappyPathTwoOrderEntityIdComparison() {
        mainPage.clickCreditButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getCreditReqBankId(), SQLHelper.getOrderCreditId());

    }

    @Test
    void shouldTestPaymentHappyPathOneAmountCheck() {
        mainPage.clickPaymentButton();
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(4_500_000, SQLHelper.getPayedAmount());

    }

    @Test
    void shouldTestPaymentHappyPathTwoAmountCheck() {
        mainPage.clickPaymentButton();
        cardInfo.setCardNumber("4444444444444442");
        mainPage.fillFields(cardInfo);
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(4_500_000, SQLHelper.getPayedAmount());

    }

}
