# Recruitment application (backend) 
This is the backend side of the recruitment application for the course IV1201. The code handles the business logic and 
database integration of the application.  

## Getting Started

Clone the repository to your local machine. The repository is not sensitive to choice of IDE. 

### Prerequisites

This application is built using Java JDK 11, as specified in the POM file.
The various dependencies are already specified in the POM file. 


### Installing

A local database must be setup, a MariaDB database is
 used. If one is to migrate the earlier data that can be found in the <code>db.sql</code> file, then the name of the 
 database must be <code>recruitment_db</code>. 

```
CREATE DATABASE recruitment_db; 
```

In order for the application to contact the database, the URL, username, and password to the database needs to be 
specified. This can be done either by specifying local environment variables, or by updating the properties directly 
in the <code>application.properties</code> file.

```
# Properties specified using environment variables
spring.datasource.url=jdbc:${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
``` 

In order to run the application on localhost you need specify which port to access it on. This can be done be 
setting a local environment variable, or by updating the <code>server.port</code> property in the <code>application.properties</code> file.

```
server.address=0.0.0.0
server.port=${PORT}
```

## Tools 
The following software development tools are used:

```
Version control (Git)
Project management (Maven)
Continous integration, CI (Travis)
Cloud runtime (Heroku)
```


## Frameworks
The following frameworks are used:
```
Spring Boot
Spring Web MVC
Spring Data (Commons and JPA)
Spring Security
Spring MVC Test Framework
JUnit 4 
Hamcrest 
Lombok
Java JWT
```

## Authors 
Oscar Wernöe <br /> 
Mikael Utterbäck <br />
Robin Nilsson
