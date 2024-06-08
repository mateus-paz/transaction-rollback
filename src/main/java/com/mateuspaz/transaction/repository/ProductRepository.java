package com.mateuspaz.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateuspaz.transaction.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
