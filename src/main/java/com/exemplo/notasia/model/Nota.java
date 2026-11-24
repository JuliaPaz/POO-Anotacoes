package com.exemplo.notasia.model;

import jakarta.persistence.*;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 10000)
    private String conteudo;

    @Column(length = 8000)
    private String resumo;

    @Column(length = 8000)
    private String perguntas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public String getPerguntas() { return perguntas; }
    public void setPerguntas(String perguntas) { this.perguntas = perguntas; }
}
