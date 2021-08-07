FROM alpine:3.14

EXPOSE 8085 
ENTRYPOINT ["java" , "-jar","docker-spring-boot.jar"]