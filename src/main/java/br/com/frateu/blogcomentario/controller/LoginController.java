package br.com.frateu.blogcomentario.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.frateu.blogcomentario.database.DatabaseUsuario;
import br.com.frateu.blogcomentario.model.Usuarios;
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
    public String postLogin(@RequestParam String emailUsuario, @RequestParam String senhaUsuario, HttpSession session) {
        String telaRedirecionamento = "redirect:/";

        Usuarios usuarioLogin = new Usuarios();

        usuarioLogin.setEmailUsuario(emailUsuario);

        DatabaseUsuario dbUsuario = new DatabaseUsuario();

        try {
            usuarioLogin = dbUsuario.consultarUsuario(usuarioLogin);

            if(verificarUsuario(usuarioLogin, senhaUsuario)) {
                telaRedirecionamento = "redirect:/home";
            }
        } catch (Exception e) {
           System.out.println(e); 
        }

        return telaRedirecionamento;
    }
    
    @Cacheable("usuarioLogado")
    private boolean verificarUsuario(Usuarios usuarioLogin, String senhaLogin) {
        boolean verificadorUsuario = false;
        
        try {
            if (BCrypt.checkpw(senhaLogin, usuarioLogin.getSenhaUsuario())) {
                verificadorUsuario = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return verificadorUsuario;
    }

}
