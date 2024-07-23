package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ServicioCalculadora {
    double calcular (double oper1, double oper2, String operacion);
    void guardarCalculo (CalculadoraData calculadoraData);
    List<CalculadoraData> obtenerHistorial();

    List<CalculadoraData> obtenerCalculosPorFecha(LocalDate fechaABuscar);

}
