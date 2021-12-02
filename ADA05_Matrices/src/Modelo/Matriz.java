/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.Arrays;
/**
 *
 * @author dado0
 */
public class Matriz {
    int filas;
    int columnas;
    double [ ][ ]m;
/**Dimensiones de la matriz
 * 
 * @return m matriz
 */  
    public double [ ][ ] getM(){
        return m;
    }
    public void setM(double [ ][ ] m){
        this.m=m;
    }
 /*Métodos para las filas
    *
    */   
    public int getFilas(){
        return filas;
    }
    
    public void setFilas(int filas){
        this.filas= filas;
    }
/**Métodos para las columnas
 * 
 * @return 
 */
    public int getColumnas(){
        return columnas;
    }
    
    public void setColumnas(int columnas){
        this.columnas= columnas;
    }
 /**Constructores
  * 
  */
    public Matriz(){
    }
    
    public Matriz(int filas, int columnas){
        this.filas=filas;
        this.columnas=columnas;
        this.m=new double[filas][columnas];    
    }
    
    /**Métodos
     * 
     * @param fila valores de la fila
     * @param columna valores de la columna
     * @param dato máximo valor de la matriz
     */
    public void insertar(int fila, int columna, int dato){
        this.m [fila][columna]=dato;    
    }
    public void insertar(int fila, int columna, double dato){
        this.m [fila][columna]=dato;    
    }
    
    public String imprimir(){
        String s="";
        for(int f=0;f<this.filas;f++){
            s=s+"[ ";
            for(int c=0;f<this.columnas;c++){
                
            s=s+String.valueOf(m[f][c])+ " ";
            
             }
            s=s+"]\n";

    }
       return s;    
    }
 /**Operaciones
  * 
  * @return 
  */
    public int esMatrizCuadrada(){
        if(this.filas==this.columnas){
        return this.filas;  
    }else{
        return 0;  
        }
    }
 /**Suma
  * 
  * @param a
  * @param b
  * @return 
  */
    public double [][] sumaMatrices(double [ ][ ] a,double [ ][ ] b ){
        double [][] sumaResultado = new double [a.length][a[0].length];
      if ((a.length == b.length) && (a[0].length==b[0].length)){
          for (int i=0; i< a.length; i++) {
              for (int j=0; j < a[i].length; j++) {				
                  sumaResultado[i][j]=a[i][j]+b[i][j];	
                    
              }
          }
      }
       return sumaResultado; 
    }
 /**Resta
  * 
  * @param a
  * @param b
  * @return 
  */
    public double [][] restaMatrices(double [ ][ ] a,double [ ][ ] b ){
        double [][] restaResultado = new double [a.length][a[0].length];
      if ((a.length == b.length) && (a[0].length==b[0].length)){
          for (int i=0; i< a.length; i++) {
              for (int j=0; j < a[i].length; j++) {				
                  restaResultado[i][j]=a[i][j]-b[i][j];	
                    
              }
          }
      }
       return restaResultado; 
    }
    
/**Transpuesta
 * 
 * @param mat
 * @return 
 */    
    public double [ ][ ] transpuesta(double [ ][ ] mat){
        double [ ][ ] matrizT=new double [mat[0].length][mat.length];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                matrizT[j][i]=mat[i][j];
                
       
             }
        }
        return matrizT;
    }
/**Multiplicación
 * 
 * @param a
 * @param b
 * @return 
 */  
     public double [ ][ ] multiplicacion(double [ ][ ] a,double [ ][ ] b ){
        double [ ][ ] c=new double [a.length][b[0].length];
        if(a[0].length==b.length){
             for(int i=0;i<a.length;i++){
                 for(int j=0;j<b[0].length;j++){
                      for(int k=0;k<a[0].length;k++){
                          //Multiplica
                          c[i][j]+=a[i][k]*b[k][j];
                      }
                 }
             }
        }
        return c;
     }
 
/**Determinante>{}
 * 
 * @param matriz
 * @return 
 */
     public double calcularDeterminante(double [ ][ ] matriz){
         double det;
         //Si es matriz de 2x2
          if(matriz.length==2){
              det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
              return (double) det;
          }    
      //Cuando es matriz de orden superior
      double suma=0;
       for(int i=0;i<matriz.length;i++){
           double[ ][ ] matrizResultado=new double [matriz.length-1][matriz.length-1];
                 for(int j=0;j<matriz.length;j++){
                     if(j!=i){
                         for(int k=1;k<matriz.length;k++){
                             int indice=-1;
                             if(j<i){ 
                                 indice=j;
                             }else if(j>i){ 
                                 indice=j-1; }
                                 matrizResultado[indice][k-1]=matriz[j][k];    
                         }    
                     }
                 }
                 if(i%2==0){
                     suma+=matriz[i][0]*calcularDeterminante(matrizResultado);
                 }else{
                     suma-=matriz[i][0]*calcularDeterminante(matrizResultado);   
                 }    
       }
        return (double) suma;
     }
/**Inversa
 * 
 * @param matriz
 * @return 
 */
     public double [ ][ ] matrizInversa(double [ ][ ] matriz){
         double det=1/calcularDeterminante(matriz);
         double [ ][ ] nuevaMatriz=matrizAdjunta(matriz);
         return multiplicarMatrices(det,nuevaMatriz);
     }
     
     private double [ ][ ] matrizAdjunta(double [ ][ ] matriz){
         return this.transpuesta(matrizCofactores(matriz));
     }
       
     private double [ ][ ] multiplicarMatrices(double n,double [ ][ ] matriz){
         for(int i=0;i<matriz.length;i++){ 
             for(int j=0;j<matriz.length;j++){ 
                 matriz[i][j]*=n; 
             }    
         }
         return matriz; 
     }
     
     public double [ ][ ] multiplicarEscalar(double n,double [ ][ ] matriz){
         double [ ][ ] nuevaMatriz= new double [matriz.length][matriz.length] ;
         for(int i=0;i<matriz.length;i++){ 
             for(int j=0;j<matriz.length;j++){ 
                 nuevaMatriz[i][j]= matriz[i][j]*n; 
             }    
         }
         return nuevaMatriz; 
     }
     private double [ ][ ] matrizCofactores(double [ ][ ] matriz){
         double [ ][ ] nuevaMatriz= new double [matriz.length][matriz.length] ;
         for(int i=0;i<matriz.length;i++){ 
             for(int j=0;j<matriz.length;j++){ 
                 double [ ][ ] det= new double [matriz.length-1][matriz.length-1] ;
                 double detValor;
                 for(int k=0;k<matriz.length;k++){
                     if(k!=i){
                         for(int l=0;l<matriz.length;l++){
                              if(k!=i){
                                  //Condicional ? primero es cuando es verdadero : segundo es cuando es falso
                                  int indice1=k<i ? k : k-1;
                                  int indice2=l<j ? l : l-1;
                                  det[indice1][indice2]=matriz[k][l];
                              } 
                             
                         } 
                         
                     
                     } 
                 
                 } 
                 detValor=calcularDeterminante(det);
                 nuevaMatriz[i][j]=detValor*(double)Math.pow(-1, i+j+2);
             }    
         }
         return nuevaMatriz; 
     }
     
     
     public double [][] matrizInversaGaussJordan(double [][]matriz){
         double[][] matrizIdentidad = new double[matriz.length][matriz.length];
         inicializarMatrizIdentidad(matrizIdentidad);
         double determinante = calcularDeterminante(matriz);
         if(determinante != 0){
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
     
     }
     
     
     
