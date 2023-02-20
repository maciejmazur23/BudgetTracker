# BudgetTracker

## About the project

Budget Tracker is an application to record and analyze your expenses. The application gives the user the ability to add
income and expenses. The summaries are divided into monthly or yearly with the expenses listed by category.
![Menu](img/menu.png)
![Transactions](img/transactions.png)
![Summary](img/summary1.png)
![Summary](img/summary2.png)

## Technologies

* Spring Boot 3.0.2
* Thymeleaf
* Bootstrap v4.1.3
* Google Charts API

## For developers
Application starts on port localhost:/8090 .
The PostgreSQL database was used in the project.

The following dependencies are downloaded with spring boot: 
* spring-boot-starter-security, 
* spring-boot-starter-data-jpa,
* spring-boot-starter-thymeleaf, 
* spring-boot-starter-web, 
* lombok, 
* logback.

## TODO
* User registration - At this point, only users present in the database can log in to the application.
* Option to export summaries to excel
* Option to add your own categories by users