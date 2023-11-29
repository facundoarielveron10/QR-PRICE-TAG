package unnoba.poo.QRPriceTag.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.model.Product;
import unnoba.poo.QRPriceTag.repository.ProductRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public void createProduct(Product product) {
        repository.save(product);
    }
    @Override
    @Transactional
    public void editProduct(Product product) throws Exception {
        // Obtenemos el producto a editar
        Product productEdit = repository.findById(product.getId()).orElseThrow();

        // Actualizamos los datos
        productEdit.setNombre(product.getNombre());
        productEdit.setDescripcion(product.getDescripcion());
        productEdit.setCodigo(product.getCodigo());
        productEdit.setActivo(product.isActivo());

        repository.save(productEdit);
    }
    @Override
    public void deleteProduct(Product product) {
        repository.delete(product);
    }
}
