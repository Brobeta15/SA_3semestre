package senai.projetoreserva.Services;

import org.springframework.stereotype.Service;
import senai.projetoreserva.Dtos.ReservaDto;
import senai.projetoreserva.Dtos.ReservaListaDto;
import senai.projetoreserva.Enums.DiasDaSemana;
import senai.projetoreserva.Models.EquipamentoModel;
import senai.projetoreserva.Models.ReservaModel;
import senai.projetoreserva.Models.UsuarioModel;
import senai.projetoreserva.Repositorys.EquipamentoRepository;
import senai.projetoreserva.Repositorys.ReservaRepository;
import senai.projetoreserva.Repositorys.UsuarioRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final EquipamentoRepository repositoryEquipamento;
    private final UsuarioRepository repositoryUsuario;

    public ReservaService(ReservaRepository repository, EquipamentoRepository repositoryEquipamento, UsuarioRepository repositoryUsuario) {
        this.repository = repository;
        this.repositoryEquipamento = repositoryEquipamento;
        this.repositoryUsuario = repositoryUsuario;
    }

    public List<ReservaListaDto> listaReservas(){

        List<ReservaModel> listaOP = repository.findAll();
        List<ReservaListaDto> listaDto = new ArrayList<>();

        for (ReservaModel lista : listaOP){

            ReservaListaDto dto = new ReservaListaDto();

            dto.setId(lista.getId());
            dto.setColaborador(lista.getColaborador().getNome());
            dto.setEquipamento(lista.getEquipamento().getDescricao());
            dto.setData(lista.getData());
            dto.setHoraInicial(lista.getHoraInicial());
            dto.setHoraFinal(lista.getHoraFinal());
            dto.setCancelamento(lista.getCancelamento());
            dto.setObservacao(lista.getObservacao());

            listaDto.add(dto);
        }
        return listaDto;
    }

    public String cadastrarReserva(ReservaDto dto){

        Optional<UsuarioModel> usuarioOP = repositoryUsuario.findById(dto.getColaborador());
        Optional<EquipamentoModel> equipamentoOP = repositoryEquipamento.findById(dto.getEquipamento());
        List<DiasDaSemana> semanaOP = equipamentoOP.get().getDiasDaSemana();

        if (validarDiaDaSemana(dto.getData(), semanaOP)) {

            if (dto.getData().isAfter(equipamentoOP.get().getDataInicialAgendamento()) || dto.getData().isEqual(equipamentoOP.get().getDataInicialAgendamento())) {

                if (dto.getData().isBefore(equipamentoOP.get().getDataFinalAgendamento()) || dto.getData().isEqual(equipamentoOP.get().getDataFinalAgendamento())) {

                    if (dto.getHoraInicial().isAfter(equipamentoOP.get().getHoraInicialAgendamento()) || dto.getHoraInicial().equals(equipamentoOP.get().getHoraInicialAgendamento())
                            && dto.getHoraInicial().isBefore(equipamentoOP.get().getHoraFinalAgendamento())) {

                        if (dto.getHoraFinal().isAfter(equipamentoOP.get().getHoraInicialAgendamento())
                                && dto.getHoraFinal().isBefore(equipamentoOP.get().getHoraFinalAgendamento()) || dto.getHoraFinal().equals(equipamentoOP.get().getHoraFinalAgendamento())) {


                            ReservaModel model = new ReservaModel();

                            model.setColaborador(usuarioOP.get());
                            model.setEquipamento(equipamentoOP.get());
                            model.setData(dto.getData());
                            model.setHoraInicial(dto.getHoraInicial());
                            model.setHoraFinal(dto.getHoraFinal());

                            repository.save(model);
                            return "ok";
                        }
                    }
                }
            }
        }
        return "erro";
    }

    public ReservaListaDto buscarReserva(Long id){

        Optional<ReservaModel> modelOP = repository.findById(id);
        ReservaListaDto dto = new ReservaListaDto();

        if (modelOP.isPresent()){

            dto.setId(id);
            dto.setColaborador(modelOP.get().getColaborador().getNome());
            dto.setEquipamento(modelOP.get().getEquipamento().getDescricao());
            dto.setData(modelOP.get().getData());
            dto.setHoraInicial(modelOP.get().getHoraInicial());
            dto.setHoraFinal(modelOP.get().getHoraFinal());
            dto.setCancelamento(LocalDate.now());
            dto.setObservacao(modelOP.get().getObservacao());
        }
        return dto;
    }

    public String cancelarResercva(ReservaListaDto dto, Long id){

        Optional<ReservaModel> modelOP = repository.findById(id);
        Optional<UsuarioModel> usuarioOP = repositoryUsuario.findById(modelOP.get().getColaborador().getId());
        Optional<EquipamentoModel> equipamentoOP = repositoryEquipamento.findById(modelOP.get().getEquipamento().getId());

        if (dataCancelamentoValida(modelOP.get().getData())){
            if (modelOP.get().getObservacao()==null){

            ReservaModel model = modelOP.get();

            model.setId(id);
            model.setColaborador(usuarioOP.get());
            model.setEquipamento(equipamentoOP.get());
            model.setData(modelOP.get().getData());
            model.setHoraFinal(dto.getHoraFinal());
            model.setCancelamento(LocalDate.now());
            model.setObservacao(dto.getObservacao());

            repository.save(model);

            return "ok";
            }
        }
        return "erro";
    }

    public Boolean dataCancelamentoValida(LocalDate dataReserva){

        LocalDate dataAtual = LocalDate.now();

        if (dataAtual.isAfter(dataReserva) || dataAtual.equals(dataReserva)){

            return false;
        }
        return true;
    }

    public Boolean validarDiaDaSemana(LocalDate data, List<DiasDaSemana> semanaOP){

        DayOfWeek diaSemanaSelecionado = data.getDayOfWeek();

        if (semanaOP.isEmpty()){

            return true;
        }

        DiasDaSemana dia = switch (diaSemanaSelecionado){
            case MONDAY -> DiasDaSemana.SEGUNDA_FEIRA;
            case TUESDAY -> DiasDaSemana.TERCA_FEIRA;
            case WEDNESDAY -> DiasDaSemana.QUARTA_FEIRA;
            case THURSDAY -> DiasDaSemana.QUINTA_FEIRA;
            case FRIDAY -> DiasDaSemana.SEXTA_FEIRA;
            case SATURDAY -> DiasDaSemana.SABADO;
            case SUNDAY -> DiasDaSemana.DOMINGO;
        };

        for (int i = 0; i < semanaOP.size(); i++) {

            if (dia.equals(semanaOP.get(i))){

                return true;
            }
        }
        return false;
    }
}
