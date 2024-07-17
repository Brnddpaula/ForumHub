package ChallengeAlura.ForumHub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ChallengeAlura.ForumHub.model.Topico;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :curso AND YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByCursoNomeAndDataCriacaoYear(String curso, Integer ano, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :curso")
    Page<Topico> findByCursoNome(String curso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByDataCriacaoYear(Integer ano, Pageable pageable);
}
