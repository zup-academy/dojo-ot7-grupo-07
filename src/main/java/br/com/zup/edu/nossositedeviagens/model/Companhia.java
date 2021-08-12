package br.com.zup.edu.nossositedeviagens.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Companhia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    private Pais pais;

    @Column(nullable = false)
    private LocalDateTime instante;

    @PrePersist
    protected void onCreate() {
        instante = LocalDateTime.now();;
    }

    @Deprecated
    public Companhia() {
    }

    public Companhia(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }
}
