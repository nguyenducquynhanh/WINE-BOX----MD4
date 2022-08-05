package com.cg.model.dto;


import com.cg.model.Capacity;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.Status;
import com.cg.utils.ValidDateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDTO implements Validator {

    private Long id;

    @NotBlank(message = "Wine name is not blank!")
    @Size(min = 3, max = 100000, message = "Length of wine name in between 3 to 100.000")
    private String productName;

    private Category category;

    @NotBlank(message = "Url image is not blank!")
    @Size(min = 10, max = 10000, message = "Length of url image in between 10 to 10.000")
    private String urlImage;

    private String price;

    private String quantity;

    @NotBlank(message = "Year is not blank!")
    @Size(min = 4, max = 4, message = "Invalid year!")
    private String year;


    private Capacity capacity;

    @NotBlank(message = "Origin is not blank!")
    private String origin;

    private boolean deleted;

    private Status status;

    public Product toProduct() {
        return new Product()
//                .setId(id)
                .setProductName(productName)
                .setCategory(category)
                .setUrlImage(urlImage)
                .setPrice(Long.parseLong(price))
                .setQuantity(Integer.parseInt(quantity))
                .setYear(year)
                .setCapacity(capacity)
                .setOrigin(origin)
                .setStatus(status)
                .setDeleted(deleted);
    }

    public ProductDTO(Long id, String productName, Category category, String urlImage, Long price, Integer quantity, String year, Capacity capacity, String origin, boolean deleted, Status status) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.urlImage = urlImage;
        this.price = String.valueOf(price);
        this.quantity = String.valueOf(quantity);
        this.year = year;
        this.capacity = capacity;
        this.origin = origin;
        this.deleted = deleted;
        this.status = status;
    }

    @Override
    public String toString() {
        return productName + " " + category.getCategory() + " " + price + " " + quantity + " " + capacity.getCapacity();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO productDTO = (ProductDTO) o;
        String quantity = productDTO.getQuantity();
        String price = productDTO.getPrice();

        if (!ValidDateUtils.isNumberValid(quantity)) {

            if (quantity == null || quantity.equals("")) {
                errors.rejectValue("quantity", "400", "Quantity is not blank!");
            } else {
                errors.rejectValue("quantity", "400", "Invalid quantity!");
            }

        } else {

            if (quantity.length() > 4) {
                errors.rejectValue("quantity", "400", "Maximum of wine quantity is 1000!");
            } else {

                long validQuantity = Integer.parseInt(quantity);
                if (validQuantity < 0) {
                    errors.rejectValue("quantity", "400", "Wine quantity cannot be a negative number!");
                }

                if (validQuantity > 1000) {
                    errors.rejectValue("quantity", "400", "Maximum of wine quantity is 1000!");
                }
            }
        }


        if (!ValidDateUtils.isNumberValid(price)) {

            if (price == null || price.equals("")) {
                errors.rejectValue("price", "400", "Price is not blank!");
            } else {
                errors.rejectValue("price", "400", "Invalid price!");
            }

        } else {
            if (price.length() > 5) {
                errors.rejectValue("price", "400", "Maximum of wine price is 10.000$!");
            } else {

                long validPrice = Long.parseLong(price);
                if (validPrice < 0) {
                    errors.rejectValue("price", "400", "Wine price cannot be a negative number!");
                }

                if (validPrice > 10000) {
                    errors.rejectValue("price", "400", "Maximum of wine price is 10.000$!");
                }
            }
        }
    }
}

