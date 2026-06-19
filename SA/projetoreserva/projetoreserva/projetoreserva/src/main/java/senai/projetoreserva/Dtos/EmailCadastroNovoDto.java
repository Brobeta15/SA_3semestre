package senai.projetoreserva.Dtos;

import jakarta.validation.constraints.Email;

public class EmailCadastroNovoDto {

    @Email
    private String remetende = "meutestesistema@gmail.com";
    @Email
    private String destinatario;

    private String assunto = "Pronto para começar?";

    private String mensagem = "Olá, tudo bem?\n" +
            "\n" +
            "Seja bem-vindo ao nosso sistema! Estamos felizes em ter você conosco.\n" +
            "\n" +
            "Nosso site permite que você gerencie os recursos disponíveis de maneira simples e rápida." +
            "\n" +
            "O objetivo do sistema é facilitar o controle, melhorar a organização e garantir que todos possam utilizar os equipamentos no momento ideal.\n" +
            "\n" +
            "Caso precise de ajuda ou tenha sugestões, estamos à disposição.\n" +
            "Esperamos que sua experiência seja produtiva e intuitiva!\n" +
            "\n" +
            "Atenciosamente,\n" +
            "Equipe de Suporte";

    public String getRemetende() {
        return remetende;
    }

    public EmailCadastroNovoDto(String destinatario) {
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
