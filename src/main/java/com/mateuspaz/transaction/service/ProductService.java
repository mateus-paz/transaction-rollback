package com.mateuspaz.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mateuspaz.transaction.domain.entity.Product;
import com.mateuspaz.transaction.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;

	public void throwUncheckedException() {
		productRepository.save(new Product());
		throw new RuntimeException("RuntimeException");
	}

	public void throwCheckedException() throws Exception {
		productRepository.save(new Product());
		throw new Exception("Exception");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void throwUncheckedExceptionWithRequiresNew() {
		productRepository.save(new Product());
		throw new RuntimeException("RuntimeException :: propagation = Propagation.REQUIRES_NEW");
	}

	@Transactional(noRollbackFor = RuntimeException.class)
	public void throwUncheckedExceptionWithNoRollbackFor() {
		productRepository.save(new Product());
		throw new RuntimeException("RuntimeException :: noRollbackFor = RuntimeException.class");
	}

}
