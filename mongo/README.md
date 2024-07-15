# Introduction

The purpose of this project is to develop an API to manage an insurance company's customer data. MongoDB was used for database managment. Spring was used to develop the API. Maven was used for dependency management.

# Quick Start

The first thing that needs to be done is ensure that the relevant ports assigned in the application.properties file are free and usable. Second you turn on the MongoDB server. Third you start the application by running the main method in the InsuranceApiApplication class. Now you should be able to issue and process requests and use all of the application's features.

# Implementation

First I created the MongoDB database which runs on localhost port 27017 and created a collection called people. I created classes in 4 different packages. The first was model which holds the classes defining the 3 different entities in the database. The second was controller which holds the PersonController class which handles requests. The third was repository which holds the interfaces and classes in which the methods that control the application behaviour were defined and implemented. The fourth was service which contains the PersonService class which acts as the intermediary between the controller and the repositories.

# Testing

Swagger and Postman were used for testing functionalities.

# Deployment

The app was deployed using Spring and MongoDB. 

# Improvments

One possible improvement is creating a GUI to display the current entities stored in the database so the information is more easily visible to users. Another improvement would be to design a GUI to better facilitate the issuing of requests in a more user friendly manner.
