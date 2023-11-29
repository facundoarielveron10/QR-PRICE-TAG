package unnoba.poo.QRPriceTag.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.repository.CompanyRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyRepository repository;
    @Autowired
    private Environment environment;

    @Override
    public void createCompany(Company company) {
        repository.save(company);
    }

    @Override
    @Transactional
    public void editCompany(Company company) throws Exception {
        // Obtenemos la empresa a editar
        Company companyEdit = repository.findById(company.getId()).orElseThrow();
        // Actualizamos los datos
        companyEdit.setRazonSocial(company.getRazonSocial());
        companyEdit.setCuit(company.getCuit());
        if (!company.getLogoFile().isEmpty()) {
            String fileName = company.getLogoFile().getOriginalFilename();
            Path path = Paths.get(environment.getProperty("file.upload-dir") + fileName);
            Files.write(path, company.getLogoFile().getBytes());
            companyEdit.setLogo(fileName);
        }
        companyEdit.setActivo(company.isActivo());

        repository.save(companyEdit);
    }

    @Override
    public void deleteCompany(Company company) {
        repository.delete(company);
    }


}
