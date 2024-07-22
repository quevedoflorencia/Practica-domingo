package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

@Service
public interface ConversionService {

    double convertir (double monto, String origen, String destino);


}
