package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    Product getByProductname(String productname);

    Optional<Product> findByProductname(String productname);

    Optional<ProductDTO> findProductDTOByProductname(String productname);

    List<ProductDTO> findAllProductDTO();

    Optional<ProductDTO> findProductDTOByID(Long id);

    List<ProductDTO> search(String searchInput);

    List<ProductDTO> filterPrice(String price);

}