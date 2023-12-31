# Сервис регистрации и отслеживания почтовых отправлений.

### Тестовый проект для имитации работы сервиса работы постовых отправлений.

Выполнен с использованием Spring Boot, JPA, Postgres, REST API.
Авторизация выполнена простым пользователем, хранящимся в памяти.
Покрытие тастами - **более 70%**. Приложен скриншот в корне проекта.

Проект позволяет создавать пользователей(получателей), адреса, почтовые офисы, отправления и треки для отслеживания.

Основные модели сервиса:

**Адрес - Address:**

1.**Long id** - id записи.  
2.**String country** - страна.  
3.**String city** - город.  
4.**String addressLine1** - строка для улицы и дома.  
5.**String addressLine2** - дополнительная строка.  

**Пользователь - Person:**

1.**Long id** - id записи.  
2.**Long passportNumber** - номер паспорта.  
3.**String firstName** - имя пользователя.  
4.**String secondName** - фамилия пользователя.  
5.**Address address** - адрес пользователя.  

**Почтовый офис - PostOffice:**

1.**Long id** - id записи.  
2.**String name** - название офиса.  
3.**int index** - почтовый индекс офиса.  
4.**Address address** - полный адрес офиса.  

**Почтовое отправление - MailPackage:**

1.**Long id** - id записи.  
2.**PackageType type** - тип посылки.  
3.**int receiverIndex** - индекс получателя.  
4.**Address receiverAddress** - адрес получателя.  
5.**Person receiver** - получатель.

Трек - Track:

1.**Long id** - id записи.  
2.**MailPackage mailPackage** - отправление, прикреплённое к треку.  
3.**List<PostOffice> path** - путь посылки.  
4.**int currentState** - текущая позиция на пути.  
5.**boolean arrived** - индикатор окончания пути.  


## Описание API:

### Работа с адресами.
___
**Get - запрос:** /addresses - получить список всех записей.  
В ответе - List адресов.  

**Post - запрос:** /addresses - создать новую запись.  
В теле запроса передаётся JSON представление нового объекта Address.  
В ответе - Сохранённый новый адрес либо ошибки валидации.  

**Get - запрос:** /addresses/{id} - получить запись по id.  
В ответе - Адрес с id из запроса либо ошибка 404.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Put - запрос:** /addresses/{id} - изменить запись по id.  
В теле запроса передаётся JSON представление объекта Address, содержащий данные для внесения.  
В ответе - Сохранённый изменённый или новый адрес, или ошибки валидации.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Delete - запрос:** /addresses/{id} - удалить запись по id.  
В ответе - Строка true при успешном удалении.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

### Работа с данными пользователя.
___
**Get - запрос:** /persons - получить список всех записей.  
В ответе - List пользователей.  

**Post - запрос:** /persons - создать новую запись.  
В теле запроса передаётся JSON представление нового объекта Person.  
В ответе - Сохранённый новый пользователь либо ошибки валидации.  
При передаче нового адреса с пользователем, то он сохраняется.  

**Get - запрос:** /persons/{id} - получить запись по id.  
В ответе - Пользователь с id из запроса либо ошибка 404.  

**Put - запрос:** /persons/{id} - изменить запись по id.  
В теле запроса передаётся JSON представление объекта Person, содержащий данные для внесения.  
В ответе - Сохранённый изменённый или новый пользователь, или ошибки валидации.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Delete - запрос:** /persons/{id} - удалить запись по id.  
В ответе - Строка true при успешном удалении либо ошибка 404.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Post - запрос:** /persons/{personId}/addAddress/{addressId} - добавления адреса пользователю. 
В ответе - Изменённый пользователь с новым адресом. Если адрес новый, то сохраняется в базе.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

### Работа с данными почтовых отправлений.
___
**Get - запрос:** /mailpackages - получить список всех записей.  
В ответе - List отправлений.  

**Post - запрос:** /mailpackages - создать новую запись.  
В теле запроса передаётся JSON представление нового объекта MailPackage.  
В ответе - Сохранённое новое отправление либо ошибки валидации.  
При передаче нового адреса с отправлением, то он сохраняется.  
При передаче нового пользователя с отправлением, то он сохраняется.  

**Get - запрос:** /mailpackages/{id} - получить запись по id.  
В ответе - Отправление с id из запроса либо ошибка 404.  

**Put - запрос:** /mailpackages/{id} - изменить запись по id.  
В теле запроса передаётся JSON представление объекта MailPackage, содержащий данные для внесения.  
В ответе - Сохранённое изменённое или новое отправление, или ошибки валидации.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Delete - запрос:** /mailpackages/{id} - удалить запись по id.  
В ответе - Строка true при успешном удалении либо ошибка 404.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

### Работа с данными почтовых офисов.
___
**Get - запрос:** /postoffices - получить список всех записей.  
В ответе - List офисов.  

**Post - запрос:** /postoffices - создать новую запись.  
В теле запроса передаётся JSON представление нового объекта PostOffice.  
В ответе - Сохранённый новый офис либо ошибки валидации.  
При передаче нового адреса, то он сохраняется.  

**Get - запрос:** /postoffices/{id} - получить запись по id.  
В ответе - Офис с id из запроса либо ошибка 404.  

**Put - запрос:** /postoffices/{id} - изменить запись по id.  
В теле запроса передаётся JSON представление объекта PostOffice, содержащий данные для внесения.  
В ответе - Сохранённый изменённый или новый офис, или ошибки валидации.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Delete - запрос:** /postoffices/{id} - удалить запись по id.  
В ответе - Строка true при успешном удалении либо ошибка 404.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

### Работа с данными треков.
___
**Get - запрос:** /tracks - получить список всех записей.  
В ответе - List треков.  

**Post - запрос:** /tracks - создать новую запись.  
В теле запроса передаётся JSON представление нового объекта Track.  
В ответе - Сохранённый новый трек либо ошибки валидации.  

**Get - запрос:** /tracks/{id} - получить запись по id.  
В ответе - Трек с id из запроса либо ошибка 404.  

**Put - запрос:** /tracks/{id} - изменить запись по id.  
В теле запроса передаётся JSON представление объекта Track, содержащий данные для внесения.  
В ответе - Сохранённый изменённый или новый трек, или ошибки валидации.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Delete - запрос:** /tracks/{id} - удалить запись по id.  
В ответе - Строка true при успешном удалении либо ошибка 404.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Post - запрос:** /tracks/{trackId}/addPackage/{packageId} - добавление отправления к треку.  
В ответе - Сохранённый изменённый трек.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Post - запрос:** /tracks/{trackId}/addStart/{packageId} - добавление точки отправления к треку.  
В ответе - Сохранённый изменённый трек.  
При попытке обратиться к не существующему id выдаётся ошибка 404.  

**Get - запрос:** /tracks/path/{id} - проверка нахождения посылки по id трека.  
В ответе - Офис на котором находится посылка в данный момент.  

**Put - запрос:** /tracks/path/{id} - добавление офиса к пути посылки.  
В теле запроса передаётся id офиса после которого добавляется и id добавляемого офиса.  
В ответе - Сохранённый изменённый трек.  

## Возможные улучшения проекта
___

1. Добавить трек-номер для отслеживания.  
2. Добавить отправителя к отправлению.