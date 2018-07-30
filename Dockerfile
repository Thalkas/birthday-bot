FROM openjdk:10-jre-slim

WORKDIR /etc/birthdaybot

# Copy dir to the image
COPY ./ ./

# Make gradle wrapper executable
USER root
RUN sudo chmod +x ./gradlew

# Build jar
RUN ./gradlew build

ENTRYPOINT java -jar ./Server/build/libs/BirthdayBot-all.jar