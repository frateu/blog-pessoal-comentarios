package br.com.frateu.blogcomentario.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
    @GetMapping("/")
    public String getLogin(Model model, HttpSession session) {
        if (session.getAttribute("mensagemLogin") != null) {
            model.addAttribute("mensagemLogin", session.getAttribute("mensagemLogin"));
            session.removeAttribute("mensagemLogin");
        }

        return "index";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam String usuario, @RequestParam String password, HttpSession session) {
        System.out.println("Usuario: " + usuario);
        System.out.println("Senha: " + password);

        if (BCrypt.checkpw("frateu", "$2a$10$iE/gm8mwJ1bGK.yzcILRq.BRzZjS5RvJnHpFu5ltjCQ27A5Zkni6O")) {
            session.setAttribute("mensagemLogin", "Foii");
        } else {
            session.setAttribute("mensagemLogin", "não foi");
        }

        return "redirect:/";
    }
    
}
