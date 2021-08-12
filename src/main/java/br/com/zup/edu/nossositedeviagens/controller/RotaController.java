package br.com.zup.edu.nossositedeviagens.controller;

import br.com.zup.edu.nossositedeviagens.config.ProibeAeroportoOrigemDestinoIguaisValidator;
import br.com.zup.edu.nossositedeviagens.config.ProibeRotaDuplicada;
import br.com.zup.edu.nossositedeviagens.dto.request.RotasRequest;
import br.com.zup.edu.nossositedeviagens.repository.AeroportoRepository;
import br.com.zup.edu.nossositedeviagens.model.Rota;
import br.com.zup.edu.nossositedeviagens.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rotas")
public class RotaController {
    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private AeroportoRepository aeroportoRepository;

    @Autowired
    private ProibeRotaDuplicada proibeRotaDuplicada;

    @Autowired
    private ProibeAeroportoOrigemDestinoIguaisValidator proibeAeroportoOrigemDestinoIguaisValidator;

    @PostMapping
    public ResponseEntity cadastraRotas(@RequestBody @Valid RotasRequest request ){
        Rota rota = request.converte(aeroportoRepository);
        rotaRepository.save(rota);
        return new ResponseEntity<>(rota.getId(), HttpStatus.CREATED);
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeAeroportoOrigemDestinoIguaisValidator);
        binder.addValidators(proibeRotaDuplicada);
    }
}
