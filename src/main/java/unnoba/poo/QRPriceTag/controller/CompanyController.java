package unnoba.poo.QRPriceTag.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.service.CompanyService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
public class CompanyController {
    // ATRIBUTOS
    @Autowired
    private CompanyService companyService;
    @Autowired
    private Environment environment;

    // - GET - //
    @GetMapping("/create-company")
    public String createCompany(Model model) {
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getPrincipal());
        model.addAttribute("company", new Company());
        return "createCompany";
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
    // -------- //
}

