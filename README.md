# Favourites-account-manager

[![CircleCI](https://circleci.com/gh/rslvn/bank-account-manager.svg?style=svg)](https://circleci.com/gh/rslvn/bank-account-manager)
[![Coverage Code](https://sonarcloud.io/api/project_badges/measure?project=rslvn_bank-account-manager&metric=coverage)](https://sonarcloud.io/dashboard?id=rslvn_bank-account-manager)
[![Lines Of Code](https://sonarcloud.io/api/project_badges/measure?project=rslvn_bank-account-manager&metric=ncloc)](https://sonarcloud.io/dashboard?id=rslvn_bank-account-manager)
[![Technical Debit](https://sonarcloud.io/api/project_badges/measure?project=rslvn_bank-account-manager&metric=sqale_index)](https://sonarcloud.io/dashboard?id=rslvn_bank-account-manager)

This application is a simple bank account implementation. An account able gets the list of favourite payee linked to a customer, which can be added, deleted, edited with a maximum of 20 favourite accounts allowed.
## Assumptions
- Assumptions have been taken that the customer already exists
- Authentication and authorization will be handled via API gateway

## implementation
- One rest endpoint to add favourite bank accounts
    - `FavAccountController.addFavouriteBankAccount`

### prerequisites
- Java11 
- Maven
- docker

### stacks
- Spring Boot
- Hibernate/JPA
- MySQL
- Lombok
- Flyway
- Swagger

### endpoints

find all endpoint details: [api-docs](http://localhost:8080/swagger-ui.html) after running application


Build the project using
```mvn clean install```

To run the application:
```mvn spring-boot:run```

## run on docker

to build:

    docker build -t FavouriteAccountsAPI:1.0 .

to run;

    docker run FavouriteAccountsAPI:1.0
