package senai.projetoreserva.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import senai.projetoreserva.Dtos.LoginDto;
import senai.projetoreserva.Dtos.LoginSessaoDto;
import senai.projetoreserva.Dtos.UsuarioDto;
import senai.projetoreserva.Services.EmailService;
import senai.projetoreserva.Services.UsuarioService;
import senai.projetoreserva.Sessao.ControleSessao;

@Controller
public class LoginController {

    private final UsuarioService service;
    private final EmailService emailService;

    public LoginController(UsuarioService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String viewLogin(Model model){

        model.addAttribute("LoginDto", new LoginDto());

        return "login";
    }

    @PostMapping("/login")
    public String verificarLogin(@ModelAttribute("LoginDto") LoginDto dto, HttpServletRequest request){

        LoginSessaoDto resposta = service.verificarLogin(dto);

        if (resposta.getId()!=0L){

            ControleSessao.registrar(request, resposta);
            return "redirect:/home";
        }else{
            return "redirect:/login?erro";
        }
    }

    @GetMapping("cadastrarusuario")
    public String viewCadastrarUsuario(Model model){

        model.addAttribute("UsuarioDto", new UsuarioDto());

        return "cadastrarusuario";
    }

    @PostMapping("cadastrarusuario")
    public String cadastrarUsuarioLogin(@ModelAttribute("UsuarioDto") UsuarioDto dto){

        String resposta = service.CadastrarUsuario(dto);

        if(resposta.equals("ok")){
            emailService.enviarEmail(dto);
            return "redirect:/login";
        }
        return "redirect:/login?erro";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        ControleSessao.encerrar(request);
        return "redirect:/login";
    }

}
