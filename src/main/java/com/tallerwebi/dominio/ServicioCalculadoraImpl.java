package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

@Service
public class ServicioCalculadoraImpl implements ServicioCalculadora{

    @Override

    public Double calcular(Double oper1, Double oper2, String operacion) {

        Double resultado = null;
        if (operacion == "+") {
            resultado = oper1 + oper2;

        }
        return resultado;
    }
}
