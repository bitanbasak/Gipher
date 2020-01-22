FROM java:8-jre
WORKDIR /usr/src

COPY ./target/GipherCommentModule-0.0.1-SNAPSHOT.jar GipherCommentModule-0.0.1-SNAPSHOT.jar
#EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/src/GipherCommentModule-0.0.1-SNAPSHOT.jar"]
