package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@Service
@Transactional
public class ServicioCalculadoraImpl implements ServicioCalculadora{
    //private List<CalculadoraData> historialCalculos = new ArrayList<>();
    private RepositorioCalculadora repositorioCalculadora;

    @Autowired
    public ServicioCalculadoraImpl (RepositorioCalculadora repositorioCalculadora){
        this.repositorioCalculadora=repositorioCalculadora;
    }

    @Override
    public double calcular(double oper1, double oper2, String operador) {
        double resultado = 0;

        if (!isNumeric(oper1)||!isNumeric(oper2)){
            throw new IllegalArgumentException("Debe ingresar numeros, no letras");
        }
        switch (operador) {
            case "+":
                resultado = oper1 + oper2;
                break;
            case "-":
                resultado = oper1 - oper2;
                break;
            case "*":
                resultado = oper1 * oper2;
                break;
            case "/":
                if (oper2 != 0) {
                    resultado = oper1 / oper2;
                }else {
                    throw new IllegalArgumentException("No se puede dividir por cero");
                }
                break;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }

        return resultado;
    }
/*
    public void guardarCalculo(CalculadoraData calculadoraData) {
        historialCalculos.add(calculadoraData);
    }*/
    public void guardarCalculo (CalculadoraData calculadoraData){
        repositorioCalculadora.guardarCalculo(calculadoraData);
    }

    public List<CalculadoraData> obtenerHistorial() {

        return repositorioCalculadora.obtenerHistorial();
    }
}
