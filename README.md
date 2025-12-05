# spring-restful-api-demo
A simple RESTful API built with the Spring Framework, following [this tutorial](https://spring.io/guides/tutorials/rest) and implementing some tweaks.

## Description
This project is intended to serve as the backend for a loan request application. It exposes HTTP endpoints that allow users to create, retrieve, update, and delete loan requests (basic CRUD operations).
As part of the business logic, each loan request can also be **approved** or **rejected**. These operations are implemented using Spring HATEOAS, which provides hypermedia links indicating the valid actions 
available for each resource—without requiring client-side validation or additional custom routing logic.

## Libraries used
- Spring Web
- Spring Data JPA
- Spring HATEOAS
- H2 Database
- Lombok

## Possible improvements
- Add unit tests; currently, endpoint behavior has only been manually tested.
- Replace the in-memory H2 database with a persistent, disk-based option such as MySQL, PostgreSQL, or SQL Server.

