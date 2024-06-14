package com.example.companyms.Company;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	private String name;
	private String description;

}
