package unnoba.poo.QRPriceTag.service;

import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Product;

@Service
public interface ProductService {
    public void createProduct(Product product);
    public void editProduct(Product product) throws Exception;
    public void deleteProduct(Product product);
}
