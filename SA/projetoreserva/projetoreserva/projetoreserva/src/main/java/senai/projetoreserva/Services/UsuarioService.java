package senai.projetoreserva.Services;

import org.springframework.stereotype.Service;
import senai.projetoreserva.Dtos.LoginDto;
import senai.projetoreserva.Dtos.LoginSessaoDto;
import senai.projetoreserva.Dtos.UsuarioDto;
import senai.projetoreserva.Models.UsuarioModel;
import senai.projetoreserva.Repositorys.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioDto> listaUsuarios(){

        List<UsuarioModel> listaOP = repository.findAll();

        List<UsuarioDto> listaDto = new ArrayList<>();

        for (UsuarioModel lista : listaOP){

            UsuarioDto usuario = new UsuarioDto();

            usuario.setEmail(lista.getEmail());
            usuario.setId(lista.getId());
            usuario.setNome(lista.getNome());
            usuario.setMatricula(lista.getMatricula());
            usuario.setDataDeNascimento(lista.getDataDeNascimento());
            usuario.setSenha(lista.getSenha());

            listaDto.add(usuario);
        }

        return listaDto;
    }

    public UsuarioDto buscarUsuario(Long id){

        Optional<UsuarioModel> usuarioOP = repository.findById(id);

        UsuarioDto usuario = new UsuarioDto();

        if (usuarioOP.isPresent()){

            usuario.setSenha(usuarioOP.get().getSenha());
            usuario.setId(usuarioOP.get().getId());
            usuario.setNome(usuarioOP.get().getNome());
            usuario.setEmail(usuarioOP.get().getEmail());
            usuario.setMatricula(usuarioOP.get().getMatricula());
            usuario.setDataDeNascimento(usuarioOP.get().getDataDeNascimento());

            return usuario;
        }
        return usuario;
    }

    public String CadastrarUsuario(UsuarioDto dto){

        Optional<UsuarioModel> usuarioOP = repository.findByEmail(dto.getEmail());

        if (usuarioOP.isPresent()){

            return "erro: email já registrado";

        } else if (verificarData(dto.getDataDeNascimento())) {

            return "erro: data de nascimento Inválida";
        }

        UsuarioModel model = new UsuarioModel();

        model.setEmail(dto.getEmail());
        model.setSenha(dto.getSenha());
        model.setNome(dto.getNome());
        model.setDataDeNascimento(dto.getDataDeNascimento());
        model.setMatricula(dto.getMatricula());
        repository.save(model);

        return "ok";
    }

    public Boolean verificarData(LocalDate dataNasc){

        Boolean dataInvalido = false;

        LocalDate dataAtual = LocalDate.now();

        LocalDate dataLimite = dataAtual.minusYears(500);

        if (dataNasc.isAfter(dataAtual) || dataNasc.isBefore(dataLimite)){

            dataInvalido = true;
            return dataInvalido;
        }
        return dataInvalido;
    }

    public String deletarUsuario(Long id){

        Optional<UsuarioModel> usuarioOP = repository.findById(id);

        if (usuarioOP.isPresent()){

            repository.delete(usuarioOP.get());
            return "ok";
        }
        return "usuário não encontrado";
    }

    public String atualizarUsuario(UsuarioDto dto, Long id){

        Optional<UsuarioModel> usuarioOP = repository.findByEmail(dto.getEmail());

        if (usuarioOP.isPresent() && usuarioOP.get().getId().equals(dto.getId()) || usuarioOP.isEmpty()){

            UsuarioModel model = new UsuarioModel();

            model.setId((dto.getId()));
            model.setMatricula(dto.getMatricula());
            model.setNome(dto.getNome());
            model.setSenha(dto.getSenha());
            model.setEmail(dto.getEmail());
            model.setDataDeNascimento(dto.getDataDeNascimento());

            repository.save(model);

            return "ok";
        }

        return "erro";
    }


    public LoginSessaoDto verificarLogin(LoginDto dto){

        LoginSessaoDto sessao = new LoginSessaoDto();

        Optional<UsuarioModel> usuarioOP = repository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());

        if (usuarioOP.isPresent()){

            sessao.setId(usuarioOP.get().getId());
            sessao.setNome(usuarioOP.get().getNome());
            return sessao;
        }
        return sessao;
    }
}
