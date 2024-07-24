package com.tallerwebi.infraestructura;
import com.tallerwebi.config.HibernateConfig;
import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.RepositorioCalculadora;
import com.tallerwebi.dominio.ServicioCalculadora;
import com.tallerwebi.dominio.ServicioCalculadoraImpl;
import com.tallerwebi.presentacion.ControladorCalculadora;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class})

public class RepositorioCalculadoraTest {

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioCalculadora repositorioCalculadora;

    @BeforeEach
    public void init(){
        this.repositorioCalculadora = new RepositorioCalculadoraImpl(this.sessionFactory);
    }


    @Test
    @Transactional
    @Rollback

    public void queSePuedaGuardarUnCalculo() {
        //preparación
        CalculadoraData calculadoraData = new CalculadoraData(3,5,"+", 8);

        //ejecución
        this.repositorioCalculadora.guardarCalculo(calculadoraData);


        //validación

        sessionFactory.getCurrentSession().save(calculadoraData);


        assertThat(calculadoraData.getResultado(), equalTo(8.0));

    }


    @Test
    @Transactional
    @Rollback

    public void queSePuedaObtenerHistorialDeCalculos() {
        //preparación
        CalculadoraData calculadoraData = new CalculadoraData(3,5,"+", 8);
        CalculadoraData calculadoraDataDos = new CalculadoraData(3,1,"-", 2);
        CalculadoraData calculadoraDataTres = new CalculadoraData(4,3,"*", 12);

        List<CalculadoraData> listaMock = new ArrayList<>();

        this.sessionFactory.getCurrentSession().save(calculadoraData);
        this.sessionFactory.getCurrentSession().save(calculadoraDataDos);
        this.sessionFactory.getCurrentSession().save(calculadoraDataTres);

        //ejecución
        List<CalculadoraData> historialObtenido = this.repositorioCalculadora.obtenerHistorial();

        //validación
        //assertThat(historialObtenido, is(not(empty())));
        assertThat(historialObtenido.size(), equalTo(3));

    }



}
