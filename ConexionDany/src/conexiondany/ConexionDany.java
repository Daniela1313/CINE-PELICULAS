package conexiondany;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;

public class ConexionDany extends JFrame implements ActionListener{
 
    JLabel Codigo, Nombre, Caducidad, Precio, Logo;
    JTextField Cod, Nom, Cad, Prec;
    JButton insertar, borrar, Actualizar, Seleccionar;
    ImageIcon img;
    
    Controlador ctr=new Controlador();
    ConexionDany(){
        setTitle("Inventario");
        setSize(470,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        Codigo=new JLabel("Codigo");
        Codigo.setBounds(10,10,100,20);
        Codigo.setForeground(Color.white);
        add(Codigo);
        
        Nombre=new JLabel("Nombre");
        Nombre.setBounds(10,40,100,20);
        Nombre.setForeground(Color.white);
        add(Nombre);
        
        Caducidad=new JLabel("Caducidad");
        Caducidad.setBounds(10,70,100,20);
        Caducidad.setForeground(Color.white);
        add(Caducidad);
        
        Precio=new JLabel("Precio");
        Precio.setBounds(10,100,100,20);
        Precio.setForeground(Color.white);
        add(Precio);
        
        Cod=new JTextField();
        Cod.setBounds(200,10,230,20);
        add(Cod);
        
        Nom=new JTextField();
        Nom.setBounds(200,40,230,20);
        add(Nom);
        
        Cad=new JTextField();
        Cad.setBounds(200,70,230,20);
        add(Cad);
        
        Prec=new JTextField();
        Prec.setBounds(200,100,230,20);
        add(Prec);
        
        insertar=new JButton("Insertar");
        insertar.setBounds(25,130,90,25);
        add(insertar);
        insertar.addActionListener(this);
        
        borrar=new JButton("Borrar");
        borrar.setBounds(130,130,90,25);
        add(borrar);
        borrar.addActionListener(this);
        
        Actualizar=new JButton("Actualizar");
        Actualizar.setBounds(235,130,90,25);
        add(Actualizar);
        Actualizar.addActionListener(this);
        
        Seleccionar=new JButton("Seleccionar");
        Seleccionar.setBounds(340,130,90,25);
        add(Seleccionar);
        Seleccionar.addActionListener(this);
        
        Logo=new JLabel();
        Logo.setBounds(0,0,470,200);
        img=new ImageIcon(getClass().getResource("/fondo.jpg"));
        Logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_SMOOTH)));
        add(Logo);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Insertar")) {
            try{
                ctr.setCodigo(Integer.parseInt(Cod.getText()));
                ctr.setNombre(Nom.getText());
                ctr.setCaducidad(Cad.getText());
                ctr.setPrecio(Double.parseDouble(Prec.getText()));
                ctr.RealizarInsert();
                Cad.setText("");
                Nom.setText("");
                Cad.setText("");
                Prec.setText("");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "No has cumplido con los requisitos requeridos");            }
        }
        if (ae.getActionCommand().equals("Seleccionar")) {
            try {
                ctr.setCodigo(Integer.parseInt(Cod.getText()));
                ResultSet rs=ctr.Buscar();
                while(rs.next()){
                   Nom.setText(rs.getString("NOMBRE"));
                   Cad.setText(rs.getString("CADUCIDAD"));
                   Prec.setText(rs.getString("PRECIO"));
                }
                if (Nom.getText()=="") {
                    JOptionPane.showMessageDialog(null, "No existen registros con el criterio de busqueda");
                }else{
                    JOptionPane.showMessageDialog(null, "La busqueda se realizó con exito");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConexionDany.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ae.getActionCommand().equals("Actualizar")) {
           try{
               ctr.setCodigo(Integer.parseInt(Cod.getText()));
               ctr.setNombre(Nom.getText());
               ctr.setCaducidad(Cad.getText());
               ctr.setPrecio(Double.parseDouble(Prec.getText()));
               ctr.Modificar();
           }
           catch(Exception ex){
               JOptionPane.showMessageDialog(null, "No has cumplido con los requisitos requeridos");
           }
        }
        if (ae.getActionCommand().equals("Borrar")) {
            try{
            ctr.setCodigo(Integer.parseInt(Cod.getText()));
            ResultSet rs=ctr.Buscar();
            while(rs.next()){
                   Nom.setText(rs.getString("NOMBRE"));
            }
             if (Nom.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El registro solicitado para borrar no existe");
                }else{
                 JOptionPane.showMessageDialog(null, "El registro se ha borrado correctamente");
             }
            Cod.setText("");
            Nom.setText("");
            Cad.setText("");
            Prec.setText("");
            ctr.Eliminar();
        }
            catch(Exception ex){ 
            JOptionPane.showMessageDialog(null, "No has cumplido con los requisitos requeridos");
        }
        }
    }
   public static void main(String[] args) { 
       ConexionDany Conexion=new ConexionDany();
       Conexion.setVisible(true);
}

    }
    

