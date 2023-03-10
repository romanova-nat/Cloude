# Дипломная работа “Облачное хранилище”

## Описание проекта

Разработать приложение - REST-сервис. Сервис должен предоставить REST интерфейс для возможности загрузки файлов и вывода списка уже загруженных файлов пользователя.
Все запросы к сервису должны быть авторизованы. Заранее подготовленное веб-приложение (FRONT) должно подключаться к разработанному сервису без доработок,
а также использовать функционал FRONT для авторизации, загрузки и вывода списка файлов пользователя.

## Требования к приложению:

- Сервис должен предоставлять REST интерфейс для интеграции с FRONT
- Сервис должны реализовывать все методы описанные [yaml файле](./CloudServiceSpecification.yaml):
    1. Вывод списка файлов
    2. Добавление файла
    3. Удаление файла
    4. Авторизация
- Все настройки должны вычитываться из файла настроек (yml)
- Информация о пользователях сервиса (логины для авторизации) и данных должны храниться в базе данных (на выбор студента).

# Принцип работы: