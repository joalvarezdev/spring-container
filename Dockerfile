FROM joalvarezdev/springboot-alpine-17:latest

MAINTAINER joalvarez

COPY target/Spring Container.jar .

EXPOSE 8090

ENTRYPOINT ["java","-jar","Spring Container.jar"]
