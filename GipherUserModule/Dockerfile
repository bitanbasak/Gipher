FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_HOST=localhost
ENV MYSQL_DATABASE=giphy
ENV MYSQL_USER=dbuser1
ENV MYSQL_PASSWORD=pass
COPY ./target/GipherUserModule-0.0.1-SNAPSHOT.jar GipherUserModule-0.0.1-SNAPSHOT.jar
#EXPOSE 8084
ENTRYPOINT ["java","-jar","/usr/src/GipherUserModule-0.0.1-SNAPSHOT.jar"]
