package Modelo;

public class Matriz { 
    public final int FILAS_MAXIMAS = 5;  //Número máximo de filas que puede tener una matriz 
    public final int COLUMNAS_MAXIMAS = 5; //Número máximo de columnas que puede tener una matriz
    private int filas;
    private int columnas;
    private double[][] matriz;
    
    public Matriz() {
        this.filas = FILAS_MAXIMAS;
        this.columnas = COLUMNAS_MAXIMAS;
        this.matriz = new double[FILAS_MAXIMAS][COLUMNAS_MAXIMAS];
    }
    /**
     * Crea una matriz recibiendo como parámetros el número de filas y número de columnas de la matriz.
     * @param filas Número de filas
     * @param columnas Número de columnas
     */
    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new double[filas][columnas];
    }
    /**
     * Inserta el valor de tipo double en la celda que se le indique.
     * @param fila Fila en la que se va a insertar
     * @param columna Columna en la que se va a insertar
     * @param valor Número que se va a insertar
     */
    public void insertar(int fila, int columna, int valor) {
        this.matriz[fila][columna] = valor;
    }

    /**
     * Suma dos matrices si ambas son de forma MxN
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
     * Multiplica una matriz de orden MxN por un escalar C
     * @param escalar recibe el escalar C
     * @param matriz recibe la matriz de orden MxN a multiplicar por C
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
     * Multiplica dos matrices si la primera matriz es de orden MxN y la segunda es de orden NxP
     * @param matrizA Primera matriz de orden MxN
     * @param matrizB Segunda matriz de orden NxP
     * @return Una matriz de orden MxP con la multiplicación efectuada
     * @throws Exception Genera una excepción con un mensaje indicando que las matrices no pueden ser multiplicadas
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
     * Calcula la matriz inversa por el método de Gauss Jordan de una matriz cuadrada no singular
     * @param matriz recibe la matriz al que se le calculará su inversa
     * @return un arreglo que representa la matriz inversa
     * @throws Exception Genera una excepción si la matriz no cumple con ser cuadrada o tener una determinante diferente a 0
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
     * @param matriz Recibe la matriz de orden NxN
     * @return Un número que es la determinante de la matriz
     */
 public static double determinante (double [][] matriz)
	{
            //Validar que la matriz no sea nula; debe tenga una longitud mayor a 0
		assert matriz != null;
		assert matriz.length>0;
		assert matriz.length == matriz[0].length;
		
		double determinante = 0.0;
		
		int filas = matriz.length;
		int columnas = matriz[0].length;
		
		// Si la matriz es 1x1, el determinante es el elemento de la matriz
		if ((filas==1) && (columnas==1))
			return matriz[0][0];
		//Valor para ir cambiando el signo de los coeficientes
		int signo=1;
		for (int columna=0;columna<columnas;columna++)
		{
	// Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
			double[][] submatriz = getSubmatriz(matriz, filas, columnas,
					columna);
			determinante = determinante + signo*matriz[0][columna]*determinante(submatriz);
			signo*=-1;
		}
		
		return determinante;
	}

	/**
	 *La submatriz resulta de elminar la primera fila y la columna que pasa como parámetro
	 * @param matriz Matriz original
	 * @param filas Numero de filas de la matriz original
	 * @param columnas Numero de columnas de la matriz original
	 * @param columna Columna que se quiere eliminar, junto con la fila=0
	 * @return Una matriz de N-1 x N-1 elementos
	 */
	public static double[][] getSubmatriz(double[][] matriz, int filas,int columnas, int columna) {
		double [][] submatriz = new double[filas-1][columnas-1];
		int contador=0;
		for (int j=0;j<columnas;j++)
		{
			if (j==columna) continue;
			for (int i=1;i<filas;i++)
				submatriz[i-1][contador]=matriz[i][j];
			contador++;
		}
		return submatriz;
	}
    
    /**
     * Verifica si una matriz es de orden NxN, comparando su número de filas con el número de sus columnas
     * @param matriz recibe una matriz.
     * @return true si es una matriz de orden NxN, false de otro modo
     */
    private boolean verificarMatrizCuadrada(double[][] matriz) {
        boolean esCuadrada = true;
        int filas = matriz.length;
        int columnas = matriz[0].length;
        if (filas != columnas) {
            esCuadrada = false;
        }
        return esCuadrada;
    }
    /**
     * Inicializa la matriz identidad colocando 1 en la diagonal principal y 0 en las 
     * demás celdas
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
     * Determina el pivote (escalar) que se utilizará para realizar la operación fundamental, 
     * el cual consiste en multiplicar una fila de la matriz extendida por un escalar
     * @param matriz Matriz con la cual determinaremos el pivote 
     * @param pivote Escalar que se multiplicará por un renglón de la matriz
     * @param matrizIdentidad Matriz identidad que se modificará conforme se vaya efectuando la operación fundamental
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
     * @param matriz Matriz con la cual determinaremos el pivote 
     * @param pivote Escalar que se multiplicará por un renglón de la matriz
     * @param matrizIdentidad Matriz identidad que se modificará conforme se vaya efectuando la operación fundamental
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
     * @param matriz representa a los coeficientes de las variables a resolver.
     * @param termino representa a los términos independientes de las ecuaciones
     * @return  un arreglo con el resultado y el tamaño de las variables a resolver 
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
