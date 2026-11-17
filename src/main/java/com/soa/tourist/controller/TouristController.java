package com.soa.tourist.controller;

import com.soa.tourist.dto.TouristUpdateRequest;
import com.soa.tourist.model.Tourist;
import com.soa.tourist.repository.TouristRepository;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/tourists")
@Tag(name = "Tourist API")
public class TouristController {
	private final TouristRepository repository;

	public TouristController(TouristRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTourist(@PathVariable Long id) {
		return repository.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(404).body(Map.of("message", "Tourist not found")));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update tourist after validating Token header", description = "Paste the pre-generated JWT directly into the Token header; do not add Bearer.", parameters = @Parameter(name = "Token", in = ParameterIn.HEADER, required = true, description = "Pre-generated JWT token"))
	public ResponseEntity<?> updateTourist(@PathVariable Long id, @RequestBody TouristUpdateRequest body) {
		Tourist t = repository.findById(id).orElse(null);
		if (t == null)
			return ResponseEntity.status(404).body(Map.of("message", "Tourist not found for id: " + id));
		if (blank(body.getName()) || blank(body.getEmail()) || blank(body.getVisitedDestination())
				|| blank(body.getContactNumber()))
			return ResponseEntity.badRequest()
					.body(Map.of("message", "name, email, visitedDestination and contactNumber are required"));
		t.setName(body.getName());
		t.setEmail(body.getEmail());
		t.setVisitedDestination(body.getVisitedDestination());
		t.setContactNumber(body.getContactNumber());
		return ResponseEntity.ok(Map.of("message", "Tourist Updated Successfully", "tourist", repository.save(t)));
	}

	private boolean blank(String s) {
		return s == null || s.isBlank();
	}
}
