package com.erikat.ej_repaso_fx;

import com.erikat.ej_repaso_fx.obj.CRUDCliente;
import com.erikat.ej_repaso_fx.obj.Usuario;
import com.erikat.ej_repaso_fx.obj.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TextField userTField;
    public PasswordField passTField;
    public TextField discountTField;
    public ToggleGroup premiumTGroup;
    ArrayList<Usuario> listaClientes;

    //Creo todos los objetos de los controladores, además de un objeto del tipo ArrayList<Usuario> sin inicializar. Este último se iniciará en la función initialize.

    public void onAddBttClick(ActionEvent actionEvent) {
        try { //Abro un try-catch para controlar una posible excepción
            String id = userTField.getText();
            String passwd = passTField.getText();
            RadioButton rb = (RadioButton) premiumTGroup.getSelectedToggle();
            //Comienzo guardando todos los datos que puedo sacar de los distintos controladores.
            if (id.isEmpty() || passwd.isEmpty() || rb == null || discountTField.getText().isEmpty()){ //Si se da el caso de que alguno de estos es nulo, el programa no continúa.
                Utils.AlertShow(Alert.AlertType.ERROR, "No puede haber un campo vacío o sin seleccionar", "Error de Inserción");
            } else {
                double discount = Double.parseDouble(discountTField.getText());
                if (discount < 0 || discount >= (rb.getText().equals("Si") ? 35.5 : 20.5)){
                    Utils.AlertShow(Alert.AlertType.ERROR, "El descuento no puede ser negativo ni igual o superior a su tarifa (35.5 para Premium y 20.5 para no premium)", "Error en el descuento");
                }else {
                    int listSize = listaClientes.size(); //Guardo el tamaño de la lista antes de tratar de añadir al nuevo usuario, este dato se utilizará en la condicional posteriormente.
                    CRUDCliente.insertarCliente(listaClientes, new Usuario(id, passwd, discount, (rb.getText().equals("Si")))); //La función no necesita devolver nada, ya que recibe directamente la dirección de memoria del ArrayList, lo que significa que si se edita la lista en la función se edita la lista original.
                    if (listSize == listaClientes.size()) { //Ahora, compara el tamaño de la lista actual con el de la lista antes de pasar por la función. Si el tamaño es el mismo, es porque el id del usuario que ha intentado meter ya existía.
                        Utils.AlertShow(Alert.AlertType.ERROR, "Ya existe un usuario con ese ID", "Error de Inserción");
                    } else {
                        Utils.AlertShow(Alert.AlertType.INFORMATION, "Usuario añadido correctamente", "Usuario añadido");
                    }
                }
            }
        }catch (NumberFormatException e){ //La única excepción que puede dar es del formato del descuento, ya que el usuario puede introducir un valor no numérico y el programa trataría de parsearlo,
            Utils.AlertShow(Alert.AlertType.ERROR, "El campo de descuento solo puede tener valores numéricos", "Error de Inserción");
        }
    }

    public void onCleanBttClick(ActionEvent actionEvent) {
        userTField.setText("");
        passTField.setText("");
        discountTField.setText("");
        //Para vaciar los campos de tipo texto, es tan facil como poner "", que equivale a un string vacío.
        premiumTGroup.selectToggle(null); //Al elegir un objeto nulo en el selectToggle, se desactiva la selección realizada previamente
    }

    public void onSearchBttClick(ActionEvent actionEvent) { //Esta función había varias formas de hacerlo: Desde la propia interfaz ya creada con el controlador del id, o desde una nueva escena. Me he decantado por la segunda opción:
        try { //Controlo la excepción del FXMLLoader
            Stage st = new Stage(); //Creo un nuevo Stage que mostrará una interfaz de búsqueda.
            FXMLLoader loader = new FXMLLoader(InitApp.class.getResource("SearchView.fxml"));
            Scene scene = new Scene(loader.load()); //Creo y cargo un loader con la interfaz de búsqueda
            st.setScene(scene); //Pongo la escena en el stage
            Search searchController = loader.getController(); //Cargo un objeto de tipo Controller de la otra interfaz para poder usar sus métodos desde fuera de la nueva ventana.
            searchController.initValues(listaClientes); //Paso la lista de clientes al controlador de la ventana nueva.
            st.showAndWait();//Se abre la nueva ventana y espera que se cierre
        }catch (IOException e){
            System.out.println("Error de ventana\n" + e.getMessage());
        }
    }

    public void onIncomeBttClick(ActionEvent actionEvent) { //Esta clase es sencilla: Se crea un Alert que muestra el total de ingresos
        String infoIngresosRecibidos = String.format("De los usuarios, se percibe un total de %.02f euros", CRUDCliente.totalIngresos(listaClientes)); //Se accede a la clase estática y saca el total de ingresos
        Utils.AlertShow(Alert.AlertType.INFORMATION, infoIngresosRecibidos, "Información sobre los ingresos");
    }

    public void onExitBttClick(ActionEvent actionEvent) {
        Utils.AlertShow(Alert.AlertType.INFORMATION, "¡Vuelve pronto!", "¡Hasta luego!");//Se muestra y, hasta que no se cierra, la aplicación no se detiene
        System.exit(0); //Cierra el programa, mostrando código 0 de salida (Normalmente atribuido a que se ha cerrado correctamente)
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //La aplicación implementa la interfaz Initializable, que le permite iniciar objetos nada más cargar el FXMLLoader de la escena. Lo aprovecho para abrir el ArrayList.
        listaClientes = new ArrayList<>();
    }
}