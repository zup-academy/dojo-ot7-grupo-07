package br.com.zup.edu.nossositedeviagens.dto.request;

import br.com.zup.edu.nossositedeviagens.model.Aeroporto;
import br.com.zup.edu.nossositedeviagens.repository.AeroportoRepository;
import br.com.zup.edu.nossositedeviagens.model.Rota;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Duration;
import java.util.Optional;

public class RotasRequest {

    private String nome;
    @NotNull
    private Long idAeroportoSaida;
    @NotNull
    private Long idAeroportoDestino;
    @NotNull
    @Positive
    private Long duracao;

    public String getNome() {
        return nome;
    }

    public Long getIdAeroportoSaida() {
        return idAeroportoSaida;
    }

    public Long getIdAeroportoDestino() {
        return idAeroportoDestino;
    }

    public Long getDuracao() {
        return duracao;
    }

    public Rota converte(AeroportoRepository aeroportoRepository) {
        Optional<Aeroporto> possivelSaida = aeroportoRepository.findById(getIdAeroportoSaida());
        Optional<Aeroporto> possivelDestino = aeroportoRepository.findById(getIdAeroportoDestino());
        if(possivelDestino.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aeroporto de destino não encontrado");
        }
        if(possivelSaida.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aeroporto de saída não encontrado");
        }
        Aeroporto aeroportoDestino = possivelDestino.get();
        Aeroporto aeroportoSaida = possivelSaida.get();
        if(nome==null){
            nome = aeroportoSaida.getNome() + " " + aeroportoDestino.getNome();
        }
        return new Rota(nome, aeroportoSaida, aeroportoDestino, Duration.ofMinutes(duracao));
        }
}
