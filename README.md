# Database Project

This is a school project for Mr. Bajer's class, demonstrating Java database connectivity with MySQL using Docker. To run the project, first start the MySQL database container with `docker compose up -d`, which will initialize the database with the SQL script located in the `initdb/` folder. To stop the containers, use `docker compose down`, and if you need to completely reset the database and remove all data volumes, run `docker compose down -v` before starting again.
