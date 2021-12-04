/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadoramatricesproyectofinalconstruccion;

import Vista.vistaCalculadora;
import Vista.vistaCalculadora;
import controlador.ControladorCalculadora;

/**
 *
 * @author jairo
 */
public class CalculadoraMatricesProyectoFinalConstruccion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //vistaCalculadora vista = new vistaCalculadora();
        vistaCalculadora vista = new vistaCalculadora();
        ControladorCalculadora controlador =new ControladorCalculadora(vista);
        vista.setVisible(true);
    }
    
}
