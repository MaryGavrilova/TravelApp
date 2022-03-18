# TravelApp

## General Information
Программа позволяет произвести расчет среднего значения и n-й процентиль времени полета между двумя городами на основе данных из файла [tickets.json](https://github.com/MaryGavrilova/TravelApp/blob/44ec9ec59c21d11218c33a3a32c12d26a1dcbdad/tickets.json) 
и настроек программы [application.properties](https://github.com/MaryGavrilova/TravelApp/blob/44ec9ec59c21d11218c33a3a32c12d26a1dcbdad/src/main/resources/application.properties).

## Technologies used
* Java  - version "16.0.2"
* Apache Maven
* Gson
* Junit Jupiter

## Building and running the project
1. склонировать репозиторий с GitHub
2. внести изменения в файл настроек программы `application.propertities`:
* tickets - указать путь к файлу tickets.json
* departure - указать название города вылета
* arrival - указать название города прилета
* percentile - указать размер процентиля от 1 до 100 включительно
3. собрать jar файл - `mvn clean install`
4. запуск программы - `java -jar C:\Users\marzuz\NetologyProjects\TravelApp\target\TravelApp-1.0-SNAPSHOT-jar-with-dependencies.jar` - указать актуальный путь к файлу jar с зависимостями

## Usage
После запуска программы результат вычислений будет выведен в консоль и в .txt файл в корень проекта
```
Результаты вычислений:
90 процентиль времени полета между городами: 9 часов 45 минут
Среднее время полета между городами: 7 часов 32 минут
```
## Tests
Код приложения покрыт unit тестами с использованием библиотеки Junit Jupiter.
```
Tests run: 47, Failures: 0, Errors: 0, Skipped: 0
```
