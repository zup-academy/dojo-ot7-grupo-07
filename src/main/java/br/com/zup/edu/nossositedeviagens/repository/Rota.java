package br.com.zup.edu.nossositedeviagens.repository;

import br.com.zup.edu.nossositedeviagens.model.Aeroporto;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Entity
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany
    private Aeroporto saida;

    @OneToMany
    private Aeroporto destino;

    @NotNull
    private Duration duracao;

    public Rota(String nome, Aeroporto saida, Aeroporto destino, Duration duracao) {
        this.nome = nome;
        this.saida = saida;
        this.destino = destino;
        this.duracao = duracao;
    }

    public Object getId() {
        return id;
    }
}
