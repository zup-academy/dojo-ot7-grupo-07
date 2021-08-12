package br.com.zup.edu.nossositedeviagens.config;

import br.com.zup.edu.nossositedeviagens.dto.request.AeroportoRequest;
import br.com.zup.edu.nossositedeviagens.dto.request.RotasRequest;
import br.com.zup.edu.nossositedeviagens.model.Aeroporto;
import br.com.zup.edu.nossositedeviagens.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ProibeAeroportoOrigemDestinoIguaisValidator implements Validator {
    @Autowired
    private AeroportoRepository aeroportoRepository;

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

        Aeroporto aeroportoOrigem = aeroportoRepository.findById(request.getIdAeroportoSaida()).get();
        if (aeroportoOrigem.mesmoId(request.getIdAeroportoDestino())) {
            errors.rejectValue("idAeroportoDestino", null, "O aéroporto de destino precisa ser diferente do de origem." + request.getIdAeroportoDestino());
        }
    }
}