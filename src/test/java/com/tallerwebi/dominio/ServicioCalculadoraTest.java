package com.tallerwebi.dominio;
import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ServicioCalculadora;
import com.tallerwebi.presentacion.ControladorCalculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ServicioCalculadoraTest {

    @Mock
    private RepositorioCalculadora repositorioCalculadora;
    @InjectMocks
    private ServicioCalculadora servicioCalculadora;

    @BeforeEach
    public void init(){
        this.repositorioCalculadora = mock(RepositorioCalculadora.class);
        this.servicioCalculadora = new ServicioCalculadoraImpl(this.repositorioCalculadora);
    }

    @Test
    public void queSePuedanSumarDosNumeros(){
        //preparación
        double numeroUno = 2.0;
        double numeroDos = 5.0;
        String operacion = "+";

        //ejecución
        double resultado = this.servicioCalculadora.calcular(numeroUno, numeroDos, operacion);

        //validación
        assertThat(resultado, equalTo(7.0));

    }

    @Test
    public void queSePuedanRestarDosNumeros(){
        //preparación
        double numeroUno = 10.0;
        double numeroDos = 5.0;
        String operacion = "-";

        //ejecución
        double resultado = this.servicioCalculadora.calcular(numeroUno, numeroDos, operacion);

        //validación
        assertThat(resultado, equalTo(5.0));
    }

    @Test
    public void queSePuedanMultiplicarDosNumeros(){
        //preparación
        double numeroUno = 10.0;
        double numeroDos = 5.0;
        String operacion = "*";

        //ejecución
        double resultado = this.servicioCalculadora.calcular(numeroUno, numeroDos, operacion);

        //validación
        assertThat(resultado, equalTo(50.0));
    }


    @Test
    public void queSePuedanDividirDosNumeros(){
        //preparación
        double numeroUno = 10.0;
        double numeroDos = 5.0;
        String operacion = "/";

        //ejecución
        double resultado = this.servicioCalculadora.calcular(numeroUno, numeroDos, operacion);

        //validación
        assertThat(resultado, equalTo(2.0));
    }

    @Test
    public void queNoSePuedaDividirPorCero(){
        //preparación
        double numeroUno = 10.0;
        double numeroDos = 0.0;
        String operacion = "/";

        //ejecución

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servicioCalculadora.calcular(numeroUno, numeroDos, operacion);
        });

        //validación
        assertThat(exception.getMessage(), containsString("No se puede dividir por cero"));
    }


    @Test
    public void queNoSePuedaHacerOperacionConOperadorNoValido(){
        //preparación
        double numeroUno = 10.0;
        double numeroDos = 5.0;
        String operacion = "#";

        //ejecución

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servicioCalculadora.calcular(numeroUno, numeroDos, operacion);
        });

        //validación
        assertThat(exception.getMessage(), containsString("Operador no válido"));
    }


    // A este escenario nunca llega porque al ser tipado no me deja mandar un valor no numérico.
    @Test
    public void queSePuedaProcesarLaOperacion(){
        //preparación
        double numeroUno = 10.8;
        double numeroDos = 0.0;
        String operacion = "/";

        //ejecución

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servicioCalculadora.calcular(numeroUno, numeroDos, operacion);
        });

        //validación
        assertThat(exception.getMessage(), not(containsString("Operador no válido")));
    }

    @Test
    public void queSePuedaGuardarUnCalculo() {
        //preparación
        CalculadoraData calculadoraData = new CalculadoraData(3,5,"+", 8);
        doNothing().when(this.repositorioCalculadora).guardarCalculo(calculadoraData);

        //ejecución
        this.servicioCalculadora.guardarCalculo(calculadoraData);


        //validación
        verify(this.repositorioCalculadora, times(1)).guardarCalculo(calculadoraData);
    }


    @Test
    public void queSePuedaObtenerHistorialDeCalculos() {
        //preparación
        CalculadoraData calculadoraData = new CalculadoraData(3,5,"+", 8);
        CalculadoraData calculadoraDataDos = new CalculadoraData(3,1,"-", 2);
        List<CalculadoraData> listaMock = new ArrayList<>();

        listaMock.add(calculadoraData);
        listaMock.add(calculadoraDataDos);

        when(this.repositorioCalculadora.obtenerHistorial()).thenReturn(listaMock);

        //ejecución
        List<CalculadoraData> historialObtenido = servicioCalculadora.obtenerHistorial();
        List<CalculadoraData> historialObtenidoDos = servicioCalculadora.obtenerHistorial();


        //validación
        //assertThat(historialObtenido.size(), equalTo(2));
        verify(this.repositorioCalculadora, times(2)).obtenerHistorial();
    }

    /*verify(...): Este método de Mockito se usa para verificar que un método en un objeto simulado fue llamado.
    Aquí, se está verificando que el método obtenerHistorial() se llamó en el objeto simulado repositorioCalculadora.
    */

}
