package senai.projetoreserva.Repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projetoreserva.Models.UsuarioModel;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmail(String email);
    Optional<UsuarioModel> findByEmailAndSenha(String email, String Senha);
}
