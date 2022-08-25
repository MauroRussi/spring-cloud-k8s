# Recommended installation of OpenJDK
FROM eclipse-temurin:17_35-jdk-alpine

# Add useful tools
RUN apk update						# update the local registry
RUN apk add busybox-extras			# install telnet and some other basic tools
RUN apk add curl                    # install curl

# Install the component
COPY build/libs/shopping-service-0.0.1-SNAPSHOT.jar shopping-service.jar
ENTRYPOINT ["java", "-jar", "/shopping-service.jar"]