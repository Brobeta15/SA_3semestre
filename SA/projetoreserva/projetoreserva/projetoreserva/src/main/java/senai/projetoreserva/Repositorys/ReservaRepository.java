package senai.projetoreserva.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projetoreserva.Models.ReservaModel;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
}
