package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.ConversionData;
import com.tallerwebi.dominio.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorConversor {
   private ConversionService conversionService;

   @Autowired
   public ControladorConversor(ConversionService conversionService){this.conversionService=conversionService;}

    @RequestMapping("/convertir")
    public ModelAndView mostrarConversor() {

        ModelMap modelo = new ModelMap();

        modelo.put("conversionData", new ConversionData());
        return new ModelAndView("calculadoraTC", modelo);
    }

    @RequestMapping(path = "/convertir", method = RequestMethod.POST)
    public ModelAndView convertir(@ModelAttribute("conversionData") ConversionData conversionData) {
        ModelMap modelo = new ModelMap();
        try {
            double resultado = conversionService.convertir(conversionData.getMonto(), conversionData.getMonedaOrigen(), conversionData.getMonedaDestino());
            modelo.put("resultado", resultado);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return new ModelAndView("calculadoraTC", modelo);
    }




}
