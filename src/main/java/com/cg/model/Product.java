package com.cg.model;


import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @OneToOne(targetEntity = Category.class,fetch = FetchType.EAGER)
    private Category category;

    @OneToOne(targetEntity = Status.class,fetch = FetchType.EAGER)
    private Status status;

    private String urlImage;

    private long price;

    private int quantity;


    private String year;

    @OneToOne(targetEntity = Capacity.class,fetch = FetchType.EAGER)
    private Capacity capacity;


    private String origin;

    private boolean deleted;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(id)
                .setProductName(productName)
                .setCategory(category)
                .setUrlImage(urlImage)
                .setPrice(Long.toString(price))
                .setQuantity(Integer.toString(quantity))
                .setYear(year)
                .setCapacity(capacity)
                .setOrigin(origin)
                .setStatus(status)
                .setDeleted(deleted);
    }
}
