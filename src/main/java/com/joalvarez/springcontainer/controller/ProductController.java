package com.joalvarez.springcontainer.controller;

import com.joalvarez.springcontainer.data.dto.ProductDTO;
import com.joalvarez.springcontainer.service.interfaces.IProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

	private final IProductService service;

	public ProductController (IProductService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
	}

}
