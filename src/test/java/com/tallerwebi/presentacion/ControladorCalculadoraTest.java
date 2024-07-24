package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ServicioCalculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ControladorCalculadoraTest {

    private ControladorCalculadora controladorCalculadora;
    private ServicioCalculadora servicioCalculadora;

    @BeforeEach
    public void init(){
        this.servicioCalculadora = mock(ServicioCalculadora.class);
        this.controladorCalculadora = new ControladorCalculadora(this.servicioCalculadora);
    }

    @Test
    public void queLuegoDeLoguarseElUsuarioVeaLaCalculadoraVacia(){
        //  Usuario usuario = new Usuario(1L, "test@unlam.edu.ar", "test", "ADMIN", true);

        ModelAndView mav = controladorCalculadora.mostrarCalculadora();

        assertThat(mav.getViewName(), equalToIgnoringCase("calculadora"));

    }

    @Test
    public void queLuegoDePresionarCalcularMeDevuelvaLaVistaCalculadora(){
        // preparación
        when(this.servicioCalculadora.calcular(6.0, 3.0, "+")).thenReturn(9.0);
        CalculadoraData calculo = new CalculadoraData(6.0, 3.0, "+", 9.0);
        doNothing().when(this.servicioCalculadora).guardarCalculo(calculo); //doNothing porque guardarCalculo es void.


        // ejecución
        ModelAndView mav = this.controladorCalculadora.procesarFormulario(calculo);


        //verificación
        assertThat(mav.getViewName(), equalToIgnoringCase("calculadora")); // Testeo que devuelva la vista correcta que es "Calculadora"

    }
    @Test
    public void queLuegoDeCalcularMuestreElHistorialConLaCantidadCorrectaDeCalculos(){
        //preparacion
        List<CalculadoraData> calculosMock = new ArrayList<>();

        calculosMock.add(new CalculadoraData(2.0, 4.0,"+"));
        calculosMock.add(new CalculadoraData(5.0, 4.0,"+"));
        calculosMock.add(new CalculadoraData(3.0, 3.0,"+"));
        calculosMock.add(new CalculadoraData(7.0, 3.0,"+"));

        when(this.servicioCalculadora.obtenerHistorial()).thenReturn(calculosMock);
        CalculadoraData calculo = new CalculadoraData(6.0, 3.0, "+", 9.0);

        //ejecucion
        ModelAndView mav = this.controladorCalculadora.procesarFormulario(calculo);

        //verificacion
        //Testeo que el historial tenga la cantidad de calculos correcto

        List<CalculadoraData> calculos = (List<CalculadoraData>) mav.getModel().get("historial");
        assertThat(calculos.size(), equalTo(4)); // Existan 4 elemento

    }


    @Test
    public void queAlPresionarCalcularArrojeElResultadoCorrectoDeLaOperacion(){
        //preparacion
        when(this.servicioCalculadora.calcular(6.0, 3.0, "+")).thenReturn(9.0);
        CalculadoraData calculo = new CalculadoraData(6.0, 3.0, "+", 9.0);
        doNothing().when(this.servicioCalculadora).guardarCalculo(calculo); //doNothing porque guardarCalculo es void.


        // ejecución
        ModelAndView mav = this.controladorCalculadora.procesarFormulario(calculo);

        //verificacion
        //Testeo que el resultado sea correcto
        assertThat(mav.getModel().get("resultadoFinal"), equalTo(9.0));
    }



}
