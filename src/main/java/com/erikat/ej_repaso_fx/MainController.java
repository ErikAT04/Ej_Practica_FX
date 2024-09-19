package com.erikat.ej_repaso_fx;

import com.erikat.ej_repaso_fx.obj.CRUDCliente;
import com.erikat.ej_repaso_fx.obj.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public void onAddBttClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de inserción");
        alert.setHeaderText(null);
        try {
            String id = userTField.getText();
            String passwd = passTField.getText();
            double discount = Double.parseDouble(discountTField.getText());
            RadioButton rb = (RadioButton) premiumTGroup.getSelectedToggle();
            if (id.isEmpty() || passwd.isEmpty() || discount == 0 || rb == null){
                alert.setContentText("No puede haber un campo vacío o sin seleccionar");
            } else {
                int listSize = listaClientes.size();
                CRUDCliente.insertarCliente(listaClientes, new Usuario(id, passwd, discount, (rb.getText().equals("Si"))));
                if (listSize == listaClientes.size()){
                    alert.setContentText("Ya existe un usuario con ese correo");
                } else {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Usuario añadido");
                    alert.setContentText("Usuario añadido correctamente");
                }
            }
        }catch (NumberFormatException e){
            alert.setContentText("En el bloque de descuento solo puede haber un valor numérico");
        }
        alert.showAndWait();
    }

    public void onCleanBttClick(ActionEvent actionEvent) {
        userTField.setText("");
        passTField.setText("");
        discountTField.setText("");
    }

    public void onSearchBttClick(ActionEvent actionEvent) {
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(InitApp.class.getResource("SearchView.fxml"));
            Scene scene = new Scene(loader.load());
            st.setScene(scene);
            Search searchController = loader.getController();
            searchController.initValues(listaClientes);
            st.showAndWait();
        }catch (IOException e){
            System.out.println("Error de ventana\n" + e.getMessage());
        }
    }

    public void onIncomeBttClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información sobre los ingresos: ");
        alert.setContentText("De los usuarios, se percibe un total de " + CRUDCliente.totalIngresos(listaClientes) + "euros");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void onExitBttClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("¡Hasta luego!");
        alert.setContentText("¡Vuelve pronto!");
        alert.showAndWait();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaClientes = new ArrayList<>();
    }
}