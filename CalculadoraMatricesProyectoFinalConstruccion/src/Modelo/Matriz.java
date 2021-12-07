package Modelo;

public class Matriz {
    int FILAS_MAXIMAS = 5;
    int COLUMNAS_MAXIMAS = 5;
    private int filas;
    private int columnas;
    private double[][] matriz;

    public Matriz() {
        this.filas = FILAS_MAXIMAS;
        this.columnas = COLUMNAS_MAXIMAS;
        this.matriz = new double[FILAS_MAXIMAS][COLUMNAS_MAXIMAS];
    }

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new double[filas][columnas];
    }

    public void insertar(int fila, int columna, int valor) {
        this.matriz[fila][columna] = valor;
    }

    /**
     * Metodo que suma dos matrices si ambos son de forma MxN
     * 
     * @param matrizA primera matriz de orden MxN
     * @param matrizB segunda matriz de orden MxN
     * @return matrizResultado: una matriz de orden MxN
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

    public double calcularDeterminante(double[][] matriz) throws Exception {
        double determinante;
        boolean esCuadrada = verificarMatrizCuadrada(matriz);
        if (esCuadrada) {
            if (matriz.length == 2) {
                determinante = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
                ;
                return (double) determinante;
            }
            double suma = 0;
            int totalRenglones = matriz.length;
            for (int fila = 0; fila < totalRenglones; fila++) {
                double[][] matrizResultado = new double[matriz.length - 1][matriz.length - 1];
                for (int columna = 0; columnas < totalRenglones; columna++) {
                    if (columna != fila) {
                        for (int k = 1; k < totalRenglones; k++) {
                            int indice = -1;
                            if (columna < fila) {
                                indice = columna;
                            } else if (columna > fila) {
                                indice = columna - 1;
                                matrizResultado[indice][k - 1] = matriz[columna][k];
                            }
                        }
                    }
                }
                if (fila % 2 == 0) {
                    suma += matriz[fila][0] * calcularDeterminante(matrizResultado);
                } else {
                    suma -= matriz[fila][0] * calcularDeterminante(matrizResultado);
                }
            }
            return (double) suma;
        } else {
            throw new Exception("Se necesita una matriz cuadrada para calcular la determinante");
        }
    }

    private boolean verificarMatrizCuadrada(double[][] matriz) {
        boolean esCuadrada = true;
        int filas = matriz.length;
        int columnas = matriz[0].length;
        if (filas != columnas) {
            esCuadrada = false;
        }
        return esCuadrada;
    }

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

    private void modificarPivote(double[][] matriz, int pivote, double[][] matrizIdentidad) {
        double pivoteDivisor = 0;
        pivoteDivisor = matriz[pivote][pivote];
        for (int columna = 0; columna < matriz.length; columna++) {
            matriz[pivote][columna] = matriz[pivote][columna] / pivoteDivisor;
            matrizIdentidad[pivote][columna] = matrizIdentidad[pivote][columna] / pivoteDivisor;
        }
    }

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

    public double[] resolverGaussJordan(double matriz[][], double termino[]) {

        // convertir la matriz aumentada en la matriz identidad
        for (int i = 0; i <= termino.length - 1; i++) {
            double pivote, fila = 0;
            pivote = matriz[i][i];// se seleciona el pivote
            // se pasa a convertir en 1 al pivote seleionado
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
        return termino;// retorna terminos
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

}
