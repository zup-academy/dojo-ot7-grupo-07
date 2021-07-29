package br.com.zup.edu.nossositedeviagens.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class UnicoValorValidator implements ConstraintValidator<UnicoValor, Object> {
    private Class<?> target;
    private String field;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UnicoValor constraintAnnotation) {
        this.target = constraintAnnotation.target();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String query = "SELECT c FROM "+ target.getName()+" c WHERE c."+ field +" = '"+ o +"'";

        List lista = manager.createQuery(query).getResultList();

        return lista.isEmpty();
    }
}
