package ChallengeAlura.ForumHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ChallengeAlura.ForumHub.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
