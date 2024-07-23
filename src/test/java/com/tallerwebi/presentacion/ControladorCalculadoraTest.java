package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ServicioCalculadora;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ControladorCalculadoraTest {

    @Mock
    private ServicioCalculadora servicioCalculadoraMock;

    @InjectMocks
    private ControladorCalculadora controladorCalculadora;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //Comienzo con el test basico de que exista la vista calculadora, basicamente me traigo el controlador/vista y pregunto si existe alguna vista con el nombre.
    @Test
    public void testMostrarCalculadora() {
        ModelAndView modelAndView = controladorCalculadora.mostrarCalculadora();
        //Aca pregunto si existe alguna vista con el nombre calculadora y me traigo los nombres de lo que existe.
        assertEquals("calculadora", modelAndView.getViewName());
        ModelMap model = modelAndView.getModelMap();
        //Comparo contra lo que trae, y en caso de que si, me da true.
        assertEquals(new CalculadoraData(), model.get("calculadoraData"));
    }

    //Aca realizo un test para procesar los datos que ingreso, y que sean aceptados por mi vista/controlador
    @Test
    public void testProcesarFormulario() {
        //Ingreso datos nuevos y les asigno un valor
        CalculadoraData calculadoraData = new CalculadoraData();
        calculadoraData.setOperando1(5.0);
        calculadoraData.setOperando2(3.0);
        calculadoraData.setOperador("+");
        //Planteo la situacion desde el cuando
        when(servicioCalculadoraMock.calcular(5.0, 3.0, "+")).thenReturn(8.0);

        ModelAndView modelAndView = controladorCalculadora.procesarFormulario(calculadoraData);
        //Proceso los valores y verifico el resultado final.
        assertEquals("calculadora", modelAndView.getViewName());
        ModelMap model = modelAndView.getModelMap();
        assertEquals(8.0, model.get("resultadoFinal"));
        verify(servicioCalculadoraMock, times(1)).calcular(5.0, 3.0, "+");
        verify(servicioCalculadoraMock, times(1)).guardarCalculo(calculadoraData);
        verify(servicioCalculadoraMock, times(1)).obtenerHistorial();
    }

    @Test
    public void testHistorial() {
        CalculadoraData data1 = new CalculadoraData();
        CalculadoraData data2 = new CalculadoraData();
        List<CalculadoraData> historialMock = Arrays.asList(data1, data2);
        //Mismo caso que el de la vista de encontrar si existe, solo que pregunto si ademas hay registros
        when(servicioCalculadoraMock.obtenerHistorial()).thenReturn(historialMock);
        //Me traigo los datos del formulario de historial
        ModelAndView modelAndView = controladorCalculadora.procesarFormulario();
        //Aca pregunto, primero me traigo el nombre y verifico que exista el nombre de la vista,
        // luego pregunto por el primer dato y lo traigo.
        assertEquals("historial", modelAndView.getViewName());
        ModelMap model = modelAndView.getModelMap();
        assertEquals(historialMock, model.get("historial"));
        verify(servicioCalculadoraMock, times(1)).obtenerHistorial();
    }
}
