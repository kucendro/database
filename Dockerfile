# syntax=docker/dockerfile:1
FROM mysql:8.0
COPY seed.sql /docker-entrypoint-initdb.d/