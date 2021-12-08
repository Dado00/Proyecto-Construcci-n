package Modelo;

public class Matriz {
    public final int FILAS_MAXIMAS = 5; // Número máximo de filas que puede tener una matriz
    public final int COLUMNAS_MAXIMAS = 5; // Número máximo de columnas que puede tener una matriz
    private int filas;
    private int columnas;
    private double[][] matriz;

    public Matriz() {
        this.filas = FILAS_MAXIMAS;
        this.columnas = COLUMNAS_MAXIMAS;
        this.matriz = new double[FILAS_MAXIMAS][COLUMNAS_MAXIMAS];
    }

    /**
     * Crea una matriz recibiendo como parámetros el número de filas y número de
     * columnas de la matriz.
     * 
     * @param filas    Número de filas
     * @param columnas Número de columnas
     */
    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new double[filas][columnas];
    }

    /**
     * Inserta el valor de tipo double en la celda que se le indique.
     * 
     * @param fila    Fila en la que se va a insertar
     * @param columna Columna en la que se va a insertar
     * @param valor   Número que se va a insertar
     */
    public void insertar(int fila, int columna, int valor) {
        this.matriz[fila][columna] = valor;
    }

    /**
     * Suma dos matrices si ambas son de forma MxN
     * 
     * @param matrizA primera matriz de orden MxN
     * @param matrizB segunda matriz de orden MxN
     * @return Una matriz de orden MxN con el resultado de la suma de cada celda
     */
    public double[][] sumarMatrices(double[][] matrizA, double[][] matrizB) throws Exception {
        double[][] matrizResultado = new double[matrizA.length][matrizA[0].length];
        boolean mismoNumeroFilas = matrizA.length == matrizB.length;
        boolean mismoNumeroColumnas = matrizA[0].length == matrizB[0].length;
        if (mismoNumeroFilas && mismoNumeroColumnas) {
            int totalFilas = matrizA.length;
            int totalColumnas = matrizA[0].length;
            for (int fila = 0; fila < totalFilas; fila++) {
                for (int columna = 0; columna < totalColumnas; columna++) {
                    matrizResultado[fila][columna] = matrizA[fila][columna] + matrizB[fila][columna];

                }
            }
        } else {
            throw new Exception("Se necesita que las matrices tengan las mismas dimensiones");
        }
        return matrizResultado;
    }

    /**
     * Calcular transpuesta de una matriz
     * 
     * @param matriz
     * @return
     */
    public double[][] calcularTranspuesta(double[][] matriz) {
        double[][] matrizT = new double[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizT[j][i] = matriz[i][j];
            }
        }
        return matrizT;
    }

    /**
     * Multiplica una matriz de orden MxN por un escalar C
     * 
     * @param escalar recibe el escalar C
     * @param matriz  recibe la matriz de orden MxN a multiplicar por C
     * @return Uma matriz de orden MxN donde cada celda es multiplicada por C
     */
    public double[][] multiplicacionPorEscalar(double escalar, double[][] matriz) {
        double[][] nuevaResultado = new double[matriz.length][matriz[0].length];
        int totalFilas = matriz.length;
        int totalColumnas = matriz[0].length;
        for (int fila = 0; fila < totalFilas; fila++) {
            for (int columna = 0; columna < totalColumnas; columna++) {
                nuevaResultado[fila][columna] = matriz[fila][columna] * escalar;
            }
        }
        return nuevaResultado;
    }

    /**
     * Multiplica dos matrices si la primera matriz es de orden MxN y la segunda es
     * de orden NxP
     * 
     * @param matrizA Primera matriz de orden MxN
     * @param matrizB Segunda matriz de orden NxP
     * @return Una matriz de orden MxP con la multiplicación efectuada
     * @throws Exception Genera una excepción con un mensaje indicando que las
     *                   matrices no pueden ser multiplicadas
     */
    public double[][] multiplicarMatrices(double[][] matrizA, double[][] matrizB) throws Exception {
        double[][] matrizResultado = new double[matrizA.length][matrizB[0].length];
        int columnasMatrizA = matrizA[0].length;
        int filasMatrizB = matrizB.length;
        if (columnasMatrizA == filasMatrizB) {
            int totalFilas = matrizA.length;
            int totalColumnas = matrizB[0].length;
            for (int fila = 0; fila < totalFilas; fila++) {
                for (int columna = 0; columna < totalColumnas; columna++) {
                    for (int k = 0; k < columnasMatrizA; k++) {
                        matrizResultado[fila][columna] += matrizA[fila][k] * matrizB[k][columna];
                    }
                }
            }
        } else {
            throw new Exception("Columnas de la primera matriz y filas de la segunda matriz no coinciden");
        }
        return matrizResultado;
    }

    /**
     * Calcula la matriz inversa por el método de Gauss Jordan de una matriz
     * cuadrada no singular
     * 
     * @param matriz recibe la matriz al que se le calculará su inversa
     * @return un arreglo que representa la matriz inversa
     * @throws Exception Genera una excepción si la matriz no cumple con ser
     *                   cuadrada o tener una determinante diferente a 0
     */
    public double[][] matrizInversaGaussJordan(double[][] matriz) throws Exception {
        double[][] matrizIdentidad = new double[matriz.length][matriz.length];
        boolean esCuadrada = matriz.length == matriz[0].length;
        inicializarMatrizIdentidad(matrizIdentidad);
        double determinante = calcularDeterminante(matriz);
        if (esCuadrada && determinante != 0) {
            int pivote = 0;
            for (int renglon = 0; renglon < matriz.length; renglon++) {
                modificarPivote(matriz, pivote, matrizIdentidad);
                realizarOperacionFundamental(matriz, pivote, matrizIdentidad);
                pivote++;
            }
        } else {
            throw new RuntimeException("No se puede calcular la inversa de esta matriz por el metodo de Gauss Jordan");
        }
        return matrizIdentidad;
    }

    /**
     * Calcula la determinante de una matriz no singular y cuadrada
     * 
     * @param matrizEntrada Recibe la matriz de orden NxN
     * @return Un número que es la determinante de la matriz
     *         obtener determinate mediante cofactores
     */
    public double calcularDeterminante(double[][] matrizEntrada) {

        double determinante = 0;
        switch (matrizEntrada.length) {

            case 2:
                // Si es una matriz de 2x2
                determinante = ((matrizEntrada[0][0] * matrizEntrada[1][1])
                        -
                        (matrizEntrada[1][0] * matrizEntrada[0][1]));
                break;
            case 3:
                // Si es una matriz 3x3
                determinante = ((matrizEntrada[0][0]) * (matrizEntrada[1][1]) * (matrizEntrada[2][2]) +
                        (matrizEntrada[1][0]) * (matrizEntrada[2][1]) * (matrizEntrada[0][2]) +
                        (matrizEntrada[2][0]) * (matrizEntrada[0][1]) * (matrizEntrada[1][2]))
                        - ((matrizEntrada[2][0]) * (matrizEntrada[1][1]) * (matrizEntrada[0][2]) +
                                (matrizEntrada[1][0]) * (matrizEntrada[0][1]) * (matrizEntrada[2][2]) +
                                (matrizEntrada[0][0]) * (matrizEntrada[2][1]) * (matrizEntrada[1][2]));
                break;
            default:
                // Matrices de mayor longitud
                for (int filas = 0; filas < matrizEntrada.length; filas++) {
                    determinante += (matrizEntrada[filas][0] * obtenerMatrizAdjunta(matrizEntrada, filas, 0));
                }

        }
        return determinante;

    }

    /**
     * La submatriz resulta de elminar la primera fila y la columna que pasa como
     * parámetro
     * 
     * @param matriz   Matriz original
     * @param filas    Numero de filas de la matriz original
     * @param columnas Numero de columnas de la matriz original
     * @param columna  Columna que se quiere eliminar, junto con la fila=0
     * @return Una matriz de N-1 x N-1 elementos
     */
    public static double[][] getSubmatriz(double[][] matriz, int filas, int columnas, int columna) {
        double[][] submatriz = new double[filas - 1][columnas - 1];
        int contador = 0;
        for (int j = 0; j < columnas; j++) {
            if (j == columna)
                continue;
            for (int i = 1; i < filas; i++)
                submatriz[i - 1][contador] = matriz[i][j];
            contador++;
        }
        return submatriz;
    }

    /**
     * @param matrizEntrada Matriz object
     * @param grado         el orden de la matriz por resolver
     * @return
     */
    public double[] calcularCramer(double[][] matrizEntrada, int grado) {
        Matriz resultado = new Matriz(matrizEntrada.length, matrizEntrada[0].length);
        resultado.setM(matrizEntrada);

        double determinanteA = resultado.calcularDeterminante(matrizEntrada);
        double resultados[] = new double[resultado.getFilas()];

        for (int coeficiente = 0; coeficiente < matrizEntrada[0].length - 1; coeficiente++) {

            Matriz matrizCoeficiente = cambiarColumnas(resultado, coeficiente, resultado.getColumnas() - 1);
            double det = resultado.calcularDeterminante(matrizCoeficiente.getM());
            double value = det / determinanteA;
            resultados[coeficiente] = value;

        }

        for (int filas = 0; filas < resultado.getFilas(); filas++) {
            resultado.setmatrizIndividual(resultados[filas], filas, resultado.getColumnas() - 1);
        }

        for (int filas = 0; filas < resultado.getFilas(); filas++) {
            for (int columnas = 0; columnas < resultado.getColumnas() - 1; columnas++) {
                if (filas == columnas) {
                    resultado.setmatrizIndividual(1, filas, columnas);
                } else {
                    resultado.setmatrizIndividual(0, filas, columnas);
                }
            }
        }
        double[] resultFinal = new double[resultado.getM().length];
        for (int i = 0; i < resultado.getM().length; i++) {
            resultFinal[i] = resultado.getM()[i][resultado.columnas - 1];
        }
        return resultFinal;

    }

    public double[][] obtenerCopiaMatriz() {

        double matrizCopia[][] = new double[this.filas][this.columnas];

        for (int i = 0; i < filas; i++) {

            double matrizPivote[] = this.matriz[i];
            int dimensionFilas = matrizPivote.length;
            matrizCopia[i] = new double[dimensionFilas];
            System.arraycopy(matrizPivote, 0, matrizCopia[i], 0, dimensionFilas);

        }
        return matrizCopia;
    }

    /**
     * Método para intercambio de columnas
     * 
     * @param entrada        objeto matriz
     * @param columnaPrimera indice para guardar posición de la primera columna
     * @param columnaSegunda indice para guardar posición de la primera columna
     * @return
     */

    public static Matriz cambiarColumnas(Matriz entrada, int columnaPrimera, int columnaSegunda) {

        Matriz resultado = new Matriz(entrada.getFilas(), entrada.getColumnas());
        resultado.setM(entrada.obtenerCopiaMatriz());

        for (int filas = 0; filas < resultado.getFilas(); filas++) {

            double cambio = resultado.getM()[filas][columnaSegunda];
            resultado.setmatrizIndividual(resultado.getM()[filas][columnaPrimera], filas, columnaSegunda);
            resultado.setmatrizIndividual(cambio, filas, columnaPrimera);

        }
        return resultado;
    }

    /**
     * Inicializa la matriz identidad colocando 1 en la diagonal principal y 0 en
     * las
     * demás celdas
     * 
     * @param matrizIdentidad matriz identidad a inicializar
     */
    private void inicializarMatrizIdentidad(double[][] matrizIdentidad) {
        for (int fila = 0; fila < matrizIdentidad.length; fila++) {
            for (int columna = 0; columna < matrizIdentidad.length; columna++) {
                if (fila == columna) {
                    matrizIdentidad[fila][columna] = 1;
                } else {
                    matrizIdentidad[fila][columna] = 0;
                }
            }

        }
    }

    /**
     * Determina el pivote (escalar) que se utilizará para realizar la operación
     * fundamental,
     * el cual consiste en multiplicar una fila de la matriz extendida por un
     * escalar
     * 
     * @param matriz          Matriz con la cual determinaremos el pivote
     * @param pivote          Escalar que se multiplicará por un renglón de la
     *                        matriz
     * @param matrizIdentidad Matriz identidad que se modificará conforme se vaya
     *                        efectuando la operación fundamental
     */
    private void modificarPivote(double[][] matriz, int pivote, double[][] matrizIdentidad) {
        double pivoteDivisor = 0;
        pivoteDivisor = matriz[pivote][pivote];
        for (int columna = 0; columna < matriz.length; columna++) {
            matriz[pivote][columna] = matriz[pivote][columna] / pivoteDivisor;
            matrizIdentidad[pivote][columna] = matrizIdentidad[pivote][columna] / pivoteDivisor;
        }
    }

    /**
     * Realiza la operación fundamental, el cual consiste en
     * multiplicar una fila de la matriz extendida por un escalar
     * 
     * @param matriz          Matriz con la cual determinaremos el pivote
     * @param pivote          Escalar que se multiplicará por un renglón de la
     *                        matriz
     * @param matrizIdentidad Matriz identidad que se modificará conforme se vaya
     *                        efectuando la operación fundamental
     */
    private void realizarOperacionFundamental(double[][] matriz, int pivote, double[][] matrizIdentidad) {
        for (int fila = 0; fila < matriz.length; fila++) {
            if (fila != pivote) {
                double escalar = matriz[fila][pivote];
                for (int columna = 0; columna < matriz.length; columna++) {
                    matriz[fila][columna] = ((-1 * escalar) * matriz[pivote][columna]) + matriz[fila][columna];
                    matrizIdentidad[fila][columna] = ((-1 * escalar) * matrizIdentidad[pivote][columna])
                            + matrizIdentidad[fila][columna];
                }
            }
        }
    }

    /**
     * Resuelve un sistema de ecuaciones usando el método de Gauss Jordan
     * 
     * @param matriz  representa a los coeficientes de las variables a resolver.
     * @param termino representa a los términos independientes de las ecuaciones
     * @return un arreglo con el resultado y el tamaño de las variables a resolver
     */
    public double[] resolverGaussJordan(double matriz[][], double termino[]) {

        // convertir la matriz aumentada en la matriz identidad
        for (int i = 0; i <= termino.length - 1; i++) {
            double pivote, fila = 0;
            pivote = matriz[i][i];// se seleciona el pivote
            // se pasa a convertir en 1 al pivote selecionado
            for (int indice = 0; indice <= termino.length - 1; indice++) {
                matriz[i][indice] = ((matriz[i][indice]) / pivote);
            }
            termino[i] = ((termino[i]) / pivote);

            for (int x = 0; x <= termino.length - 1; x++) {
                if (i != x) {

                    for (int columna = 0; columna <= termino.length - 1; columna++) {
                        // se hace cero a todos los elementos de la colunma que no sean el pivote
                        matriz[x][columna] = matriz[x][columna] - fila * matriz[i][columna];

                    }
                    termino[x] = termino[x] - fila * termino[i];
                }
            }
        }
        return termino;
    }

    /**
     * 
     * @param entrada   objeto tipo Matriz
     * @param cofactorA
     * @param cofactorB
     * @return
     */
    public static double obtenerMatrizAdjunta(double[][] entrada, int cofactorA, int cofactorB) {
        Matriz matrizEvaluar = new Matriz();
        matrizEvaluar.setM(entrada);
        double resultado = 0;
        int indiceA, indiceB;
        for (int i = 0; i < matrizEvaluar.getFilas(); i++) {
            indiceA = (i < cofactorA) ? i : i + 1;
            for (int l = 0; l < matrizEvaluar.getFilas(); l++) {
                indiceB = (l < cofactorB) ? l : l + 1;
                double valorCofactor = matrizEvaluar.obtenerCopiaMatriz()[indiceA][indiceB];
                matrizEvaluar.setmatrizIndividual(valorCofactor, i, l);
            }
        }
        resultado = (int) Math.pow(-1, cofactorA + cofactorB)
                * matrizEvaluar.calcularDeterminante(matrizEvaluar.getM());
        return resultado;
    }

    public double[][] getM() {
        return matriz;
    }

    public void setM(double[][] matriz) {
        this.matriz = matriz;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setmatrizIndividual(double value, int filas, int columnas) {

        this.matriz[filas][columnas] = value;

    }

}
