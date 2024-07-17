package ChallengeAlura.ForumHub.dto;

import ChallengeAlura.ForumHub.model.Topico;

import java.time.LocalDateTime;

public class TopicoResponseDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String estado;
    private String autor;
    private String curso;

    public TopicoResponseDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.estado = topico.getEstado().name();
        this.autor = topico.getAutor().getNome();
        this.curso = topico.getCurso().getNome();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getEstado() {
        return estado;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
