package unnoba.poo.QRPriceTag.service;

import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Company;

@Service
public interface CompanyService {
    public void createCompany(Company company);
    public void editCompany(Company company) throws Exception;
    public void deleteCompany(Company company);
}
