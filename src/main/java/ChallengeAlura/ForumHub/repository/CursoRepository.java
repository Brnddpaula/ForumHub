package ChallengeAlura.ForumHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ChallengeAlura.ForumHub.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
