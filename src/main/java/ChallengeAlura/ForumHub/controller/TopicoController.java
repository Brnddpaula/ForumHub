package ChallengeAlura.ForumHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import ChallengeAlura.ForumHub.service.TopicoService;
import ChallengeAlura.ForumHub.dto.TopicoRequestDTO;
import ChallengeAlura.ForumHub.model.Topico;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Endpoint para criar um novo tópico
    @PostMapping
    @Transactional
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid TopicoRequestDTO topicoDTO) {
        Topico novoTopico = topicoService.criarTopico(topicoDTO);
        return ResponseEntity.ok(novoTopico);
    }

    // Endpoint para listar todos os tópicos
    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.listarTopicos();
    }

    // Endpoint para obter um tópico específico
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obterTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obterTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoRequestDTO topicoAtualizadoDTO) {
        Topico topicoAtualizado = topicoService.atualizarTopico(id, topicoAtualizadoDTO);
        return topicoAtualizado != null ? ResponseEntity.ok(topicoAtualizado) : ResponseEntity.notFound().build();
    }

    // Endpoint para deletar um tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        boolean deletado = topicoService.deletarTopico(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
