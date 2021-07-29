package br.com.zup.edu.nossositedeviagens.controller;

import br.com.zup.edu.nossositedeviagens.dto.request.CompanhiaRequest;
import br.com.zup.edu.nossositedeviagens.model.Companhia;
import br.com.zup.edu.nossositedeviagens.repository.CompanhiaRepository;
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
@RequestMapping("/companhias")
public class CompanhiaController {
    private CompanhiaRepository companhiaRepository;
    private PaisRepository paisRepository;

    public CompanhiaController(CompanhiaRepository companhiaRepository, PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
        this.companhiaRepository = companhiaRepository;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CompanhiaRequest request, UriComponentsBuilder uriBuilder) {
        Companhia companhia = request.toModel(paisRepository);

        companhiaRepository.save(companhia);

        URI uri = uriBuilder.path("/companhias/{id}").buildAndExpand(companhia.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
