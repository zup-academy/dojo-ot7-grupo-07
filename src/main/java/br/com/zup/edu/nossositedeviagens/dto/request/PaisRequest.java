package br.com.zup.edu.nossositedeviagens.dto.request;

import br.com.zup.edu.nossositedeviagens.model.Pais;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    private String nome;

    @Deprecated
    public PaisRequest() {
    }

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
