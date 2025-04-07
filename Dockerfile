# Menggunakan image OpenJDK sebagai base image
FROM openjdk:17-jdk-slim

# Menentukan direktori kerja di dalam container
WORKDIR /app

# Menyalin file JAR ke dalam container
COPY target/customer-service-api-1.0.0-0.0.1-SNAPSHOT.jar /app/app.jar

#COPY .env .env

# Menjalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

# Mengexpose port yang digunakan oleh aplikasi
EXPOSE 8080
