FROM java:8-jre
WORKDIR /usr/src

COPY ./target/GipherRecommendationModule-0.0.1-SNAPSHOT.jar GipherRecommendationModule-0.0.1-SNAPSHOT.jar
#EXPOSE 8083
ENTRYPOINT ["java","-jar","/usr/src/GipherRecommendationModule-0.0.1-SNAPSHOT.jar"]
