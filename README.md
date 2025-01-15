# Weather Sensor API
___

# Описание проекта

Этот проект представляет собой REST API сервис, разработанный с использованием **Spring Boot**. Сервис предназначен для получения данных от условного "сенсора", который отправляет HTTP-запросы с измерениями температуры и погодных условий. Он включает в себя регистрацию сенсоров, обработку измерений и возвращение статистики по количеству дождливых дней.

Проект включает следующие основные функциональные возможности:
- Регистрация сенсоров.
- Отправка измерений с данными о температуре и погодных условиях.
- Получение всех измерений.
- Получение статистики о количестве дождливых дней.

Также реализована защита API с помощью **JWT** (JSON Web Token) для авторизации пользователей.(Пока не реализована, но скоро будет;) )
___
# Структура проекта

- **Controllers**: REST контроллеры для обработки HTTP-запросов.
- **Services**: Логика приложения для работы с данными сенсоров и измерений.
- **Repositories**: Репозитории для взаимодействия с базой данных.
- **DTO**: Объекты передачи данных для регистрации сенсоров и отправки измерений.

___
# Установка и запуск

## 1. Склонировать репозиторий

Склонируйте репозиторий на свой локальный компьютер:

git clone https://github.com/your-username/weather-sensor-api.git
cd weather-sensor-api

## 2. Запуск с использованием Docker Compose
собираем проект через **mvn clean package**
поднимаем контейнеры через **docker compose up**
Это запустит:

Spring Boot приложение в контейнере с портом 8080.

PostgreSQL базу данных в контейнере.
____
## Пример использования API
### Регистрация сенсора
Метод: **POST /sensors/registration**

Пример запроса:

json
{
"name": "sensorName"
}

### Добавление измерений
- Метод: **POST /measurements/add**

Пример запроса:

{
"value": 23.5,
"raining": true,
"sensor": {
"name": "sensorName"
}
}

### Получение всех измерений
- Метод: **GET /measurements**

### Получение количества дождливых дней
- Метод: **GET /measurements/rainyDaysCount**

### Заполнение случайными данными и вывод полученного результата:
- Метод: **GET /randoms**
