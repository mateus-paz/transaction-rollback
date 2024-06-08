package com.mateuspaz.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateuspaz.transaction.facade.ProductFacade;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/api/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductFacade productFacade;

	@GetMapping("/exception")
	public ResponseEntity<String> exception() {
		return ResponseEntity.ok().body(productFacade.runCheckedException());
	}

	@GetMapping("/runtime-exception")
	public ResponseEntity<String> runtimeException() {
		return ResponseEntity.ok().body(productFacade.runUncheckedException());
	}

	@GetMapping("/runtime-exception/requires-new")
	public ResponseEntity<String> runtimeExceptionWithPropagationRequiresNew() {
		return ResponseEntity.ok().body(productFacade.runUncheckedExceptionWithRequiresNew());
	}

	@GetMapping("/runtime-exception/no-rollback-for")
	public ResponseEntity<String> runtimeExceptionWithNoRollbackFor() {
		return ResponseEntity.ok().body(productFacade.runUncheckedExceptionWithNoRollbackFor());
	}

}
