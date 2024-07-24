package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ServicioCalculadora;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorCalculadora {

    private ServicioCalculadora servicioCalculadora;

    @Autowired
    public ControladorCalculadora(ServicioCalculadora servicioCalculadora){
        this.servicioCalculadora = servicioCalculadora;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, dateFormatter));
            }
        });
    }

    @RequestMapping("/calculadora")
    public ModelAndView mostrarCalculadora() {
        ModelMap modelo = new ModelMap();


        modelo.put("calculadoraData", new CalculadoraData());

        return new ModelAndView("calculadora", modelo);
    }

    @RequestMapping(path = "/calcular", method = RequestMethod.POST)
    public ModelAndView procesarFormulario(@ModelAttribute("calculadoraData") CalculadoraData calculadoraData, HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        /*
        Long usuarioId = (Long) request.getSession().getAttribute("ID");
        if (usuarioId == null) {
            // Manejar el caso en que el usuario no esté autenticado
            return new ModelAndView("redirect:/login");
        }*/

        double resultadoFinal = servicioCalculadora.calcular(calculadoraData.getOperando1(), calculadoraData.getOperando2(), calculadoraData.getOperador());

        calculadoraData.setResultado(resultadoFinal);
        calculadoraData.setFecha(LocalDate.now());





        servicioCalculadora.guardarCalculo(calculadoraData);
        modelo.put("resultadoFinal", resultadoFinal);
        modelo.put("historial", servicioCalculadora.obtenerHistorial());

        return new ModelAndView("calculadora", modelo);
    }

    @RequestMapping(path = "/historial")
    public ModelAndView procesarFormulario() {
        ModelMap modelo = new ModelMap();
        List<CalculadoraData> historial = servicioCalculadora.obtenerHistorial();

        if (historial.isEmpty()) {
            modelo.put("mensaje", "Aún no hay cálculos.");
        } else {
            modelo.put("historial", historial);
        }
        return new ModelAndView("historial", modelo);

    }

    @RequestMapping(path = "/historial-fecha", method = RequestMethod.GET)
    public ModelAndView mostrarFormulario() {
        ModelMap modelo = new ModelMap();
        modelo.put("calculadoraData", new CalculadoraData());
        return new ModelAndView("historial-fecha", modelo);
    }

    @RequestMapping(path = "/historial-fecha", method = RequestMethod.POST)
    public ModelAndView historialPorFechaIngresada(@ModelAttribute("calculadoraData") CalculadoraData calculadoraData) {
        ModelMap modelo = new ModelMap();
        LocalDate fechaBuscada = calculadoraData.getFecha();
        List<CalculadoraData> historialPorFecha = servicioCalculadora.obtenerCalculosPorFecha(fechaBuscada);

        if (historialPorFecha.isEmpty()) {
            modelo.put("mensaje", "No se encontraron cálculos para la fecha ingresada.");
        } else {
            modelo.put("historialFecha", historialPorFecha);
        }
        return new ModelAndView("historial-fecha", modelo);
    }
}