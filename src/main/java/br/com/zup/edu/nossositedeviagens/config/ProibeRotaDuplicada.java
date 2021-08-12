package br.com.zup.edu.nossositedeviagens.config;

import br.com.zup.edu.nossositedeviagens.dto.request.RotasRequest;
import br.com.zup.edu.nossositedeviagens.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeRotaDuplicada implements Validator {


    @Autowired
    private RotaRepository rotaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RotasRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        RotasRequest request = (RotasRequest) o;
        if (!rotaRepository.existsByAeroportoSaida_saidaAndAeroportoDestino_destino(request.getIdAeroportoSaida(), request.getIdAeroportoDestino())) {
            errors.rejectValue("Nome", null, "Já existe um(a) estado(a) com o nome informado. " + request.getNome());
        }
    }


}
