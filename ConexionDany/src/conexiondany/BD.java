package conexiondany;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

public class BD {
    private int Codigo=0;
    public String Nombre="",Caducidad="";
    public Double Precio=0.0;
    private final String driver="com.mysql.jdbc.Driver";
    private final String url="jdbc:mysql://localhost:3307/tienda";
    private final String user="root";
    private final String password="";
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    
    public void CrearConexion(){
        try {
            con=null;
            Class.forName(driver);
            con=DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: "+ex);
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);        
        }
    }
    
    public void CerrarConexion(){
    con=null;
    stmt=null;
}
    public void Insertar(int Codigo, String Nombre, String Caducidad, Double Precio){
        try {
            stmt=null;
            String sql="insert into productos(CODIGO, NOMBRE, CADUCIDAD, PRECIO)"+"values("+Codigo+",'"+Nombre+"','"+Caducidad+"','"+Precio+"')";
            stmt=con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "El registro se a Insertado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al inserter el registro");
        }
    }
    public ResultSet Busquedas(int Codigo){
        try {
            stmt=null;
            String sql="select * from productos where CODIGO = "+Codigo;
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ERROR "+ex);
    }
    return rs;
}
    public void Eliminar(int Codigo){
        try {
            stmt=null;
            String sql="delete from productos where CODIGO = "+Codigo;
            stmt=con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ex);
        } 
    }
    public void Modificar(int Codigo, String Nombre, String Caducidad, Double Precio){
        try {
            stmt=null;
            String sql="update productos set NOMBRE = '"+Nombre+"', CADUCIDAD ="+" '"+Caducidad+"', PRECIO ="+" '"+Precio+"'"+" where CODIGO="+Codigo;
            stmt=con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "El registro se ha modificado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Necesitas cumplir con los requisitos requeridos");
        }
    }
}
