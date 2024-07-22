package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCalculadoraImpl implements ServicioCalculadora{
    private List<CalculadoraData> historialCalculos = new ArrayList<>();

    @Override
    public double calcular(double oper1, double oper2, String operador) {
        double resultado = 0;

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

    public void guardarCalculo(CalculadoraData calculadoraData) {
        historialCalculos.add(calculadoraData);
    }

    public List<CalculadoraData> obtenerHistorial() {
        return historialCalculos;
    }
}
