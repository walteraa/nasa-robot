#Use Java image
FROM java:7-jdk-alpine

#Open 8080 port in container
EXPOSE 8080

#Create the workspace directory
RUN mkdir -p /var/apps/nasa-robot

#Copy the build into the workspace directory
ADD target/NasaRobot-0.0.1-SNAPSHOT.jar /var/apps/nasa-robot/webapp.jar

WORKDIR /var/apps/nasa-robot

CMD ["java", "-jar", "webapp.jar"]
