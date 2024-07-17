package ChallengeAlura.ForumHub.controller;

import ChallengeAlura.ForumHub.dto.TopicoRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ChallengeAlura.ForumHub.service.TopicoService;
import ChallengeAlura.ForumHub.model.Topico;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid TopicoRequestDTO topicoDTO) {
        Topico novoTopico = topicoService.criarTopico(topicoDTO);
        return ResponseEntity.ok(novoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        Page<Topico> topicos = topicoService.listarTopicos(curso, ano, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obterTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obterTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoRequestDTO topicoAtualizadoDTO) {
        Topico topicoAtualizado = topicoService.atualizarTopico(id, topicoAtualizadoDTO);
        return topicoAtualizado != null ? ResponseEntity.ok(topicoAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        boolean deletado = topicoService.deletarTopico(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
