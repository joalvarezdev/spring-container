package com.joalvarez.springcontainer.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.springcontainer.data.dto.ProductDTO;
import com.joalvarez.springcontainer.data.mapper.generals.BaseMapper;
import com.joalvarez.springcontainer.data.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<ProductDTO, Product> {

	public ProductMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
