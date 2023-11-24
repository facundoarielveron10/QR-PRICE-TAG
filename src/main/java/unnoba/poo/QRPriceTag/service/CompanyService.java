package unnoba.poo.QRPriceTag.service;

import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Company;

@Service
public interface CompanyService {
    public Company createCompany(Company company) throws Exception;
}
