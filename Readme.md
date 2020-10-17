# Пример проекта Работа с gmail

Используемые технологии:  
Java, Gradle, Allure, Junit5, Selenide

запуск осуществляется через jenkins+Selenoid
в папке screenshots находятся скриншоты и видео прохождения теста

Проект параметризирован:  
-Dgmail.nameFrom="имя отправителя"
-Dgmail.username="логин gmail"
-Dgmail.password="пароль gmail"

Запись видео прохождения тестов  
        - установить через системные свойства параметр remote_driver_url  
        - установить через системные свойства параметр video_storage_url

Примеры запуска:  
gradle clean gmail -Dgmail.nameFrom=Name -Dgmail.username=login -Dgmail.password=pass -Dremote_driver_url= -Dvideo_storage_url=


К тесту в отчете прикладываются последние скриншоты, видео выполнения и логи.  

![пример отчета](/screenshots/report.png) 
