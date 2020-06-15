/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.dao.TelefonoDao;
import ec.edu.ups.idao.ITelefonoDao;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.vista.VentanaCrearTelefono;
import ec.edu.ups.vista.VentanaListarTelefono;
import ec.edu.ups.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControladorTelefono implements ActionListener{
    ITelefonoDao telefonoDao;
    VentanaCrearTelefono ventanaTelefono;
    VentanaListarTelefono ventana= new VentanaListarTelefono();
    Telefono telefono;

    public ControladorTelefono(TelefonoDao telefonoDao, VentanaCrearTelefono ventanaTelefono) {
        this.telefonoDao = telefonoDao;
        this.ventanaTelefono = ventanaTelefono;
        this.ventanaTelefono.btnRegistrar.addActionListener(this);
        this.ventanaTelefono.btnListar.addActionListener(this);;
    }

    public void crearTelefono(){
        
        telefono=ventanaTelefono.crearTelefono();
        System.out.println(telefono);
        if(telefono!=null){
            JOptionPane.showMessageDialog(null, "Telefono registrado correctamente");
            telefonoDao.create(telefono);
        }else{
            JOptionPane.showMessageDialog(null, "Error en el registro");
        }
    }
    
    public void listarTelefonos(){
        List<Telefono> telefonos;
        telefonos = telefonoDao.listar();
        if(!telefonos.isEmpty()){
            ventana.areaListar.setText(telefonos.toString());
        }else{
            ventana.areaListar.setText("Agenda Vacia");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(ventanaTelefono.btnRegistrar)){
            crearTelefono();
        }
        if(e.getSource().equals(ventanaTelefono.btnListar)){
            listarTelefonos();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
        }
    }
    
    public void iniciar(){
        VentanaPrincipal op = new VentanaPrincipal();
        op.setVisible(true);
        op.setLocationRelativeTo(null);
    }
    
    
    
}
