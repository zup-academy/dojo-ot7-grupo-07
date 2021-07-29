package br.com.zup.edu.nossositedeviagens.dto.request;

import br.com.zup.edu.nossositedeviagens.config.UnicoValor;
import br.com.zup.edu.nossositedeviagens.model.Companhia;
import br.com.zup.edu.nossositedeviagens.model.Pais;
import br.com.zup.edu.nossositedeviagens.repository.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompanhiaRequest {
    @NotBlank
    private String nome;

    @NotNull
    @UnicoValor(target = Pais.class, field = "id")
    private Integer pais;

    @Deprecated
    public CompanhiaRequest() {
    }

    public String getNome() {
        return nome;
    }

    public Integer getPais() {
        return pais;
    }

    public Companhia toModel(PaisRepository paisRepository) {
        return new Companhia(this.nome, paisRepository.findById(this.pais).get());
    }
}
