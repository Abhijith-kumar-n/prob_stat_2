FROM maven
WORKDIR /workspace/app
COPY mvnw .
COPY pom.xml .
COPY src src
RUN mvn -DskipTests=true  package
WORKDIR /workspace/app/target
RUN mv *.war customjson.war
CMD ["java","-jar","customjson.war"]