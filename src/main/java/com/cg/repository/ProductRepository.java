package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Product getByProductName (String productName);
//    Option<Product> findProductByProductName(String productName);
//    @Query("SELECT NEW com.cg.model.dto.ProductDTO (p.id, p.productName, p.category, p.urlImage, p.price, p.quantity, p.deleted) FROM Product p WHERE p.productName = ?1")
//    Optional<UserDTO> findUserDTOByUsername(String username);

    @Query ("SELECT NEW com.cg.model.dto.ProductDTO (" +
                "p.id, " +
                "p.productName," +
                "p.category, " +
                "p.urlImage, " +
                "p.price, " +
                "p.quantity, " +
                "p.year, " +
                "p.capacity, " +
                "p.origin, " +
                "p.deleted, " +
                "p.status" +
            ") " +
            "FROM Product AS p " +
            "ORDER BY p.id"
    )
    List<ProductDTO> findAllProductDTO();

    @Query ("SELECT NEW com.cg.model.dto.ProductDTO (" +
            "p.id," +
            "p.productName," +
            "p.category, " +
            "p.urlImage, " +
            "p.price, " +
            "p.quantity, " +
            "p.year, " +
            "p.capacity, " +
            "p.origin, " +
            "p.deleted, " +
            "p.status" +
        ")" +
            "FROM Product AS p " +
            "WHERE p.id = :id"
    )
    Optional<ProductDTO> findProductDTOByID(@Param("id") long id);
}
