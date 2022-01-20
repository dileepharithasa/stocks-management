FROM openjdk:8
ADD target/Stocks_management.jar stocks_management.jar
ENTRYPOINT ["java","-jar","stocks_management.jar"]
EXPOSE 8085
