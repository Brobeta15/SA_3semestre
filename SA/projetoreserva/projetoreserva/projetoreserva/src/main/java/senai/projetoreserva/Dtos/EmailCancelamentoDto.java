package senai.projetoreserva.Dtos;

import jakarta.validation.constraints.Email;

public class EmailCancelamentoDto {

    @Email
    private String remetende = "meutestesistema@gmail.com";
    @Email
    private String destinatario;

    private String assunto = "Cancelamento confirmado";

    private String mensagem = "Olá,\n" +
            "\n" +
            "Informamos que a sua reserva foi cancelada com sucesso.\n" +
            "\n" +
            "Caso tenha cancelado por engano ou precise fazer uma nova reserva, fique à vontade para acessar o sistema e realizar o agendamento novamente.\n" +
            "\n" +
            "Se precisar de ajuda ou tiver qualquer dúvida, estamos à disposição.\n" +
            "\n" +
            "Atenciosamente,\n" +
            "Equipe de Suporte";

    public String getRemetende() {
        return remetende;
    }

    public EmailCancelamentoDto(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setRemetende(String remetende) {
        this.remetende = remetende;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
