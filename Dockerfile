FROM adoptopenjdk/openjdk8
COPY artificats/ app/
ENTRYPOINT ["java","-jar","app/wirelessmesh-1.0-SNAPSHOT.jar"]
