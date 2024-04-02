package com.joalvarez.springcontainer.data.repository;

import com.joalvarez.springcontainer.data.model.Product;
import com.joalvarez.springcontainer.data.repository.generals.MultitenantRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MultitenantRepository<Product, Long> {
}
