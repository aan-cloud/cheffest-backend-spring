package com.cheffest.customer_service_api100;

//import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		// Load .env file, but ignore if it's missing (useful for production)
//		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
//		System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
//		System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
//		System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
//		System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
//		System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));

		// Continue with Spring Boot initialization
		SpringApplication.run(Application.class, args);
	}
}
