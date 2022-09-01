package com.rayyan.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String name;
    private String productImage;
    private double unitPrice;
    private String productCategory;
    private String productStatus;
    private int productInventory;
    private String productDescription;
    private String productCondition;
    
    public Product(String name, String image, double unitPrice, String category, String status, int inventory, String description, String condition)
    {
        this.name = name;
        this.productImage = image;
        this.unitPrice = unitPrice;
        this.productCategory = category;
        this.productStatus = status;
        this.productInventory = inventory;
        this.productDescription = description;
        this.productCondition = condition;
    }
}
