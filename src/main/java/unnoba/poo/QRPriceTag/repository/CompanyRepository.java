package unnoba.poo.QRPriceTag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.model.User;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    public List<Company> findAll();
}