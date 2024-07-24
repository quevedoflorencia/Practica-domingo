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
    public void queSiNoHayCalculosMeMuestreElMensajeQueAunNoHayCalculos(){
        //preparacion
        List<CalculadoraData> calculosMock = new ArrayList<>();
        when(servicioCalculadora.obtenerHistorial()).thenReturn(calculosMock);
        //ejecucion

        ModelAndView mav=this.controladorCalculadora.procesarFormulario();
        //validacion
        assertThat(mav.getModel().get("mensaje"),equalTo("Aún no hay cálculos."));
    }
    @Test
    public void queSiHayCalculosMeImprimaLosMismos(){
        //preparacion
        List<CalculadoraData> calculosMock = new ArrayList<>();
        calculosMock.add(new CalculadoraData(2.0, 4.0,"+"));

        when(servicioCalculadora.obtenerHistorial()).thenReturn(calculosMock);

        //ejecucion
        ModelAndView mav=this.controladorCalculadora.procesarFormulario();
        List<CalculadoraData>historialRecibido= (List<CalculadoraData>) mav.getModel().get("historial");
        //validacion
        assertThat(historialRecibido.size(), equalTo(1));
    }




}
