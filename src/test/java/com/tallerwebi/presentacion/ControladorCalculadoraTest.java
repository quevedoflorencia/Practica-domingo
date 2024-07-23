package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ServicioCalculadora;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCalculadoraTest {
    private ControladorCalculadora controladorCalculadora;
    private ServicioCalculadora servicioCalculadora;
    private CalculadoraData calculadoraData;

    @BeforeEach
    public void init(){
        this.servicioCalculadora =mock(ServicioCalculadora.class);
        this.controladorCalculadora= new ControladorCalculadora(this.servicioCalculadora);

        this.calculadoraData = mock(CalculadoraData.class);

    }

    @Test
    public void queLuegoDeLoguarseElUsuarioVeaLaCalculadoraVacia(){
      //  Usuario usuario = new Usuario(1L, "test@unlam.edu.ar", "test", "ADMIN", true);

        ModelAndView mav = controladorCalculadora.mostrarCalculadora();

        assertThat(mav.getViewName(), equalToIgnoringCase("calculadora"));

    }
    @Test
    public void procesarFormulario_deberiaCalcularGuardarYRetornarVista() {
        // Datos de prueba
        CalculadoraData calculadoraData = new CalculadoraData(5, 3, "+");

        // Resultados esperados
        double resultadoEsperado = 8;


        // Configurar los mocks
        when(servicioCalculadora.calcular(5, 3, "+")).thenReturn(resultadoEsperado);


        // Ejecución
        ModelAndView modelAndView = controladorCalculadora.procesarFormulario(calculadoraData);

        // Verificación de la vista
        assertThat(modelAndView.getViewName(), equalTo("calculadora"));


    }
    /*
    @Test
    public void queAlCalcularDosMasTresElResultadoSeaCinco(){

        double resultadoEsperado = 5;

        calculadoraData.setOperando1(2.0);
        calculadoraData.setOperando2(3.0);
        calculadoraData.setOperador("+");

        when(servicioCalculadora.calcular(28.0, 3.0, "+")).thenReturn(resultadoEsperado);

        // Ejecución
        ModelAndView mav = controladorCalculadora.procesarFormulario(calculadoraData);


        assertThat(mav.getViewName(), equalTo("calculadora"));

        ModelMap modelo = mav.getModelMap();
        assertThat(modelo.get("resultadoFinal"), equalTo(resultadoEsperado));

        //assertThat(mav.getModel().get("resultadoFinal").toString(),equalTo(resultadoEsperado));


    }
*/

}
