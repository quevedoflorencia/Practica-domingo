package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConversionServiceImpl implements ConversionService{
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Tasas de cambio ficticias para el ejemplo
        exchangeRates.put("USD_EUR", 0.85);
        exchangeRates.put("USD_JPY", 110.0);
        exchangeRates.put("USD_GBP", 0.75);
        exchangeRates.put("EUR_USD", 1.18);
        exchangeRates.put("EUR_JPY", 129.53);
        exchangeRates.put("EUR_GBP", 0.88);
        exchangeRates.put("JPY_USD", 0.0091);
        exchangeRates.put("JPY_EUR", 0.0077);
        exchangeRates.put("JPY_GBP", 0.0068);
        exchangeRates.put("GBP_USD", 1.33);
        exchangeRates.put("GBP_EUR", 1.14);
        exchangeRates.put("GBP_JPY", 151.77);
    }
    @Override
    public double convertir(double monto, String origen, String destino) {

        String key= origen + "_"+destino;
        if (!exchangeRates.containsKey(key)) {
            throw new IllegalArgumentException("No se puede convertir entre las monedas seleccionadas");
        }
        double tasa = exchangeRates.get(key);
        return monto * tasa;
    }

}
