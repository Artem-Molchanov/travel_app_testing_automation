package ru.netology.data;

import com.github.javafaker.BackToTheFuture;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    static Faker faker = new Faker();

    private DataHelper() {
    }

    public static String genRandomDigits(String setDigitQuantity) {
        return faker.numerify(setDigitQuantity);
    }

    public static String genValidCardOwner() {
        return faker.name().name();

    }

    public static String genInvalidOwnerNameQuote() {
        BackToTheFuture invalidOwnerName = faker.backToTheFuture();
        return invalidOwnerName.quote();
    }

    public static String generateValidCvc() {
        return faker.numerify("###");
    }


    public static String genMonth(int shift) {
        return LocalDate.now().minusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String genYear(int shift) {
        return LocalDate.now().minusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }

}