package com.tallerwebi.dominio;

public class CalculadoraData {

    private double operando1;
    private double operando2;
    private String operador;
    private double resultado;

    public CalculadoraData() {}

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
}
