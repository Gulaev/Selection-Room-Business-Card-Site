FROM openjdk:11-jdk-slim
WORKDIR /app
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

RUN mvn package --quiet
EXPOSE 8080
CMD ["java", "-jar", "./target/springbootdemo-0.1.jar"]