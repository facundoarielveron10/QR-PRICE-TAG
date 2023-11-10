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
import unnoba.poo.QRPriceTag.model.User;
import unnoba.poo.QRPriceTag.service.UserService;

@Controller
public class UserController {
    // ATRIBUTOS
    @Autowired
    private UserService userService;

    // - GET - //
    @GetMapping("/home")
    public String home(Model model) {
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getPrincipal());
        return "home";
    }

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
