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


    public static String generateMonth(int shift) {
        String currentMonth = LocalDate.now().minusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
        return currentMonth;
    }

    public static String generateYear(int shift) {
        String currentYear = LocalDate.now().minusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
        return currentYear;
    }


    @Value

    public static class HappyPathOneInfo {
        String cardNumber = "4444 4444 4444 4441";
        String month = generateMonth(0);
        String year = generateYear(0);
        String owner = "Ivanov Ivan";
        String cvc = "999";

    }

    @Value

    public static class HappyPathTwoInfo {
        String cardNumber = "4444 4444 4444 4442";
        String month = generateMonth(0);
        String year = generateYear(0);
        String owner = "Petrov Aleksandr ";
        String cvc = "001";

    }

}