package com.example.companyms.Company.ServiceImpl;


import com.example.companyms.Company.Company;
import com.example.companyms.Company.CompanyRepo;
import com.example.companyms.Company.CompanyService;
import com.example.companyms.Company.clients.ReviewClient;
import com.example.companyms.Company.dto.ReviewMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private CompanyRepo companyRepo;
	private ReviewClient reviewClient;
	

	public CompanyServiceImpl(CompanyRepo companyRepo,ReviewClient reviewClient) {
		this.reviewClient =reviewClient;
		this.companyRepo = companyRepo;
	}


	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = companyRepo.findAll();
		for(Company company : companies){
			List<Double> rating = reviewClient.getRating(company.getId());
			company.setAverage_rating(rating.get(0));
			company.setMedian_rating(rating.get(1));
			companyRepo.save(company);
		}
		return companyRepo.findAll();
		}


	@Override
	public void createCompany(Company company) {
		companyRepo.save(company);
	}


	@Override
	public boolean updateCompany(Company updatedCompany, Long id) {
		Optional<Company> optionalCompany = companyRepo.findById(id);
		if(optionalCompany.isPresent()) {
			Company company = optionalCompany.get();
			company.setDescription(updatedCompany.getDescription());
			company.setName(updatedCompany.getName());
			companyRepo.save(company);
			return true;
			}
		return false;
	}


	@Override
	public boolean deleteCompany(Long id) {
		try {
			companyRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public Company getCompanyById(Long id) {
		return companyRepo.findById(id).orElse(null);
	}

	@Override
	public Company createCompanyForJob(Long id) {
		Company company = new Company();
		company.setId(id);
		company.setName("New Company, coz of absence of company with id");
		company.setDescription("New description");

		companyRepo.save(company);
		return company;
	}

	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		System.out.println("Message from queue "+reviewMessage.getDescription());
	}

}
