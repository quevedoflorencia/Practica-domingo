package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioCalculadora {
    void guardarCalculo(CalculadoraData calculadoraData);
    List<CalculadoraData> obtenerHistorial();

    List<CalculadoraData> obtenerCalculosPorFecha(LocalDate fechaABuscar);
}
