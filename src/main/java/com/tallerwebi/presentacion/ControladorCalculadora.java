package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ServicioCalculadora;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorCalculadora {

    private ServicioCalculadora servicioCalculadora;


    @Autowired
    public ControladorCalculadora(ServicioCalculadora servicioCalculadora){
        this.servicioCalculadora = servicioCalculadora;
    }

    @RequestMapping("/calculadora")
    public ModelAndView mostrarCalculadora() {

        ModelMap modelo = new ModelMap();

        modelo.put("calculadoraData", new CalculadoraData());
        return new ModelAndView("calculadora", modelo);
    }

    @RequestMapping(path = "/calcular", method = RequestMethod.POST)
    public ModelAndView procesarFormulario(@ModelAttribute ("calculadoraData") CalculadoraData calculadoraData ) {

        ModelMap modelo = new ModelMap();

        double resultadoFinal= servicioCalculadora.calcular(calculadoraData.getOperando1(), calculadoraData.getOperando2(), calculadoraData.getOperador());
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

       List<CalculadoraData> historial= servicioCalculadora.obtenerHistorial();

        modelo.put("historial", historial);
        return new ModelAndView("historial", modelo);
    }

    @RequestMapping(path = "/historial-fecha", method = RequestMethod.GET)
    public ModelAndView mostrarFormulario() {
        ModelMap modelo = new ModelMap();
        modelo.put("calculadoraData", new CalculadoraData());
        return new ModelAndView("historial-fecha", modelo);
    }

    @RequestMapping(path = "/historial-fecha", method = RequestMethod.POST)
    public ModelAndView historialPorFechaIngresada(@ModelAttribute ("calculadoraData") CalculadoraData calculadoraData ) {

        ModelMap modelo = new ModelMap();

        LocalDate fechaABuscar= calculadoraData.getFecha();

        List<CalculadoraData> historialPorFecha =servicioCalculadora.obtenerCalculosPorFecha(fechaABuscar);

        if (historialPorFecha.isEmpty()) {
            modelo.put("mensaje", "No se encontraron cálculos para la fecha ingresada.");
        } else {
            modelo.put("historialFecha", historialPorFecha);
        }
        return new ModelAndView("historial-fecha", modelo);
    }



/*
    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
        if (usuarioBuscado != null) {
            request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
            return new ModelAndView("redirect:/home");
        } else {
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login", model);
    }*/
/*
    @RequestMapping(path = "/calcular", method = RequestMethod.POST)
    public ModelAndView calcular(@ModelAttribute("calculadoraData") CalculadoraData calculadoraData) {
        ModelMap model = new ModelMap();

        Double oper1 = calculadoraData.getOperando1();
        Double oper2 = calculadoraData.getOperando2();
        String operacion = calculadoraData.getOperador();



        //Double resultado = servicioCalculadora.calcular(oper1, oper2, operacion);
        Double resultado = servicioCalculadora.calcular(calculadoraData.getOperando1(), calculadoraData.getOperando2(), calculadoraData.getOperador());

        model.put("resultado", resultado);
        model.put("msg", "se imprime");
        return new ModelAndView("calculadora", model);
    }
        /*
        try{
            servicioLogin.registrar(usuario);
        } catch (UsuarioExistente e){
            model.put("error", "El usuario ya existe");
            return new ModelAndView("nuevo-usuario", model);
        } catch (Exception e){
            model.put("error", "Error al registrar el nuevo usuario");
            return new ModelAndView("nuevo-usuario", model);
        }
        return new ModelAndView("redirect:/login");
    }
/*
    @RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelMap model = new ModelMap();
        model.put("usuario", new Usuario());
        return new ModelAndView("nuevo-usuario", model);
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }*/
}