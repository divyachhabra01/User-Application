# User Application

## Description
This microservice is created using Spring Boot (Spring Data JPA and Spring MVC),Hibernate and MySQL database . In order to run this application you first need to connect this application to a database that is up and running. Provide your database credentials in application.properties file 
## Note: 
You don't need to create DB tables ,it will create itself once you build the Project.
## Authentication OF APIs:
For security reasons each API is secured with a Client key . This client key is present in application.properties file inside resource folder.
In order to run an API you need to add this key in header of API in Postman
 
 * X-Client-Id: yxvayucqyqcm

## APIs
It contains the following apis:
* POST "/sign-up": This API is used for user registration .
===========================================

curl --location --request POST 'http://localhost:8080/sign-up' \
--header 'X-Client-Id: yxvayucqyqcm' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName":"Divya Chhabra",
"password":"12345",
"phoneNumber":"9045580607"
}'

* PATCH "/spam": This API allows user to mark any phone number as spam
==============================================================
  curl --location --request PATCH 'http://localhost:8080/spam?phoneNumber=9045580607' \
  --header 'X-Client-Id: yxvayucqyqcm'


* POST "/sign-in": This API is used for User Login after successful registration.

==================================================================
curl --location --request POST 'http://localhost:8080/sign-in' \
--header 'X-Client-Id: yxvayucqyqcm' \
--header 'Content-Type: application/json' \
--data-raw '{
"phoneNumber":"10455890",
"password":"divy"

}'

* GET "/users": This is a search API that returns a list of users that matches the search criteria 
==============================================================
curl --location --request GET 'http://localhost:8080/users?phoneNumber=9045580607' \
--header 'X-Client-Id: yxvayucqyqcm'


## Technology and Library Used
* Spring Boot
* Hibernate
* MySQL
* Project Lombok
* Mapstruct

