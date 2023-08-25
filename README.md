### _Автоматизированное тестирование веб-сервиса по покупке тура "Путешествие дня"_


## 1. Общие сведения

Функционал веб-сервиса предлагает купить тур двумя способами:

* обычная оплата по дебетовой карте;
* через кредит, по данным банковской карты.

Сервис имеет поддержку MySQL и PostgreSQL.

## 2. Необходимое ПО

ИСР (для Java), Docker (либо: СУБД MySQL, PostgreSQL и Node.js).

## 3. Условия для проведения автотестов

### Докеризация 

Запуск процесса:

> docker compose up

### Тестирование БД на MySQL (задано по умолчанию)  

Запуск приложения:

> java -jar artifacts/aqa-shop.jar

Запуск автотестов:

>./gradlew clean test 

### Тестирование БД PostgreSQL

Запуск приложения:

> java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar

Запуск автотестов:

>./gradlew test "-Durl=jdbc:postgresql://localhost:5432/app"


