package com.erikat.ej_repaso_fx;

import com.erikat.ej_repaso_fx.obj.CRUDCliente;
import com.erikat.ej_repaso_fx.obj.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Search {

    public TextField idTField;
    Stage thisStage;
    ArrayList<Usuario> listaUsuarios;

    public void onExitBttClick(ActionEvent actionEvent) {
        thisStage.close();
    }

    public void onSearchBttClick(ActionEvent actionEvent) {
        String id = idTField.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error de búsqueda");
        if (id.isEmpty()){
            alert.setContentText("El campo no puede estar vacío");
        } else {
            Usuario u = CRUDCliente.buscarUsuario(listaUsuarios, id);
            if (u==null){
                alert.setContentText("No se ha encontrado ese usuario en la lista");
            } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Información de usuario");
                alert.setContentText(u.toString());
                thisStage.close();
            }
        }
        alert.showAndWait();

    }

    public void initValues(ArrayList<Usuario> lista){
        this.listaUsuarios = lista;
        thisStage = (Stage) idTField.getScene().getWindow();
    }
}
