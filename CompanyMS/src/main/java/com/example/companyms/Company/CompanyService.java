package com.example.companyms.Company;

import java.util.List;

public interface CompanyService {

	List<Company> getAllCompanies();
	void createCompany(Company company);
	boolean updateCompany(Company updatedCompany, Long id);
	boolean deleteCompany(Long id);
	Company getCompanyById(Long id);
	Company createCompanyForJob(Long id);
}
