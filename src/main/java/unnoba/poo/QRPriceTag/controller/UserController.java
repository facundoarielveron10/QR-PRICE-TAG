package unnoba.poo.QRPriceTag.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unnoba.poo.QRPriceTag.model.Company;
import unnoba.poo.QRPriceTag.model.User;
import unnoba.poo.QRPriceTag.repository.CompanyRepository;
import unnoba.poo.QRPriceTag.service.CompanyService;
import unnoba.poo.QRPriceTag.service.UserService;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    // ATRIBUTOS
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    // - GET - //
    @GetMapping("/home")
    public String home(Model model) {
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getPrincipal());
        // Obtener todas las empresas
        List<Company> companies = Company.getAllCompanies(companyRepository);
        model.addAttribute("companies", companies);
        return "home";
    }

    @GetMapping("/create-user")
    public String createUser(Model model) {
        // Obtener todas las empresas
        List<Company> companies = Company.getAllCompanies(companyRepository);
        model.addAttribute("companies", companies);
        // Enviamos un modelo de usuario vacio
        model.addAttribute("user", new User());
        return "/createUser";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    // ------- //

    // - POST - //
    @PostMapping("/create-user")
    public String createUser(@Valid @ModelAttribute("user")User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            // Obtener todas las empresas
            List<Company> companies = Company.getAllCompanies(companyRepository);
            model.addAttribute("companies", companies);
            // Enviamos los datos del usuario
            model.addAttribute("user", user);
            return "/createUser";
        }

        try {
            User newUser = userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("user", user);
            return "/createUser";
        }

        return "redirect:/home";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    // -------- //
}
