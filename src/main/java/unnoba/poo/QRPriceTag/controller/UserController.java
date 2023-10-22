package unnoba.poo.QRPriceTag.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import unnoba.poo.QRPriceTag.model.User;
import unnoba.poo.QRPriceTag.service.UserService;

@Controller
public class UserController {
    // ATRIBUTOS
    @Autowired
    private UserService userService;

    // - GET - //
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
    // ------- //

    // - POST - //
    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute("user")User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }

        try {
            userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("user", user);
            return "/register";
        }

        return "redirect:/";
    }
    // -------- //
}
