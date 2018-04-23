FROM maven:3.5-jdk-8-slim
WORKDIR /tests
COPY src src
COPY pom.xml regression.xml run.sh ./
RUN mvn dependency:go-offline && \
    mvn dependency:get -Dartifact=org.apache.maven.plugins:maven-surefire-plugin:2.20.1 && \
    mvn dependency:get -Dartifact=org.apache.maven.surefire:surefire-testng:2.20.1 && \
    mvn compile clean

VOLUME /tests/target/allure-results

ENTRYPOINT ["./run.sh"]
CMD ["-Dsut-domain=opencart", "-Dbrowser=remote-firefox", "-Dhub-domain=selenium:4444"]
