package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

@Service
public class ServicioCalculadoraImpl implements ServicioCalculadora{

    @Override

    public double calcular(double oper1, double oper2, String operacion, double resultado) {

        switch (operacion) {
            case "sumar":
                resultado = oper1 + oper2;
                break;
            case "restar":
                resultado = oper1 - oper2;
                break;
            case "multiplicar":
                resultado = oper1 * oper2;
                break;
            case "dividir":
                if (oper2 != 0) {
                    resultado = oper1 / oper2;
                }
                break;
        }

        return resultado;
    }
}
