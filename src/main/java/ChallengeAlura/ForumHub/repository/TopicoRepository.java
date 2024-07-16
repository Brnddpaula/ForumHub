package ChallengeAlura.ForumHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ChallengeAlura.ForumHub.model.Topico;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
}
