package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.repository.ProductRepository;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getByProductname(String productname) {
        return null;
    }

    @Override
    public Optional<Product> findByProductname(String productname) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDTO> findProductDTOByProductname(String productname) {
        return Optional.empty();
    }

    @Override
    public List<ProductDTO> findAllProductDTO() {
        return productRepository.findAllProductDTO();
    }

    @Override
    public Optional<ProductDTO> findProductDTOByID(Long id) {
        return productRepository.findProductDTOByID(id);
    }


    @Override
    public List<ProductDTO> search(String searchInput) {
        List<ProductDTO> productDTOs = findAllProductDTO();
        List<ProductDTO> listSearch = new ArrayList<>();

        for(ProductDTO productDTO : productDTOs){
            if(productDTO.toString().toLowerCase().contains(searchInput)){
                listSearch.add(productDTO);
            }
        }

        return listSearch;
    }
}
