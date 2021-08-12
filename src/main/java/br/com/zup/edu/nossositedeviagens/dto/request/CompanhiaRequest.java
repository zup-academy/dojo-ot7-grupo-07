package br.com.zup.edu.nossositedeviagens.dto.request;

import br.com.zup.edu.nossositedeviagens.config.UnicoValor;
import br.com.zup.edu.nossositedeviagens.model.Companhia;
import br.com.zup.edu.nossositedeviagens.model.Pais;
import br.com.zup.edu.nossositedeviagens.repository.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CompanhiaRequest {
    @NotBlank
    @UnicoValor(
            target = Companhia.class,
            field = "nome",
            message = "Companhia já existe."
    )
    private String nome;

    @NotNull
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
        Optional<Pais> pais = paisRepository.findById(this.pais);
        return new Companhia(this.nome, pais.get() );
    }
}
