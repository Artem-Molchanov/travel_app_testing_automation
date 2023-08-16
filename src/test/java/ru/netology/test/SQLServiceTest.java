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
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusInfoPayment());

    }

    @Test
    void shouldTestPaymentHappyPathTwoDatabasePayStatus() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusInfoPayment());

    }


    @Test
    void shouldTestCreditRequestHappyPathOnePayStatus() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatusInfoCredit());
    }


    @Test
    void shouldTestCreditRequestHappyPathTwoPayStatus() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatusInfoCredit());
    }


    @Test
    void shouldTestPaymentHappyPathOneOrderEntityIdComparison() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getPaymentTransId(), SQLHelper.getOrderPaymentId());

    }

    @Test
    void shouldTestPaymentHappyPathTwoOrderEntityIdComparison() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getPaymentTransId(), SQLHelper.getOrderPaymentId());

    }

    //не проходит
    @Test
    void shouldTestCreditRequestHappyPathOneOrderEntityIdComparison() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getCreditReqBankId(), SQLHelper.getOrderCreditId());

    }

    //не проходит
    @Test
    void shouldTestCreditRequestHappyPathTwoOrderEntityIdComparison() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals(SQLHelper.getCreditReqBankId(), SQLHelper.getOrderCreditId());

    }

    //не проходит
    @Test
    void shouldTestPaymentHappyPathOneAmountCheck() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("45000", SQLHelper.getPayedAmount());

    }

    //не проходит
    @Test
    void shouldTestPaymentHappyPathTwoAmountCheck() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        Assertions.assertEquals("45000", SQLHelper.getPayedAmount());

    }

}
