package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioCalculadora {
    void guardarCalculo(CalculadoraData calculadoraData);
    List<CalculadoraData> obtenerHistorial();

}
