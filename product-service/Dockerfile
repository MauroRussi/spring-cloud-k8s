# Recommended installation of OpenJDK
FROM eclipse-temurin:17_35-jdk-alpine
MAINTAINER mrussi

# Add useful tools
RUN apk update						# update the local registry
RUN apk add busybox-extras			# install telnet and some other basic tools
RUN apk add curl                    # install curl

# Install the component
COPY build/libs/product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]