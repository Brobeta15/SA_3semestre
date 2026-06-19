package senai.projetoreserva.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import senai.projetoreserva.Dtos.EquipamentoDto;
import senai.projetoreserva.Dtos.ReservaDto;
import senai.projetoreserva.Dtos.ReservaListaDto;
import senai.projetoreserva.Dtos.UsuarioDto;
import senai.projetoreserva.Services.EmailService;
import senai.projetoreserva.Services.EquipamentoService;
import senai.projetoreserva.Services.ReservaService;
import senai.projetoreserva.Services.UsuarioService;

import java.util.List;

@Controller
public class ReservaController {

    private final ReservaService service;
    private final UsuarioService serviceUsuario;
    private final EquipamentoService serviceEquipamento;
    private final EmailService serviceEmail;

    public ReservaController(ReservaService service, UsuarioService serviceUsuario, EquipamentoService serviceEquipamento,EmailService serviceEmail) {
        this.service = service;
        this.serviceUsuario = serviceUsuario;
        this.serviceEquipamento = serviceEquipamento;
        this.serviceEmail = serviceEmail;
    }

    @GetMapping("/reservalista")
    public String viewLista(Model model){

        List<ReservaListaDto> lista = service.listaReservas();

        model.addAttribute("listaDto", lista);

        return "reservalista";
    }

    @GetMapping("/reservacadastro")
    public String viewCadastro(Model model){

        List<UsuarioDto> usuario = serviceUsuario.listaUsuarios();
        List<EquipamentoDto> equipamento = serviceEquipamento.retornarLista();

        model.addAttribute("ReservaDto", new ReservaDto());
        model.addAttribute("usuarios", usuario);
        model.addAttribute("equipamentos", equipamento);

        return "reservacadastro";
    }

    @PostMapping("/reservacadastro")
    public String cadastrar(@ModelAttribute("ReservaDto") ReservaDto dto){

        String resposta = service.cadastrarReserva(dto);

        if (resposta.equals("ok")){

            return "redirect:/reservalista";
        }else {
            return "redirect:/reservalista?erro";
        }
    }

    @GetMapping("/reservacancelamento/{id}")
    public String viewCancelamento(Model model, @PathVariable Long id){

        ReservaListaDto dto = service.buscarReserva(id);
        model.addAttribute("ReservaDto", dto);

        return "reservacancelamento";
    }

    @PostMapping("/reservacancelamento/{id}")
    public String cancelarReserva(@ModelAttribute("ReservaDto") ReservaListaDto dto, @PathVariable Long id){

        String resposta = service.cancelarResercva(dto,id);

        if (resposta.equals("ok")){

            serviceEmail.envioEmailCancelamento(id);
            return "redirect:/reservalista";
        }else {

            return "redirect:/reservalista?erro";
        }
    }

    @GetMapping("/reservavisualizar/{id}")
    public String viewVisualizarReserva(Model model, @PathVariable Long id){

        ReservaListaDto reserva = service.buscarReserva(id);

        model.addAttribute("ReservaDto", reserva );

        return "reservavisualizar";
    }

}
