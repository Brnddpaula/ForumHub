package ChallengeAlura.ForumHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ChallengeAlura.ForumHub.repository.TopicoRepository;
import ChallengeAlura.ForumHub.model.Topico;
import ChallengeAlura.ForumHub.model.Usuario;
import ChallengeAlura.ForumHub.model.Curso;
import ChallengeAlura.ForumHub.model.EstadoTopico;
import ChallengeAlura.ForumHub.dto.TopicoRequestDTO;
import ChallengeAlura.ForumHub.dto.TopicoResponseDTO;
import ChallengeAlura.ForumHub.repository.UsuarioRepository;
import ChallengeAlura.ForumHub.repository.CursoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico criarTopico(TopicoRequestDTO topicoDTO) {
        Optional<Topico> existente = topicoRepository.findByTituloAndMensagem(topicoDTO.getTitulo(), topicoDTO.getMensagem());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Tópico duplicado: título e mensagem já existem.");
        }

        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensagem(topicoDTO.getMensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setEstado(EstadoTopico.ABERTO);
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    public Page<Topico> listarTopicos(String curso, Integer ano, Pageable pageable) {
        if (curso != null && ano != null) {
            return topicoRepository.findByCursoNomeAndDataCriacaoYear(curso, ano, pageable);
        } else if (curso != null) {
            return topicoRepository.findByCursoNome(curso, pageable);
        } else if (ano != null) {
            return topicoRepository.findByDataCriacaoYear(ano, pageable);
        } else {
            return topicoRepository.findAll(pageable);
        }
    }

    public Optional<TopicoResponseDTO> obterTopicoPorId(Long id) {
        return topicoRepository.findById(id).map(TopicoResponseDTO::new);
    }

    public Topico atualizarTopico(Long id, TopicoRequestDTO topicoAtualizadoDTO) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            Topico topico = topicoOpt.get();
            topico.setTitulo(topicoAtualizadoDTO.getTitulo());
            topico.setMensagem(topicoAtualizadoDTO.getMensagem());
            topico.setEstado(EstadoTopico.ABERTO);
            return topicoRepository.save(topico);
        }
        return null;
    }

    public boolean deletarTopico(Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
