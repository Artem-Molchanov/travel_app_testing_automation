### _Автоматизированное тестирование веб-сервиса по покупке тура "Путешествие дня"_



## 1. Общие данные

Функционал веб-сервиса предлагает купить тур двумя способами:

* обычная оплата по дебетовой карте;
* через кредит, по данным банковской карты.

Сервис имеет поддержку MySQL и PostgreSQL.

## 2. Необходимое ПО

ИСР (для Java), Docker (либо: СУБД MySQL, PostgreSQL и Node.js).

## 3. Условия запуска автотестов

Запуск автотестов производится через команду: 

>./gradlew clean test 

По умолчанию, тестирование SQL части проходит на MySQL. Для тестирования на PostgreSQL необходимо запустить команду: 

> java -jar artifacts/aqa-shop.jar "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass"