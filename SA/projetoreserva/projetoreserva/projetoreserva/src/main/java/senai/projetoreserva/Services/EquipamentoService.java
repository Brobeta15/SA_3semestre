package senai.projetoreserva.Services;

import org.springframework.stereotype.Service;
import senai.projetoreserva.Dtos.EquipamentoDto;
import senai.projetoreserva.Models.EquipamentoModel;
import senai.projetoreserva.Repositorys.EquipamentoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public List<EquipamentoDto> retornarLista(){

        List<EquipamentoModel> listaOP = repository.findAll();
        List<EquipamentoDto> lista = new ArrayList<>();

        for (EquipamentoModel equipamento : listaOP){

            EquipamentoDto dto = new EquipamentoDto();

            dto.setId(equipamento.getId());
            dto.setDescricao(equipamento.getDescricao());
            dto.setTipo(equipamento.getTipo());
            dto.setDiasDaSemana(equipamento.getDiasDaSemana());
            dto.setDataInicialAgendamento(equipamento.getDataInicialAgendamento());
            dto.setDataFinalAgendamento(equipamento.getDataFinalAgendamento());
            dto.setHoraInicialAgendamento(equipamento.getHoraInicialAgendamento());
            dto.setHoraFinalAgendamento(equipamento.getHoraFinalAgendamento());

            lista.add(dto);
        }
        return lista;
    }

    public String cadastrarEquipamento(EquipamentoDto dto){

        if(dto.getDataInicialAgendamento() != null && dto.getDataFinalAgendamento() != null) {

            if (validarDatasAgendamento(dto.getDataInicialAgendamento(), dto.getDataFinalAgendamento())) {

                EquipamentoModel model = new EquipamentoModel();

                model.setDescricao(dto.getDescricao());
                model.setTipo(dto.getTipo());
                model.setDiasDaSemana(dto.getDiasDaSemana());
                model.setDataInicialAgendamento(dto.getDataInicialAgendamento());
                model.setDataFinalAgendamento(dto.getDataFinalAgendamento());
                model.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
                model.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
                model.setHoraFinalAgendamento(dto.getHoraFinalAgendamento());

                repository.save(model);

                return "ok";
            }else{
                return "erro";
            }
        }else{

            EquipamentoModel model = new EquipamentoModel();

            model.setDescricao(dto.getDescricao());
            model.setTipo(dto.getTipo());
            model.setDiasDaSemana(dto.getDiasDaSemana());
            model.setDataInicialAgendamento(dto.getDataInicialAgendamento());
            model.setDataFinalAgendamento(dto.getDataFinalAgendamento());
            model.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
            model.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
            model.setHoraFinalAgendamento(dto.getHoraFinalAgendamento());

            repository.save(model);

            return "ok";
        }
    }

    public boolean validarDatasAgendamento(LocalDate dataIncial, LocalDate dataFinal){

        Boolean datasValidas;

        if (dataIncial.isBefore(dataFinal)){

            return datasValidas = true;
        }
        return datasValidas = false;
    }

    public EquipamentoDto retornarEquipamento(Long id){

        Optional<EquipamentoModel> equipamentoOP = repository.findById(id);
        EquipamentoDto dto = new EquipamentoDto();

        if (equipamentoOP.isPresent()){

            dto.setId(id);
            dto.setDescricao(equipamentoOP.get().getDescricao());
            dto.setTipo(equipamentoOP.get().getTipo());
            dto.setDiasDaSemana(equipamentoOP.get().getDiasDaSemana());
            dto.setDataInicialAgendamento(equipamentoOP.get().getDataInicialAgendamento());
            dto.setDataFinalAgendamento(equipamentoOP.get().getDataFinalAgendamento());
            dto.setHoraInicialAgendamento(equipamentoOP.get().getHoraInicialAgendamento());
            dto.setHoraFinalAgendamento(equipamentoOP.get().getHoraFinalAgendamento());

            return dto;
        }
        return dto;
    }

    public String atualizarEquipamento(Long id, EquipamentoDto dto){

        Optional<EquipamentoModel> equipamentoOP = repository.findById(id);

        if (equipamentoOP.isPresent()){
            EquipamentoModel model = new EquipamentoModel();

            model.setId(id);
            model.setDescricao(dto.getDescricao());
            model.setTipo(dto.getTipo());
            model.setDataInicialAgendamento(dto.getDataInicialAgendamento());
            model.setDataFinalAgendamento(dto.getDataFinalAgendamento());
            model.setDiasDaSemana(dto.getDiasDaSemana());
            model.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
            model.setHoraFinalAgendamento(dto.getHoraFinalAgendamento());

            repository.save(model);
            return "ok";
        }
        return "erro";
    }

    public String deletarEquipamento(Long id){

        Optional<EquipamentoModel> modelOP = repository.findById(id);

        if (modelOP.isPresent()){

            repository.delete(modelOP.get());
            return "ok";
        }
        return "erro";
    }
}
