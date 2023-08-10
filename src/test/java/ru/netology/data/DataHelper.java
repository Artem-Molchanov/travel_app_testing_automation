package ru.netology.data;

import com.github.javafaker.BackToTheFuture;
import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    static Faker faker = new Faker();

    private DataHelper() {
    }

    public static HappyPathOneInfo getHappyPathOneInfo() {
        return new HappyPathOneInfo();
    }

    public static HappyPathTwoInfo getHappyPathTwoInfo() {
        return new HappyPathTwoInfo();
    }

    public static StatusInfo getStatusInfo() {
        return new StatusInfo("APPROVED");
    }

    public static CreditIdInfo getIdInfo() {
        return new CreditIdInfo(null);
    }

    public static long generateInvalidCardNumber() {
        long invalidCardNumber = faker.number().randomNumber(15, false);
        return invalidCardNumber;
    }

    public static int generateInvalidMonthOrYear() {
        int invalidMonthOrYear = faker.number().randomDigit();
        return invalidMonthOrYear;
    }

    public static String generateInvalidOwnerNameQuote() {
        BackToTheFuture invalidOwnerName = faker.backToTheFuture();
        return invalidOwnerName.quote();
    }

    public static int generateInvalidCvc() {
        int invalidCvc = (int) faker.number().randomNumber(2, false);
        return invalidCvc;
    }

    public static String generateCurrentMonth() {
        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return currentMonth;
    }

    public static String generatePrevMonth(int shift) {
        String prevMonth = LocalDate.now().minusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
        return prevMonth;
    }

    public static String generateCurrentYear() {
        String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return currentYear;
    }

    public static String generatePrevYear(int shift) {
        String prevYear = LocalDate.now().minusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
        return prevYear;
    }

    @Value

    public static class HappyPathOneInfo {
        String cardNumber = "4444 4444 4444 4441";
        String month = generateCurrentMonth();
        String year = generateCurrentYear();
        String owner = "Ivanov Ivan";
        String cvc = "999";

    }

    @Value

    public static class HappyPathTwoInfo {
        String cardNumber = "4444 4444 4444 4442";
        String month = generateCurrentMonth();
        String year = generateCurrentYear();
        String owner = "Petrov Aleksandr ";
        String cvc = "001";

    }

    @Value
    public static class StatusInfo {
        String status;
    }

    @Value
    public static class CreditIdInfo {
        String creditIdInfo;
    }

}
