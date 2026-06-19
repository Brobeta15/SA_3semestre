package senai.projetoreserva.Dtos;

import org.springframework.format.annotation.DateTimeFormat;
import senai.projetoreserva.Enums.DiasDaSemana;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class EquipamentoDto {

    private Long id;

    private String descricao;

    private String tipo;

    private List<DiasDaSemana> diasDaSemana;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicialAgendamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinalAgendamento;

    private LocalTime horaInicialAgendamento;

    private LocalTime horaFinalAgendamento;

    public EquipamentoDto() {
    }

    public EquipamentoDto(Long id, String descricao, String tipo, List<DiasDaSemana> diasDaSemana, LocalDate dataInicialAgendamento,
                          LocalDate dataFinalAgendamento, LocalTime horaInicialAgendamento, LocalTime horaFinalAgendamento) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.diasDaSemana = diasDaSemana;
        this.dataInicialAgendamento = dataInicialAgendamento;
        this.dataFinalAgendamento = dataFinalAgendamento;
        this.horaInicialAgendamento = horaInicialAgendamento;
        this.horaFinalAgendamento = horaFinalAgendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<DiasDaSemana> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(List<DiasDaSemana> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public LocalDate getDataInicialAgendamento() {
        return dataInicialAgendamento;
    }

    public void setDataInicialAgendamento(LocalDate dataInicialAgendamento) {
        this.dataInicialAgendamento = dataInicialAgendamento;
    }

    public LocalDate getDataFinalAgendamento() {
        return dataFinalAgendamento;
    }

    public void setDataFinalAgendamento(LocalDate dataFinalAgendamento) {
        this.dataFinalAgendamento = dataFinalAgendamento;
    }

    public LocalTime getHoraInicialAgendamento() {
        return horaInicialAgendamento;
    }

    public void setHoraInicialAgendamento(LocalTime horaInicialAgendamento) {
        this.horaInicialAgendamento = horaInicialAgendamento;
    }

    public LocalTime getHoraFinalAgendamento() {
        return horaFinalAgendamento;
    }

    public void setHoraFinalAgendamento(LocalTime horaFinalAgendamento) {
        this.horaFinalAgendamento = horaFinalAgendamento;
    }
}
