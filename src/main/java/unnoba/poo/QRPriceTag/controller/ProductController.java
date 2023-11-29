package unnoba.poo.QRPriceTag.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.model.Product;
import unnoba.poo.QRPriceTag.model.User;
import unnoba.poo.QRPriceTag.repository.ProductRepository;
import unnoba.poo.QRPriceTag.service.ProductService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
public class ProductController {
    // ATRIBUTOS
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    // - GET - //
    @GetMapping("/create-product")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/createProduct";
    }
    @GetMapping("/edit-product/{productId}")
    public String editProduct(@PathVariable("productId")Product product, Model model) {
        model.addAttribute("product", product);
        return "/editProduct";
    }
    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("ID de empresa no valido");
        }

        // Obtener el producto a eliminar
        Optional<Product> productToDelete = productRepository.findById(productId);

        // Eliminar el producto y redirigir a la página principal
        productToDelete.ifPresent(product -> productService.deleteProduct(product));

        return "redirect:/home";
    }
    // -------- //

    // - POST - //
    @PostMapping("/create-product")
    public String createProduct(@Valid @ModelAttribute("product")Product product, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "/createProduct";
        }

        try {
            // Obtener el usuario autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User authenticatedUser = (User) authentication.getPrincipal();

            // Obtener la empresa del usuario autenticado
            Company company = authenticatedUser.getCompany();

            // Asignar la empresa al producto
            product.setCompany(company);

            // Crear el producto
            productService.createProduct(product);
        } catch (Exception e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("product", product);
            return "/createProduct";
        }

        return "redirect:/home";
    }
    @PostMapping("/edit-product")
    public String editProduct(@Valid @ModelAttribute("product")Product product, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "/editProduct";
        }

        try {
            productService.editProduct(product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("formError", e.getMessage());
            model.addAttribute("product", product);
            return "/editProduct";
        }

        return "redirect:/home";
    }
    // -------- //
}

