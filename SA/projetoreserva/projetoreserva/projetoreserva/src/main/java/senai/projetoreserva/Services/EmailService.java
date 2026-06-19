package senai.projetoreserva.Services;

import jakarta.validation.constraints.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import senai.projetoreserva.Dtos.EmailCadastroNovoDto;
import senai.projetoreserva.Dtos.EmailCancelamentoDto;
import senai.projetoreserva.Dtos.ReservaListaDto;
import senai.projetoreserva.Dtos.UsuarioDto;
import senai.projetoreserva.Models.ReservaModel;
import senai.projetoreserva.Models.UsuarioModel;
import senai.projetoreserva.Repositorys.ReservaRepository;
import senai.projetoreserva.Repositorys.UsuarioRepository;

import java.util.Optional;

@Service
public class EmailService {

    private JavaMailSender mailSender;
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;

    public EmailService(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, JavaMailSender mailSender) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mailSender = mailSender;
    }

    public void enviarEmail(UsuarioDto dto){

        EmailCadastroNovoDto email = new EmailCadastroNovoDto(dto.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getDestinatario());
        message.setTo(email.getDestinatario());
        message.setSubject(email.getAssunto());
        message.setText(email.getMensagem());
        mailSender.send(message);
    }

    public void envioEmailCancelamento(Long idReserva){

        Optional<ReservaModel> reservaModel = reservaRepository.findById(idReserva);

        if (reservaModel.isPresent()){

            EmailCancelamentoDto email = new EmailCancelamentoDto(reservaModel.get().getColaborador().getEmail());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getDestinatario());
            message.setTo(email.getDestinatario());
            message.setSubject(email.getAssunto());
            message.setText(email.getMensagem());
            mailSender.send(message);
        }
    }
}
