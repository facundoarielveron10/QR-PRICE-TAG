package unnoba.poo.QRPriceTag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.model.Product;
import unnoba.poo.QRPriceTag.model.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.company = :company")
    List<Product> findByCompany(@Param("company") Company company);
}
