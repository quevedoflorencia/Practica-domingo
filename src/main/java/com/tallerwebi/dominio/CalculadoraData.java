package com.tallerwebi.dominio;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class CalculadoraData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private double operando1;
    private double operando2;
    private String operador;
    private double resultado;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    public CalculadoraData() {}

    public CalculadoraData(LocalDate fecha, double operando1, double operando2, String operador, double resultado, Usuario usuario) {
        this.fecha = fecha;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
        this.resultado = resultado;
        this.usuario = usuario;
    }

    public CalculadoraData(double operando1, double operando2, String operador, double resultado) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
        this.resultado = resultado;
    }

    public CalculadoraData(double operando1, double operando2, String operador) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
    }

    public CalculadoraData(LocalDate fecha, double operando1, double operando2, String operador, double resultado) {
        this.fecha = fecha;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operador = operador;
        this.resultado = resultado;
    }

    public CalculadoraData(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOperando1() {
        return operando1;
    }

    public void setOperando1(double operando1) {
        this.operando1 = operando1;
    }

    public double getOperando2() {
        return operando2;
    }

    public void setOperando2(double operando2) {
        this.operando2 = operando2;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
