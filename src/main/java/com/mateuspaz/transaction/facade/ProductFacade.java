package com.mateuspaz.transaction.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateuspaz.transaction.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductFacade {

	private final ProductService productService;

	public String runCheckedException() {
		String errorMessage = null;

		try {
			productService.throwCheckedException();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

		return errorMessage;
	}

	public String runUncheckedException() {
		String errorMessage = null;

		try {
			productService.throwUncheckedException();
		} catch (RuntimeException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

		return errorMessage;
	}

	public String runUncheckedExceptionWithRequiresNew() {
		String errorMessage = null;

		try {
			productService.throwUncheckedExceptionWithRequiresNew();
		} catch (RuntimeException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

		return errorMessage;
	}

	public String runUncheckedExceptionWithNoRollbackFor() {
		String errorMessage = null;

		try {
			productService.throwUncheckedExceptionWithNoRollbackFor();
		} catch (RuntimeException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

		return errorMessage;
	}

}
