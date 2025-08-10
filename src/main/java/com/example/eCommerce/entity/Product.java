package com.example.eCommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "product_name")
	private String name;

	@NotNull
	@Size(max = 500)
	@Column(name = "product_description")
	private String description;

	@NotNull
	@Positive
	@Column(name = "product_pricre")
	private Double price;

    @Lob
    @Column(name = "product_image")
    private String image; // Base64 encoded image

	// Constructors
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String name, String description, String image, Double prize) {
		this.description = description;
		this.name = name;
		this.price = prize;
		this.image = image;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrize() {
		return price;
	}

	public void setPrize(Double prize) {
		this.price = prize;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}