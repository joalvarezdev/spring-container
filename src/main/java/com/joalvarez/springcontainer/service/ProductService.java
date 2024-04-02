package com.joalvarez.springcontainer.service;

import com.joalvarez.springcontainer.data.dao.ProductDAO;
import com.joalvarez.springcontainer.data.dto.ProductDTO;
import com.joalvarez.springcontainer.data.mapper.ProductMapper;
import com.joalvarez.springcontainer.service.generals.GenericService;
import com.joalvarez.springcontainer.service.interfaces.IProductService;
import com.joalvarez.springcontainer.utils.LocalStorage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService extends GenericService<ProductDAO, ProductMapper> implements IProductService {

	private final LocalStorage storage;
	public ProductService(ProductDAO productDAO, ProductMapper mapper, LocalStorage storage) {
		super(productDAO, mapper);
		this.storage = storage;
	}

	@Override
	public List<ProductDTO> findAll() {
		return this.dao.findAll().stream().map(this.mapper::toDTO).toList();
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
	public ProductDTO create(ProductDTO dto) {
		dto.setUserId(this.storage.getUserDetails().userId());
		return this.mapper.toDTO(this.dao.save(this.mapper.fromDTO(dto)));
	}
}
