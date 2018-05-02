# Blogroulette JavaEE Backend
[![Build Status](https://travis-ci.org/blogroulette/backend-jee.svg?branch=master)](https://travis-ci.org/blogroulette/backend-jee)

This is the backend for the blogroulette web application. 
It is written in JavaEE and provides a REST-ful API to the frontend.
The component is developed and tested with JavaEE/openJDK1.8 and JBoss/Wildfly 
12 as graded project in an university lecture on JavaEE and is designed to match
the [frontend written in Angular](https://github.com/blogroulette/frontend-angular).


## Run it
You will need a MySQL/MariaDB Database Server running and prepared for this application.

To build this project, you need a Java JDK version 1.8 or compatible and Maver version 3 or compatible.
To run it, execute ```mvn clean install``` which builds the target/blogroulette.war file to be used by 
an web application server like Wildfly.

We also provide a docker container for this backend on DockerHub. You can use it via  
```docker run -d -p 8080:8080 blogroulette/backend-jee```  
A docker compose file to start up the whole application is currently work in progress.
