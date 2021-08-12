package br.com.zup.edu.nossositedeviagens.dto.request;

import br.com.zup.edu.nossositedeviagens.config.UnicoValor;
import br.com.zup.edu.nossositedeviagens.model.Aeroporto;
import br.com.zup.edu.nossositedeviagens.repository.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AeroportoRequest {

    @NotBlank
    @UnicoValor(
            target = Aeroporto.class,
            field = "nome",
            message = "Este aeroporto já está cadastrado."
    )
    private String nome;

    @NotNull
    private Long pais;

    public String getNome() {
        return nome;
    }

    public Long getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "AeroportoRequest{" +
                "nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }

    public Aeroporto toModel(PaisRepository paisRepository) {
        paisRepository.findById();
        return new Aeroporto(nome,)
    }
}
