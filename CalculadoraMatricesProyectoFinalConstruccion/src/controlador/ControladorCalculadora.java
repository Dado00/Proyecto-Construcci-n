/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Vista.vistaCalculadora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Modelo.Matriz;

/**
 *
 * @author jairo
 */
public class ControladorCalculadora implements ActionListener {
    String DETERMINANTE = "determinante";
    String INVERSA = "inversa";
    String MULTIPLICAR_ESCALAR = "multiplicar_escalar";
    String MULTIPLICAR_MATRICES = "multiplicar_matrices";
    String SUMA = "suma";
    String METODO_GAUSS = "metodo_gauss";
    String CRAMER = "cramer";
    int FILAS_MAXIMAS = 5;
    int COLUMNAS_MAXIMAS = 5;

    Matriz model;
    JTextField[][] primeraMatriz;
    JTextField[][] segundaMatriz;
    JTextField[][] terceraMatriz;
    vistaCalculadora vista;
    double[][] matrizADouble;
    double[][] matrizBDouble;
    double[][] matrizResultadoDouble;

    public ControladorCalculadora(vistaCalculadora vista) {
        this.vista = vista;
        primeraMatriz = this.vista.getPrimeraMatriz();
        segundaMatriz = this.vista.getSegundaMatriz();
        terceraMatriz = this.vista.getTerceraMatriz();
        model = new Matriz();

        this.vista.getCalcularButton().addActionListener(this);
        this.vista.getDeterminante().addActionListener(this);
        this.vista.getInversa().addActionListener(this);
        this.vista.getMultiplicaEscalar().addActionListener(this);
        this.vista.getMultiplicarMatrices().addActionListener(this);
        this.vista.getSuma().addActionListener(this);
        this.vista.getMetodoGauss().addActionListener(this);
        this.vista.getCramer().addActionListener(this);
        this.vista.getMostrarMatrizA().addActionListener(this);
        this.vista.getMostrarMatrizB().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.vista.getCalcularButton() == e.getSource()) {
            // llamar a modelo para realizar la operacion
            try {

                switch (this.vista.getOperacionSeleccionada().getText()) {
                    case "determinante":
                        matrizADouble = getData(primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                                Integer.valueOf(this.vista.getColumnasA().getText()));
                        double determinante = model.calcularDeterminante(matrizADouble);
                        JOptionPane.showMessageDialog(null, "El Determinante es: " + String.valueOf(determinante));
                        this.vista.getFilasA().setEditable(true);
                        this.vista.getColumnasA().setEditable(true);
                        break;

                    case "inversa":
                        matrizADouble = getData(primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                                Integer.valueOf(this.vista.getColumnasA().getText()));
                        matrizResultadoDouble = model.matrizInversaGaussJordan(matrizADouble);
                        setMatrizResult(matrizResultadoDouble);
                        this.vista.getFilasA().setEditable(true);
                        this.vista.getColumnasA().setEditable(true);
                        break;

                    case "multiplicar_escalar":
                        matrizADouble = getData(primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                                Integer.valueOf(this.vista.getColumnasA().getText()));
                        double escalar = Integer.valueOf(this.vista.getEscalar().getText());
                        matrizResultadoDouble = model.multiplicacionPorEscalar(escalar, matrizADouble);
                        setMatrizResult(matrizResultadoDouble);
                        this.vista.getFilasA().setEditable(true);
                        this.vista.getColumnasA().setEditable(true);
                        break;

                    case "multiplicar_matrices":
                        matrizADouble = getData(primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                                Integer.valueOf(this.vista.getColumnasA().getText()));
                        matrizBDouble = getData(segundaMatriz, Integer.valueOf(this.vista.getFilasB().getText()),
                                Integer.valueOf(this.vista.getColumnasB().getText()));
                        matrizResultadoDouble = model.multiplicarMatrices(matrizADouble, matrizBDouble);
                        setMatrizResult(matrizResultadoDouble);
                        this.vista.getFilasA().setEditable(true);
                        this.vista.getColumnasA().setEditable(true);
                        this.vista.getFilasB().setEditable(true);
                        this.vista.getColumnasB().setEditable(true);
                        break;

                    case "suma":
                        matrizADouble = getData(primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                                Integer.valueOf(this.vista.getColumnasA().getText()));
                        matrizBDouble = getData(segundaMatriz, Integer.valueOf(this.vista.getFilasB().getText()),
                                Integer.valueOf(this.vista.getColumnasB().getText()));
                        matrizResultadoDouble = model.sumarMatrices(matrizADouble, matrizBDouble);
                        setMatrizResult(matrizResultadoDouble);
                        this.vista.getFilasA().setEditable(true);
                        this.vista.getColumnasA().setEditable(true);
                        break;

                    case "metodo_gauss":
                        matrizADouble = getData(primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                                (Integer.valueOf(this.vista.getColumnasA().getText())) - 1);
                        double[] termino = getColumnaData(Integer.valueOf(this.vista.getFilasA().getText()),
                                Integer.valueOf(this.vista.getColumnasA().getText()));
                        double[] result = model.resolverGaussJordan(matrizADouble, termino);
                        setColumnaResult(result);
                        this.vista.getFilasA().setEditable(true);
                        this.vista.getColumnasA().setEditable(true);
                        break;

                    case "cramer":

                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Seleccione una operacion", "Warning",
                                JOptionPane.WARNING_MESSAGE);

                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR!!", JOptionPane.ERROR_MESSAGE);
                this.vista.getFilasA().setEditable(true);
                this.vista.getColumnasA().setEditable(true);
                this.vista.getFilasB().setEditable(true);
                this.vista.getColumnasB().setEditable(true);
            }
        }

