package senai.projetoreserva.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.projetoreserva.Models.EquipamentoModel;

public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Long> {
}
