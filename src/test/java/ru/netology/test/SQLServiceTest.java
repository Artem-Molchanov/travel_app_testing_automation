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

//    @AfterAll
//    static void teardown() {
//        SQLHelper.cleanDatabase();
//    }

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
        var expected = DataHelper.getStatusInfo();
        var actual = SQLHelper.getStatusInfoPayment();
        Assertions.assertEquals(expected, actual);

    }

    // не проходит:
    @Test
    void shouldTestPaymentHappyPathTwoDatabasePayStatus() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getStatusInfo();
        var actual = SQLHelper.getStatusInfoPayment();
        Assertions.assertEquals(expected, actual);

    }


    @Test
    void shouldTestCreditRequestHappyPathOnePayStatus() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getStatusInfo();
        var actual = SQLHelper.getStatusInfoCredit();
        Assertions.assertEquals(expected, actual);
    }

    // не проходит:
    @Test
    void shouldTestCreditRequestHappyPathTwoPayStatus() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getStatusInfo();
        var actual = SQLHelper.getStatusInfoCredit();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void shouldTestPaymentHappyPathOneCreditIdNull() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getIdInfo();
        var actual = SQLHelper.getIdInfo();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldTestPaymentHappyPathTwoCreditIdNull() {
        mainPage.clickPaymentButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getIdInfo();
        var actual = SQLHelper.getIdInfo();
        Assertions.assertEquals(expected, actual);

    }


    @Test
    void shouldTestCreditRequestHappyPathOneCreditIdNull() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathOneInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getIdInfo();
        var actual = SQLHelper.getIdInfo();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldTestCreditRequestHappyPathTwoCreditIdNull() {
        mainPage.clickCreditButton();
        mainPage.fillAllFields(DataHelper.getHappyPathTwoInfo());
        mainPage.clickContinueButton();
        mainPage.findSuccessMsg();
        var expected = DataHelper.getIdInfo();
        var actual = SQLHelper.getIdInfo();
        Assertions.assertEquals(expected, actual);

    }

}
