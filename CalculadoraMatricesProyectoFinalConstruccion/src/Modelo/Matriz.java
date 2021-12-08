package Modelo;

import javax.management.RuntimeErrorException;

public class Matriz {
    private int filas;
    private int columnas;
    private double [][]matriz;

    public Matriz(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new double[filas][columnas];
    }

    public void insertar(int fila, int columna, int valor){
        this.matriz [fila][columna]=valor;
    }

    /**
     * Método que suma dos matrices si ambos son de forma mxn
     * @param matrizA primera Matriz que se va a sumar de orden MxN
     * @param matrizB segunda Matriz que se va a sumar de orden MxN
     * @return matrizResultado: una matriz de orden MxN con cada celda sumada
     */
    public double [][] sumarMatrices(double [ ][ ] matrizA,double [ ][ ] matrizB ){
        double [][] matrizResultado = new double [matrizA.length][matrizA[0].length];
        boolean mismoNumeroFilas = matrizA.length == matrizB.length;
        boolean mismoNumeroColumnas = matrizA[0].length == matrizB[0].length;
        if (mismoNumeroFilas && mismoNumeroColumnas){
            int totalFilas = matrizA.length;
            int totalColumnas = matrizA[0].length;
            for (int fila=0; fila< totalFilas; fila++) {
                for (int columna=0; columna < totalColumnas; columna++) {				
                  matrizResultado[fila][columna]=matrizA[fila][columna]+matrizB[fila][columna];	
                    
                }
            }
        } 
       return matrizResultado; 
    }

    /**
     * Multiplica la matriz por un escalar C
     * @param escalar Escalar por el cual se va a multiplicar la matriz
     * @param matriz Matriz a la cual se va a multiplicar
     * @return nuevaMatriz: una matriz conde cada una de sus celdas sea multplicada por escalar C
     */
    public double [ ][ ] multiplicacionPorEscalar(double escalar,double [ ][ ] matriz){
        double [ ][ ] nuevaResultado= new double [matriz.length][matriz[0].length] ;
        int totalFilas = matriz.length;
        int totalColumnas = matriz[0].length;
        for(int fila=0;fila<totalFilas;fila++){ 
            for(int columna=0;columna<totalColumnas;columna++){ 
                nuevaResultado[fila][columna]= matriz[fila][columna]*escalar; 
            }    
        }
        return nuevaResultado; 
    }

    /**
     * Multiplica dos matrices que deben ser de orden MxN y NxP
     * @param matrizA primera matriz de orden MxN
     * @param matrizB segunda matriz de orden NxP
     * @return matrizResultado: Una matriz de orden MxP
     */
    public double [ ][ ] multiplicarMatrices(double [ ][ ] matrizA,double [ ][ ] matrizB ){
        double [ ][ ] matrizResultado=new double [matrizA.length][matrizB[0].length];
        int columnasMatrizA = matrizA[0].length;
        int filasMatrizB = matrizB.length;
        if(columnasMatrizA==filasMatrizB){
            int totalFilas = matrizA.length;
            int totalColumnas = matrizA[0].length;
            for(int fila=0;fila<totalFilas;fila++){
                for(int columna=0;columna<totalColumnas;columna++){
                    for(int k=0;k<totalColumnas;k++){
                        matrizResultado[fila][columna]+=matrizA[fila][k]*matrizB[k][columna];
                    }
                }
            }
        }
        return matrizResultado;
    }

    /**
     * Método que devuelve la inversa de una matriz por el Método de Gauss-Jordan
     * @param matriz es la matriz de entrada, debe ser de orden NxN y No singular
     * @return matrizIdentidad: 
     */
    public double [][] matrizInversaGaussJordan(double [][]matriz){
        double[][] matrizIdentidad = new double[matriz.length][matriz.length];
        boolean esCuadrada= matriz.length == matriz[0].length;
        inicializarMatrizIdentidad(matrizIdentidad);
        double determinante = calcularDeterminante(matriz);
        if(esCuadrada && determinante != 0){
            int pivote = 0;
            for(int renglon = 0; renglon<matriz.length; renglon++){
                modificarPivote(matriz, pivote, matrizIdentidad);
                realizarOperacionFundamental(matriz, pivote, matrizIdentidad);
                pivote++;
            }
        }else{
            System.out.println("No se puede calcular la inversa de esta matriz por el metodo de Gauss Jordan");
        }
        return matrizIdentidad;
    }

    /**
     * Calcula la determinante de una matriz de orden NxN
     * @param matriz 
     * @return
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
    
    private void inicializarMatrizIdentidad(double [][]matrizIdentidad){
        for(int fila=0; fila<matrizIdentidad.length; fila++){
            for(int columna=0; columna<matrizIdentidad.length; columna++){
                if(fila == columna){
                    matrizIdentidad[fila][columna] = 1;
                }else{
                    matrizIdentidad[fila][columna] = 0;
                }
            }
            
        }
    }

    private void modificarPivote(double[][] matriz, int pivote, double[][]matrizIdentidad){
        double pivoteDivisor = 0;
        pivoteDivisor = matriz[pivote][pivote];
        for(int columna=0; columna<matriz.length; columna++){
            matriz[pivote][columna] = matriz[pivote][columna]/pivoteDivisor;
            matrizIdentidad[pivote][columna]= matrizIdentidad[pivote][columna]/pivoteDivisor;
        }
    }
    private void realizarOperacionFundamental(double[][]matriz, int pivote, double[][] matrizIdentidad){
        for(int fila=0; fila<matriz.length; fila++){
            if(fila != pivote){
                double escalar = matriz[fila][pivote];
                for(int columna=0; columna<matriz.length; columna++){
                    matriz[fila][columna]=((-1*escalar)*matriz[pivote][columna])+matriz[fila][columna];
                    matrizIdentidad[fila][columna]=((-1*escalar)*matrizIdentidad[pivote][columna])+matrizIdentidad[fila][columna];
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
                    fila = matriz[x][i];
                   
                    for (int columna = 0; columna<= termino.length - 1; columna++) {
                        // se hace cero a todos los elementos de la colunma que no sean el pivote
                        matriz[x][columna] = matriz[x][columna] - fila * matriz[i][columna];

                    }
                    termino[x] = termino[x] - fila * termino[i];         
                }
            }
        }
        return termino;// retorna terminos

    }

    

    

    public double [ ][ ] getM(){
        return matriz;
    }
    public void setM(double [ ][ ] matriz){
        this.matriz=matriz;
    }
  
    public int getFilas(){
        return filas;
    }
    
    public void setFilas(int filas){
        this.filas= filas;
    }

    public int getColumnas(){
        return columnas;
    }
    
    public void setColumnas(int columnas){
        this.columnas= columnas;
    }
    
}
 

