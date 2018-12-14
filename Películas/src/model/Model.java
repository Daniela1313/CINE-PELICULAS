package model;
/*@set No regresa nada y permite modificar*/
/*@get Regresa y permite conocer el valor que tiene la variable*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Model {

    private Connection conexion; 
    private Statement st; 
    private ResultSet rs;
    private PreparedStatement ps;
    private String ID;
    private String NomP;

    public String getID() {
        return ID;
    }

    public void setID(String nombre) {
        this.ID = ID;
    }
    
    public String getNomP() {
        return NomP; 
    }

    public void setNomP(String email) {
        this.NomP = NomP; //llamada a la clase 
    }

    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/agenda_mvcs", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement(); //para ejecutar sentencias sql desde java
            rs = st.executeQuery("SELECT * FROM peliculas;"); //almacena temporalmente los datos de la consulta 
            rs.next(); //avanza de fila en fila 
            ID = rs.getString("id"); //almacena los nombres de los usuario en ID
            NomP = rs.getString("nombre"); //almacena el nombre de los usuarios en nombre pelicula 
            this.setID(ID);  //asigna un valor a la variable id
            this.setNomP(NomP); //asigna un valor a la variable nombre pelicula
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error Model 001: " + err.getMessage()); //Regresa si encuentra error 
        }

    }

    public void primerRegistro() throws SQLException { 
        System.out.print("Programa accion cambiarPrimerRegistro"); //Mensaje para asegurar que si funcione el boton primerRegistro
        rs.first(); //mover al priver registro
        this.setID(rs.getString("ID")); //Modifica en valor de la variable Nombre y permite regresar a conocer el nuevo valor que tiene. 
        this.setNomP(rs.getString("NomP")); //Modifica en valor de la variable Email y permite regresar a conocer el nuevo valor que tiene. 
    }

    public void siguienteRegistro() throws SQLException { 
        System.out.print("Programa accion cambiarSiguienteRegistro"); //Mensaje para asegurar que funcione el boton siguieteRegistro
        if (!rs.isLast()) { //mueve si es que hay un registro antes del siguiente o despues del primero
            rs.next(); //mover al siguiebte registroo
        }
        this.setID(rs.getString("ID")); //
        this.setNomP(rs.getString("NomP")); //
    }

    public void anteriorRegistro() throws SQLException {
        System.out.print("Programa accion camiarAnteriorRegistro"); //Mensaje para asegurar que funcione el boton anteriorRegistro
        if (!rs.isFirst()) { //mueve si es que hay un resgitro antes del primero y siguiente
            rs.previous();
        }
        this.setID(rs.getString("ID")); //
        this.setNomP(rs.getString("NomP")); //
    }

    public void ultimoRegistro() throws SQLException {
        System.out.print("Programa accion cambiarUltimoRegistro"); //Mensaje para asegurar que funcione el boton ultimoRegistro
        rs.last(); //mueve al ultimo registro
        this.setID(rs.getString("ID")); //
        this.setNomP(rs.getString("NomP")); //
    }

    public void insertarRegistro(String ID, String NomP) throws SQLException {
        System.out.print("Programa accion insertarRegistro"); //Mensaje que asegure que funciona el booton insertarRegistro
        st.executeUpdate("INSERT INTO peliculas(ID,NomP) VALUES" + "('" + ID + "','" + NomP + "');"); 
        this.conectarDB();
    }

    public void modificarRegistro(String ID, String NomP) throws SQLException {
        System.out.print("Programa accion modificarRegistro"); //Mensaje que asegure que funciona el booton modificarRegistro
        String actualID = this.getNomP();
        st.executeUpdate("UPDATE peliculas SET ID='" + ID + "',NomP='" + NomP + "'WHERE ID='" + actualID + "';");
        this.conectarDB();
    }

    public void eliminarRegistro(String ID) throws SQLException {
        System.out.print("Programa accion eliminarRegistro"); //Mensaje que asegure que funciona el booton eliminarRegistro
        st.executeUpdate("DELETE FROM peliculas WHERE ID='" + ID + "';"); 
        this.conectarDB();
    }
}
