package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.CalculadoraData;
import com.tallerwebi.dominio.RepositorioCalculadora;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RepositorioCalculadoraImpl implements RepositorioCalculadora {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCalculadoraImpl (SessionFactory sessionFactory){this.sessionFactory=sessionFactory;}

    @Override
    public void guardarCalculo(CalculadoraData calculadoraData) {
        sessionFactory.getCurrentSession().save(calculadoraData);
    }


    @Override
    public List<CalculadoraData> obtenerHistorial() {
        return sessionFactory.getCurrentSession()
                .createCriteria(CalculadoraData.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }


    @Override

    public List<CalculadoraData> obtenerCalculosPorFecha(LocalDate fechaABuscar) {
        return sessionFactory.getCurrentSession()
                .createCriteria(CalculadoraData.class)
                .add(Restrictions.eq("fecha",fechaABuscar))
                .list();
    }




}
