package main; 
import model.Model; //llama a lo que se este ejecutando en el modelo
import view.View; //llama a lo que se este ejecutando en vista
import controller.Controller; //llama a lo que se este ejecutanto en el controlador

/**
 *
 * @author Gabi Curiel
 */
public class Main { 

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    } 
}
