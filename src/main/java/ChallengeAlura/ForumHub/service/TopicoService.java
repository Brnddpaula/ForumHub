package ChallengeAlura.ForumHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ChallengeAlura.ForumHub.repository.TopicoRepository;
import ChallengeAlura.ForumHub.model.Topico;
import ChallengeAlura.ForumHub.model.Usuario;
import ChallengeAlura.ForumHub.model.Curso;
import ChallengeAlura.ForumHub.model.EstadoTopico;
import ChallengeAlura.ForumHub.dto.TopicoRequestDTO;
import ChallengeAlura.ForumHub.repository.UsuarioRepository;
import ChallengeAlura.ForumHub.repository.CursoRepository;

import java.time.LocalDateTime;
import java.util.List;
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
        // Verificar se já existe um tópico com o mesmo título e mensagem
        Optional<Topico> existente = topicoRepository.findByTituloAndMensagem(topicoDTO.getTitulo(), topicoDTO.getMensagem());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Tópico duplicado: título e mensagem já existem.");
        }

        // Obter autor e curso
        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        // Criar e salvar o tópico
        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensagem(topicoDTO.getMensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setEstado(EstadoTopico.ABERTO);
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obterTopicoPorId(Long id) {
        return topicoRepository.findById(id);
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
