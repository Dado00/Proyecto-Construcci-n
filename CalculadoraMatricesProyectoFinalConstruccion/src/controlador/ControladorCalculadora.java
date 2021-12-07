/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Vista.vistaCalculadora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jairo
 */
public class ControladorCalculadora implements ActionListener{
    String TRANSPUESTA = "transpuesta";
    String DETERMINANTE = "determinante";
    String INVERSA = "inversa";
    String MULTIPLICAR = "multiplicar";
    String SUMA = "suma";
    String METODO_GAUSS = "metodo_gauss";

    //Matriz modelo;
    vistaCalculadora vista;

    public ControladorCalculadora(vistaCalculadora vista) {
        this.vista = vista;
        //modelo = Matriz;
        
        this.vista.getCalcularButton().addActionListener(this);
        this.vista.getTranspuestaA().addActionListener(this);
        this.vista.getDeterminanteA().addActionListener(this);
        this.vista.getInversaA().addActionListener(this);
        this.vista.getMultiplicaA().addActionListener(this);
        this.vista.getSuma().addActionListener(this);
        this.vista.getMetodoGauss().addActionListener(this);
        
        
        this.vista.setAlwaysOnTop(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.vista.getCalcularButton() == e.getSource()){
            //llamar a modelo para realizar la operacion
            switch(this.vista.getOperacionSeleccionada().getText()){
                case  "transpuesta":
                    
                    this.vista.getOperacionSeleccionada().setText(null);
                    break;
                case  "determinante":
                    
                    this.vista.getOperacionSeleccionada().setText(null);
                    break;
                case  "inversa":
                    
                    this.vista.getOperacionSeleccionada().setText(null);
                    break;
                case  "multiplicar":
                    
                    this.vista.getOperacionSeleccionada().setText(null);
                    break;
                case  "suma":
                    
                    this.vista.getOperacionSeleccionada().setText(null);
                    break;
                case  "metodo_gauss":
                    
                    this.vista.getOperacionSeleccionada().setText(null);
                    break;
                default :
                    JOptionPane.showMessageDialog(null, "Seleccione una operacion");
                    
                    
            }
        }
        
        if(this.vista.getTranspuestaA() == e.getSource()){

            this.vista.getOperacionSeleccionada().setText(this.TRANSPUESTA);
        }
        
        if(this.vista.getDeterminanteA() == e.getSource()){
            this.vista.getOperacionSeleccionada().setText(this.DETERMINANTE);
        }
        
        if(this.vista.getInversaA() == e.getSource()){
            this.vista.getOperacionSeleccionada().setText(this.INVERSA);
        }
        
        if(this.vista.getMultiplicaA() == e.getSource()){
            this.vista.getOperacionSeleccionada().setText(this.MULTIPLICAR);
        }
        
        if(this.vista.getSuma() == e.getSource()){
            this.vista.getOperacionSeleccionada().setText(this.SUMA);
        }
        
        if(this.vista.getMetodoGauss() == e.getSource()){
            this.vista.getOperacionSeleccionada().setText(this.METODO_GAUSS);
        }
        
    }
    
    
}
