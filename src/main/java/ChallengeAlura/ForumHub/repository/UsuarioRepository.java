package ChallengeAlura.ForumHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ChallengeAlura.ForumHub.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
