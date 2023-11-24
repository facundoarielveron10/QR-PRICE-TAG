package unnoba.poo.QRPriceTag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyRepository repository;

    @Override
    public Company createCompany(Company company) throws Exception {
        company = repository.save(company);
        return company;
    }
}
