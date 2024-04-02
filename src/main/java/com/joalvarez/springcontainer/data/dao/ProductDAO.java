package com.joalvarez.springcontainer.data.dao;

import com.joalvarez.springcontainer.data.dao.generals.MultitenantDAO;
import com.joalvarez.springcontainer.data.model.Product;
import com.joalvarez.springcontainer.data.repository.ProductRepository;
import com.joalvarez.springcontainer.utils.LocalStorage;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO extends MultitenantDAO<ProductRepository, Product, Long> {

	public ProductDAO(ProductRepository repository, LocalStorage localStorage) {
		super(repository, localStorage);
	}
}
