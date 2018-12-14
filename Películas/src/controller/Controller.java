
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model;
import view.View;

public class Controller {

    Model model;
    View view;
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            if (e.getSource() == view.jb_primero) {
                try {
                    jb_primero_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.jb_anterior) {
                try {
                    jb_anterior_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.jb_siguiente) {
                try {
                    jb_siguiente_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.jb_ultimo) {
                try {
                    jb_ultimo_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(e.getSource() == view.jb_eliminar){
                try {
                    jb_eliminar();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == view.jb_insertar){
                try {
                    jb_insertar();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == view.jb_modificar){
                try {
                    jb_modificar();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == view.jb_nuevo){
                jb_nuevo();
            }

        }

    };

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initComponents();
        setActionListener();
        initDB();
    }

    public void initDB(){
        model.conectarDB();
        view.jtf_idp.setText(model.getID());
        view.jtf_nomp.setText(model.getNomP());
    }

    public void initComponents() {
        view.setLocationRelativeTo(null);
        view.setTitle("Agenda MVC");
        view.setVisible(true);
    }


    public void setActionListener() {
        view.jb_primero.addActionListener(actionListener);
        view.jb_anterior.addActionListener(actionListener);
        view.jb_siguiente.addActionListener(actionListener);
        view.jb_ultimo.addActionListener(actionListener);
        view.jb_eliminar.addActionListener(actionListener);
        view.jb_insertar.addActionListener(actionListener);
        view.jb_modificar.addActionListener(actionListener);
        view.jb_nuevo.addActionListener(actionListener);
    }

 
    private void jb_primero_actionPerformed() throws SQLException {
        System.out.println("Action del boton jb_primero"); //Mensaje que verfoque que funcione el boton jb_primero
        model.primerRegistro(); 
        view.jtf_idp.setText(model.getID());
        view.jtf_nomp.setText(model.getNomP());
    }


    private void jb_anterior_actionPerformed() throws SQLException {
        System.out.println("boton jb_anterior"); //Mensaje que verfoque que funcione el boton jb_anterior
        model.anteriorRegistro();
        view.jtf_idp.setText(model.getID());
        view.jtf_nomp.setText(model.getNomP());
    }


    private void jb_ultimo_actionPerformed() throws SQLException {
        System.out.println("boton jb_ultimo"); //Mensaje que verfoque que funcione el boton jb_ultimo
        model.ultimoRegistro();
        view.jtf_idp.setText(model.getID());
        view.jtf_nomp.setText(model.getNomP());
    }
    
    private void jb_siguiente_actionPerformed() throws SQLException {
        System.out.println("boton jb_siguiente"); //Mensaje que verfoque que funcione el boton jb_siguiente
        model.siguienteRegistro();
        view.jtf_idp.setText(model.getID());
        view.jtf_nomp.setText(model.getNomP());
    }
    private void jb_eliminar() throws SQLException {
            System.out.println("boton jb_eliminar"); //Mensaje que verfoque que funcione el boton jb_eliminar
            System.out.println(model.getID());
            model.eliminarRegistro(model.getNomP());
            JOptionPane.showMessageDialog(view, "eliminado correctamente"); //Se elimino el registro en la base de datos
            jb_primero_actionPerformed();
        }

        private void jb_insertar() throws SQLException {
            System.out.println(" boton jb_insertar"); //Mensaje que verfoque que funcione el boton jb_insertar
            model.setID(view.jtf_idp.getText());
            model.setNomP(view.jtf_nomp.getText());
            model.insertarRegistro(model.getID(),model.getNomP());
            JOptionPane.showMessageDialog(view, "guardado correctamente"); //Se guardo el registro en la base de datos
            jb_primero_actionPerformed();
        }

        private void jb_modificar() throws SQLException {
            System.out.println("boton jb_modificar"); //Mensaje que verfoque que funcione el boton jb_modificar
            model.modificarRegistro(view.jtf_idp.getText(), view.jtf_nomp.getText());
            JOptionPane.showMessageDialog(view, " actualizado correctamente"); //Se actualizo el registro en la base de datos
            jb_primero_actionPerformed();
        }

        private void jb_nuevo() {
            System.out.println(" boton jb_nuevo"); //Mensaje que verfoque que funcione el boton jb_nuevo
            model.setID(null);
            model.setNomP(null);
            view.jtf_nomp.setText(model.getID());
            view.jtf_idp.setText(model.getNomP());
            JOptionPane.showMessageDialog(view, "¿Quieres Ingresar Un Nuevo Registro?");
        }
}
