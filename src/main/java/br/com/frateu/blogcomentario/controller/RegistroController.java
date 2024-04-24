package br.com.frateu.blogcomentario.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.frateu.blogcomentario.database.DatabaseUsuario;
import br.com.frateu.blogcomentario.model.Usuarios;
import jakarta.servlet.http.HttpSession;


@Controller
public class RegistroController {
    @GetMapping("/registrar")
    public String getRegistrar() {
        return "registrar";
    }
    
    @PostMapping("/registrar")
    public String postRegistrar(
        @RequestParam String nomeUsuario,
        @RequestParam String emailUsuario,
        @RequestParam String senhaUsuario,
        @RequestParam String senhaConfirmarUsuario,
        HttpSession session) {
        
            String mensagemLogin = "";

        try {
            if (senhaUsuario.equals(senhaConfirmarUsuario)) {
                String senhaHasheada = BCrypt.hashpw(senhaUsuario, BCrypt.gensalt());

                Usuarios usuario = new Usuarios(nomeUsuario, emailUsuario, senhaHasheada);

                DatabaseUsuario dbUsuario = new DatabaseUsuario();

                dbUsuario.registrarUsuario(usuario);

                mensagemLogin = "Usuario registrado com sucesso!";
            } else {
                mensagemLogin = "As senhas não correspondem!";
            }
        } catch (Exception e) {
           mensagemLogin = "Erro no registro de conta!"; 
        }

        session.setAttribute("mensagemLogin", mensagemLogin);

        return "redirect:/";
    }
    
}
