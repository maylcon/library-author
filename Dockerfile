# Usar una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-alpine

# Variables de entorno para la contraseña de SYS y PDB_ADMIN
ENV SPRING_DATASOURCE_URL jdbc:oracle:thin:@oracle-xe-container:1521:XE
ENV SPRING_DATASOURCE_USERNAME C##maylcon
ENV SPRING_DATASOURCE_PASSWORD maylcon123
ENV SPRING_JPA_HIBERNATE_DDL_AUTO update
ENV SERVER_PORT 8181

# Copiar el archivo JAR de tu aplicación al contenedor
COPY target/library-0.0.1-SNAPSHOT.jar /app/library-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que tu aplicación escucha las solicitudes
EXPOSE 8181

# Comando para ejecutar tu aplicación Spring Boot cuando el contenedor se inicie
CMD ["java", "-jar", "/app/library-0.0.1-SNAPSHOT.jar"]