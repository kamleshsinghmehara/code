package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity Class for nutrition value parameters of a smoothie
 * @author Kamlesh
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NutritionValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "nutritionValue")
	@JsonBackReference
	private Product product;

	private Double carbohydrate;

	private Double protein;

	private Double fat;

	private Double cholestrol;

	private Double sodium;

	private Double potassium;

	private Double calcium;

	private Double iron;

	private Double calories;

}
