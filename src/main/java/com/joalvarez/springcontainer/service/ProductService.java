package com.joalvarez.springcontainer.service;

import com.joalvarez.springcontainer.data.dao.ProductDAO;
import com.joalvarez.springcontainer.data.dto.ProductDTO;
import com.joalvarez.springcontainer.data.mapper.ProductMapper;
import com.joalvarez.springcontainer.service.generals.GenericService;
import com.joalvarez.springcontainer.service.interfaces.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService extends GenericService<ProductDAO, ProductMapper> implements IProductService {

	public ProductService(ProductDAO productDAO, ProductMapper mapper) {
		super(productDAO, mapper);
	}

	@Override
	public List<ProductDTO> findAll() {
		return null;
	}

	@Override
	public ProductDTO findById(Long id) {
		return null;
	}

	@Override
	public ProductDTO update(ProductDTO productDTO) {
		return null;
	}

	@Override
	public ProductDTO create(ProductDTO productDTO) {
		return null;
	}
}
