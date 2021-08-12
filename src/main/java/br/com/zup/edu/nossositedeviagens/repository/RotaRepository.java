package br.com.zup.edu.nossositedeviagens.repository;

import br.com.zup.edu.nossositedeviagens.model.Rota;
import org.springframework.data.repository.CrudRepository;

public interface RotaRepository extends CrudRepository<Rota, Long> {


    boolean existsByAeroportoSaida_saidaAndAeroportoDestino_destino(Long idAeroportoSaida, Long idAeroportoDestino);

}
