package conexiondany;

import java.sql.*;

public class Controlador {

    private int Codigo;
    private Double Precio;
    private String Nombre, Caducidad;
    ResultSet rs;
    
    BD datos=new BD();
    
    Controlador(){

        Codigo=0;
        Nombre="";
        Caducidad="";
        Precio=0.0;
    }
    public void setCodigo(int cod){
        Codigo=cod;
    }
    public void setNombre(String nom){
        Nombre=nom;
    }
    public void setPrecio(Double prec){
        Precio=prec;
    }
    public void setCaducidad(String cad){
        Caducidad=cad;
    }
    public void RealizarInsert(){
        datos.CrearConexion();
        datos.Insertar(Codigo, Nombre, Caducidad, Precio);
        datos.CerrarConexion();
    }
    public ResultSet Buscar(){
        datos.CrearConexion();
        rs=datos.Busquedas(Codigo);
        datos.CerrarConexion();
        return rs;
    }
    public void Eliminar(){
        datos.CrearConexion();
        datos.Eliminar(Codigo);
        datos.CerrarConexion();
    }
    public void Modificar(){
        datos.CrearConexion();
        datos.Modificar(Codigo, Nombre, Caducidad, Precio);
        datos.CerrarConexion();
    }
}

