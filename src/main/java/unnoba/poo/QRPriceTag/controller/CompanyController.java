package unnoba.poo.QRPriceTag.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.model.Product;
import unnoba.poo.QRPriceTag.model.User;
import unnoba.poo.QRPriceTag.repository.CompanyRepository;
import unnoba.poo.QRPriceTag.repository.ProductRepository;
import unnoba.poo.QRPriceTag.service.CompanyService;
import unnoba.poo.QRPriceTag.service.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class CompanyController {
    // ATRIBUTOS
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Environment environment;

    // - GET - //
    @GetMapping("/create-company")
    public String createCompany(Model model) {
        model.addAttribute("company", new Company());
        return "/createCompany";
    }
    @GetMapping("/edit-company/{companyId}")
    public String editCompany(@PathVariable("companyId") Company company, Model model) {
        model.addAttribute("company", company);
        // Obtener todos los usuarios asociados a la empresa
        List<User> companyUsers = company.getUsers();
        model.addAttribute("companyUsers", companyUsers);
        // Obtener todos los productos que sean de la empresa a editar
        List<Product> products = productRepository.findByCompany(company);
        model.addAttribute("products", products);
        return "/editCompany";
    }
    @GetMapping("/delete-company/{companyId}")
    public String deleteCompany(@PathVariable Long companyId) throws Exception {
        if (companyId == null) {
            throw new IllegalArgumentException("ID de empresa no valido");
        }

        // Obtener la empresa a eliminar
        Optional<Company> companyToDelete = companyRepository.findById(companyId);

        // Eliminar todos los usuarios asociados a la empresa
        companyToDelete.ifPresent(company -> {
            List<User> usersToDelete = company.getUsers();
            for (User user : usersToDelete) {
                userService.deleteUser(user);
            }
        });

        // Eliminar la empresa y redirigir a la página principal
        companyToDelete.ifPresent(company -> companyService.deleteCompany(company));

        return "redirect:/home";
    }
    // -------- //

    // - POST - //
    @PostMapping("/create-company")
    public String createCompany(@Valid @ModelAttribute("company")Company company, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("company", company);
            return "/createCompany";
        }

        if (company.getLogoFile() == null || company.getLogoFile().isEmpty()) {
            result.rejectValue("logoFile", "NotEmpty", "El logo es obligatorio");
            model.addAttribute("logoError", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return "/createCompany";
        }

        try {
            String fileName = company.getLogoFile().getOriginalFilename();
            Path path = Paths.get(environment.getProperty("file.upload-dir") + fileName);
            Files.write(path, company.getLogoFile().getBytes());
            company.setLogo(fileName);
            companyService.createCompany(company);
        } catch (Exception e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("company", company);
            return "/createCompany";
        }

        return "redirect:/home";
    }
    @PostMapping("/edit-company")
    public String editCompany(@Valid @ModelAttribute("company")Company company, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("company", company);
            return "/editCompany";
        }

        try {
            companyService.editCompany(company);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("formError", e.getMessage());
            model.addAttribute("company", company);
            return "redirect:/edit-company/" + company.getId();
        }

        return "redirect:/home";
    }
    // -------- //
}

