package br.com.zup.edu.nossositedeviagens.controller;

import br.com.zup.edu.nossositedeviagens.dto.request.AeroportoRequest;
import br.com.zup.edu.nossositedeviagens.model.Aeroporto;
import br.com.zup.edu.nossositedeviagens.repository.AeroportoRepository;
import br.com.zup.edu.nossositedeviagens.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/aeroportos")
public class AeroportoController {

    private AeroportoRepository aeroportoRepository;
    private PaisRepository paisRepository;

    public AeroportoController(AeroportoRepository aeroportoRepository, PaisRepository paisRepository) {
        this.aeroportoRepository = aeroportoRepository;
        this.paisRepository = paisRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AeroportoRequest request, UriComponentsBuilder uriBuilder){
        Aeroporto aeroporto = request.toModel(paisRepository);

        aeroportoRepository.save(aeroporto);

        URI uri = uriBuilder.path("/aeroportos/{id}").buildAndExpand(aeroporto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