        if (this.vista.getDeterminante() == e.getSource()) {
            this.vista.getOperacionSeleccionada().setText(this.DETERMINANTE);
        }

        if (this.vista.getInversa() == e.getSource()) {
            this.vista.getOperacionSeleccionada().setText(this.INVERSA);
        }

        if (this.vista.getMultiplicaEscalar() == e.getSource()) {
            this.vista.getOperacionSeleccionada().setText(this.MULTIPLICAR_ESCALAR);
        }

        if (this.vista.getMultiplicarMatrices() == e.getSource()) {
            this.vista.getOperacionSeleccionada().setText(this.MULTIPLICAR_MATRICES);
        }

        if (this.vista.getSuma() == e.getSource()) {
            this.vista.getOperacionSeleccionada().setText(this.SUMA);
        }

        if (this.vista.getMetodoGauss() == e.getSource()) {
            this.vista.getOperacionSeleccionada().setText(this.METODO_GAUSS);
        }

        if (this.vista.getCramer() == e.getSource()) {

            this.vista.getOperacionSeleccionada().setText(this.CRAMER);
        }

        if (this.vista.getMostrarMatrizA() == e.getSource()) {
            try {
                this.vista.getMatrizRPanel().setVisible(false);
                this.vista.getMatrizAPanel().setVisible(false);
                this.vista.getFilasA().setEditable(false);
                this.vista.getColumnasA().setEditable(false);
                mostrarMatriz(this.primeraMatriz, Integer.valueOf(this.vista.getFilasA().getText()),
                        Integer.valueOf(this.vista.getColumnasA().getText()));
                this.vista.getMatrizAPanel().setVisible(true);
            } catch (Exception exception) {
                this.vista.getFilasA().setEditable(true);
                this.vista.getColumnasA().setEditable(true);
                JOptionPane.showMessageDialog(null, "Valores de filas y columnas no valido.", "ERROR!!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (this.vista.getMostrarMatrizB() == e.getSource()) {
            try {
                this.vista.getMatrizRPanel().setVisible(false);
                this.vista.getMatrizBPanel().setVisible(false);
                this.vista.getFilasB().setEditable(false);
                this.vista.getColumnasB().setEditable(false);
                mostrarMatriz(this.segundaMatriz, Integer.valueOf(this.vista.getFilasB().getText()),
                        Integer.valueOf(this.vista.getColumnasB().getText()));
                this.vista.getMatrizBPanel().setVisible(true);
            } catch (Exception exception) {
                this.vista.getFilasB().setEditable(true);
                this.vista.getColumnasB().setEditable(true);
                JOptionPane.showMessageDialog(null, "Valores de filas y columnas no valido.");
            }
        }

    }
    /**
     * Obtiene los valores de cada celda de la matriz
     * @param matriz recibe la matriz de la interfaz gráfica
     * @param filas recibe la cantidad de filas de la matriz
     * @param columnas recibe la cantidad de columnas de la matriz
     * @return
     */
    private double[][] getData(JTextField[][] matriz, int filas, int columnas) {
        double[][] result = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                result[i][j] = Double.parseDouble(matriz[i][j].getText());
            }
        }
        return result;
    }
    /**
     * Obtiene los valores de cada celda de las columnas de la matriz
     * @param filas 
     * @param columna
     * @return
     */
    private double[] getColumnaData(int filas, int columna) {
        double[] result = new double[filas];
        for (int i = 0; i < filas; i++) {
            result[i] = Double.parseDouble(this.vista.getPrimeraMatriz()[i][columna - 1].getText());
        }

        return result;
    }
    /**
     * Establece los valores de la columna para que después de haber realizado la operación seleccionada
     * @param result el arreglo con los resultados 
     */
    private void setColumnaResult(double[] result) {
        for (int i = 0; i < FILAS_MAXIMAS; i++) {
            for (int j = 0; j < COLUMNAS_MAXIMAS; j++) {
                this.terceraMatriz[i][j].setText(null);
                this.terceraMatriz[i][j].setVisible(false);
            }
        }
        for (int i = 0; i < result.length; i++) {
            this.terceraMatriz[i][0].setVisible(true);
            this.terceraMatriz[i][0].setText(String.valueOf(result[i]));
        }
        this.vista.getMatrizRPanel().setVisible(true);
    }
    /**
     * Establece los valores de la matriz para que después de haber realizado la operación seleccionada
     * @param matrizResult la matriz con los datos 
     */
    private void setMatrizResult(double[][] matrizResult) {

        for (int i = 0; i < FILAS_MAXIMAS; i++) {
            for (int j = 0; j < COLUMNAS_MAXIMAS; j++) {
                this.terceraMatriz[i][j].setText(null);
                this.terceraMatriz[i][j].setVisible(false);
            }
        }

        for (int i = 0; i < matrizResult.length; i++) {
            for (int j = 0; j < matrizResult[i].length; j++) {
                this.terceraMatriz[i][j].setText(String.valueOf(matrizResult[i][j]));
                this.terceraMatriz[i][j].setVisible(true);
            }
        }

        this.vista.getMatrizRPanel().setVisible(true);

    }
    /**
     * Presenta la matriz en la interfaz gráfica
     * @param matriz
     * @param filas
     * @param columnas
     */
    private void mostrarMatriz(JTextField[][] matriz, int filas, int columnas) {

        for (int i = 0; i < FILAS_MAXIMAS; i++) {
            for (int j = 0; j < COLUMNAS_MAXIMAS; j++) {
                matriz[i][j].setText(null);
                matriz[i][j].setVisible(false);
            }
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j].setVisible(true);
            }
        }

    }

}
