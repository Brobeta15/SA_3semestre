package senai.projetoreserva.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import senai.projetoreserva.Dtos.EquipamentoDto;
import senai.projetoreserva.Services.EquipamentoService;

import java.util.List;

@Controller
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @GetMapping("/equipamentolista")
    public String viewEquipamentoLista(Model model){

        List<EquipamentoDto>lista = service.retornarLista();

        model.addAttribute("listaDto", lista);

        return "equipamentolista";
    }

    @GetMapping("/equipamentocadastro")
    public String viewEquipamentoCadastro(Model model){

        model.addAttribute("EquipamentoDto", new EquipamentoDto());

        return "equipamentocadastro";
    }

    @PostMapping("/equipamentocadastro")
    public String cadastrarEquipamentoCadastro(@ModelAttribute("EquipamentoDto")EquipamentoDto dto){

        String resposta = service.cadastrarEquipamento(dto);

        if (resposta.equals("ok")){

            return "redirect:/equipamentolista";
        }else{

            return "redirect:/equipamentolista?erro";
        }
    }
    @GetMapping("/equipamentoatualizar/{id}")
    public String viewEquipamentoAtualizar(Model model, @PathVariable Long id){

        EquipamentoDto dto = service.retornarEquipamento(id);

        model.addAttribute("equipamentoDto", dto);

        return "equipamentoatualizar";
    }

    @PostMapping("/equipamentoatualizar/{id}")
    public String atualizarEquipamento(@ModelAttribute("equipamentoDto")EquipamentoDto dto, @PathVariable Long id){

        System.out.println(id);

        String resposta = service.atualizarEquipamento(id,dto);

        if (resposta.equals("ok")){
            return "redirect:/equipamentolista";
        }else {
            return "redirect:/equipamentolista?erro";
        }
    }
    @DeleteMapping("/equipamento/{id}")
    public ResponseEntity<String> deletarEquipamento(@PathVariable Long id){

        String resposta = service.deletarEquipamento(id);

        if (resposta.equals("ok")){

            return ResponseEntity.ok().body(resposta);
        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
