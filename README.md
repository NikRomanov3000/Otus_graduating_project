# Otus_graduating_project
Самописная библиотека, которая содержит все модели проекта. Для общения между сервисами предусмотрена отдельная модель PaymentInfo.

Подключение:
1) Собрать Jar-ник
2) В консоли прописать mvn install:install-file -Dfile=<Пусть к архиву> -DgroupId=models -DartifactId=models -Dversion=1.0-SNAPSHOT -Dpackaging=jar
3) Проверить наличие в pom.xml зависимост:
<dependency>
	<groupId>models</groupId>
	<artifactId>models</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>

4) Если зависимость не подтягивается автоматически Alt+Enter: Update Maven Indices 

