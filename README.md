# Database Project

This is a school project for Mr. Bajer's class, demonstrating Java database connectivity with MySQL using Docker. To run the project, you have to have docker enviroment on PC!!!
Program will automaticaly deploy SQL database.


Abstract:

The core of the app will has HashMap to store products etc. but on the start and on the end will write the data to the database.
Or another approach is to create ORM to directly write/update the changes to database since its written to the HashMap.

Disconnect from db after write/delete, select only on start. No need for the singleton, just connect every time


On object save method - new object (call insert) - update (call update)

MySQL database is deployed via docker and the SEED data are initialised.