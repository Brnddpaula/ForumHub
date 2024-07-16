package ChallengeAlura.ForumHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ChallengeAlura.ForumHub.service.TopicoService;
import ChallengeAlura.ForumHub.model.Topico;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> criarTopico(@RequestBody Topico topico) {
        Topico novoTopico = topicoService.criarTopico(topico);
        return ResponseEntity.ok(novoTopico);
    }

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.listarTopicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obterTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obterTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody Topico topicoAtualizado) {
        Topico topico = topicoService.atualizarTopico(id, topicoAtualizado);
        return topico != null ? ResponseEntity.ok(topico) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
