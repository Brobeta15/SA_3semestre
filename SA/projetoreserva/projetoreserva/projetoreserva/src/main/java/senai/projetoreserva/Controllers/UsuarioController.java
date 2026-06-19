package senai.projetoreserva.Controllers;

import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import senai.projetoreserva.Dtos.EmailCadastroNovoDto;
import senai.projetoreserva.Dtos.UsuarioDto;
import senai.projetoreserva.Services.EmailService;
import senai.projetoreserva.Services.UsuarioService;

import java.util.List;

@Controller
public class UsuarioController {

    private final UsuarioService service;
    private final EmailService emailService;

    public UsuarioController(UsuarioService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @GetMapping("/usuariolista")
    public String viewUsuarioLista(Model model){

        List<UsuarioDto> lista = service.listaUsuarios();

        model.addAttribute("listaDto",lista);

        return "usuariolista";
    }

    @GetMapping("/usuariocadastro")
    public String viewUsuarioCadastro(Model model) {

        model.addAttribute("UsuarioDto", new UsuarioDto());

        return "usuariocadastro";
    }

    @PostMapping("/usuariocadastro")
    public String cadastrarUsuario(@ModelAttribute("UsuarioDto") UsuarioDto dto){

        String resposta = service.CadastrarUsuario(dto);

        if (resposta.equals("ok")){

            emailService.enviarEmail(dto);
            return "redirect:/usuariolista";
        }else{
            return "redirect:/usuariolista?erro";
        }
    }

    @GetMapping("/usuarioatualizar/{id}")
    public String viewAtualizarUsuario(@PathVariable Long id, Model model){

        UsuarioDto dto = service.buscarUsuario(id);
        model.addAttribute("usuarioDto", dto);

        return "usuarioatualizar";
    }

    @PostMapping("/usuarioatualizar/{id}")
    public String atualizarUsuario(@ModelAttribute("usuarioDto") UsuarioDto dto, @PathVariable Long id){

        String resposta = service.atualizarUsuario(dto, id);

        if (resposta.equals("ok")){

            return "redirect:/usuariolista";
        }else{
            return "redirect:/usuariolista?erro";
        }
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id){

        String resposta = service.deletarUsuario(id);

        if (resposta.equals("ok")){
            return ResponseEntity.ok().body(resposta);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
