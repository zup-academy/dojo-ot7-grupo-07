package br.com.zup.edu.nossositedeviagens.controller;

import br.com.zup.edu.nossositedeviagens.dto.request.PaisRequest;
import br.com.zup.edu.nossositedeviagens.model.Pais;
import br.com.zup.edu.nossositedeviagens.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisRepository repository;

    @PostMapping
    private ResponseEntity<?> salvar(@RequestBody @Valid PaisRequest request, UriComponentsBuilder uriBuilder){
        Pais pais = request.toModel();
        repository.save(pais);

        URI uri = uriBuilder.path("/pais/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
