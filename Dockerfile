FROM openjdk:8
EXPOSE 9922
ADD target/backend-challenge.jar backend-challenge.jar
ENTRYPOINT ["java","-jar","/backend-challenge.jar"]