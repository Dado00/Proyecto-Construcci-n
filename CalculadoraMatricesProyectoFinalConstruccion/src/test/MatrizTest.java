package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Modelo.Matriz;

public class MatrizTest {

    Matriz matrizA = new Matriz(1, 1);
    Matriz matrizB = new Matriz(1, 1);
    Matriz matrizResultado = new Matriz(1, 1);
    double valorEsperado;

    @Test
    public void testSumarMatrices() {
        // SetUp
        matrizA.insertar(0, 0, 5);
        matrizB.insertar(0, 0, 8);
        try {
            matrizResultado.setM(matrizResultado.sumarMatrices(matrizA.getM(), matrizB.getM()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // expected value
        valorEsperado = 13;

        // assertEquals to get actual value
        assertTrue(valorEsperado == matrizResultado.getM()[0][0]);
    }

    @Test
    public void testMultiplicacionPorEscalar() {
        // SetUp
        matrizA.insertar(0, 0, 5);
        try {
            matrizResultado.setM(matrizResultado.multiplicacionPorEscalar(3, matrizA.getM()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // expected value
        valorEsperado = 15;

        // assertEquals to get actual value
        assertTrue(valorEsperado == matrizResultado.getM()[0][0]);
    }

    @Test
    public void testMultiplicarMatrices() {
        // SetUp
        matrizA.insertar(0, 0, 5);
        matrizB.insertar(0, 0, 8);
        try {
            matrizResultado.setM(matrizResultado.multiplicarMatrices(matrizA.getM(), matrizB.getM()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // expected value
        valorEsperado = 40;

        // assertEquals to get actual value
        assertTrue(valorEsperado == matrizResultado.getM()[0][0]);
    }

    @Test
    public void testMatrizInversaGaussJordan() {
        // SetUp
        Matriz matrizA = new Matriz(2, 2);
        matrizA.insertar(0, 0, 4);
        matrizA.insertar(0, 1, -2);
        matrizA.insertar(1, 0, 3);
        matrizA.insertar(1, 1, -1);
        Matriz matrizResultado = new Matriz(2, 2);
        try {
            matrizResultado.setM(matrizResultado.matrizInversaGaussJordan(matrizA.getM()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // expected value
        Matriz valorEsperado = new Matriz(2, 2);
        valorEsperado.insertar(0, 0, -0.5);
        valorEsperado.insertar(0, 1, 1);
        valorEsperado.insertar(1, 0, -1.5);
        valorEsperado.insertar(1, 1, 2);

        // assertEquals to get actual value
        for (int i = 0; i < matrizResultado.getFilas(); i++) {
            for (int j = 0; j < matrizResultado.getColumnas(); j++) {
                assertTrue(valorEsperado.getM()[i][j] == matrizResultado.getM()[i][j]);
            }
        }
    }

    @Test
    public void testCalcularDeterminante() {
        // SetUp
        Matriz matrizA = new Matriz(2, 2);
        matrizA.insertar(0, 0, 3);
        matrizA.insertar(0, 1, 9);
        matrizA.insertar(1, 0, -4);
        matrizA.insertar(1, 1, 7);
        double resultado = 0;
        try {
            resultado = matrizA.determinante(matrizA.getM());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // expected value
        double valorEsperado = 57;

        // assertTrue to get actual value
        assertTrue(valorEsperado == resultado);
    }

    /**
     *
     * 
     * public double[] resolverGaussJordan(double matriz[][], double
     * termino[]) {
     * 
     * // convertir la matriz aumentada en la matriz identidad
     * for (int i = 0; i <= termino.length - 1; i++) {
     * double pivote, fila = 0;
     * pivote = matriz[i][i];// se seleciona el pivote
     * // se pasa a convertir en 1 al pivote seleionado
     * for (int indice = 0; indice <= termino.length - 1; indice++) {
     * matriz[i][indice] = ((matriz[i][indice]) / pivote);
     * }
     * termino[i] = ((termino[i]) / pivote);
     * 
     * for (int x = 0; x <= termino.length - 1; x++) {
     * if (i != x) {
     * 
     * for (int columna = 0; columna <= termino.length - 1; columna++) {
     * // se hace cero a todos los elementos de la colunma que no sean el
     * pivote
     * matriz[x][columna] = matriz[x][columna] - fila * matriz[i][columna];
     * 
     * }
     * termino[x] = termino[x] - fila * termino[i];
     * }
     * }
     * }
     * return termino;// retorna terminos
     * }
     * 
     *
     * 
     */

}
