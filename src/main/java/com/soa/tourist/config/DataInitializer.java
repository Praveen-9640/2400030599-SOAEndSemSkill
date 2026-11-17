package com.soa.tourist.config;

import com.soa.tourist.model.Tourist;
import com.soa.tourist.repository.TouristRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class DataInitializer {
	@Bean
	CommandLineRunner init(TouristRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				repo.save(new Tourist("Praveen", "Praveen@example.com", "Hyderabad", "9876543210"));
				repo.save(new Tourist("Dinesh", "Dinesh@example.com", "Goa", "9123456780"));
			}
		};
	}
}
