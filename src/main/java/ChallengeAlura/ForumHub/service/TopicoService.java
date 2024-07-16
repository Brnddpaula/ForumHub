package ChallengeAlura.ForumHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ChallengeAlura.ForumHub.repository.TopicoRepository;
import ChallengeAlura.ForumHub.model.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico criarTopico(Topico topico) {
        topico.setDataCriacao(LocalDateTime.now());
        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obterTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico atualizarTopico(Long id, Topico topicoAtualizado) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            Topico topico = topicoOpt.get();
            topico.setTitulo(topicoAtualizado.getTitulo());
            topico.setMensagem(topicoAtualizado.getMensagem());
            topico.setEstado(topicoAtualizado.getEstado());
            return topicoRepository.save(topico);
        }
        return null;
    }

    public void deletarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
