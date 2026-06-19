package senai.projetoreserva.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import senai.projetoreserva.Models.EquipamentoModel;
import senai.projetoreserva.Models.UsuarioModel;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDto {

    private Long id;

    private Long colaborador;

    private Long equipamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private LocalTime horaInicial;

    private LocalTime horaFinal;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelamento;

    private String observacao;

    public ReservaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getColaborador() {
        return colaborador;
    }

    public void setColaborador(Long colaborador) {
        this.colaborador = colaborador;
    }

    public Long getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Long equipamento) {
        this.equipamento = equipamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getCancelamento() {
        return cancelamento;
    }

    public void setCancelamento(LocalDate cancelamento) {
        this.cancelamento = cancelamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
