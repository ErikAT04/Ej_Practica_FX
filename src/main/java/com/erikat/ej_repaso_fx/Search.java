package com.erikat.ej_repaso_fx;

import com.erikat.ej_repaso_fx.obj.CRUDCliente;
import com.erikat.ej_repaso_fx.obj.Usuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Search {

    public TextField idTField;
    Stage thisStage;
    ArrayList<Usuario> listaUsuarios;

    public void onExitBttClick(ActionEvent actionEvent) { //Simplemente cierra la escena
        thisStage.close();
    }

    public void onSearchBttClick(ActionEvent actionEvent) {
        String id = idTField.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error de búsqueda");
        //Comienzo creando el Alert y un String que guarda la información del bloque de texto.
        if (id.isEmpty()){ //En caso de que no haya escrito nada, el alert solo dice que tiene que haber texto.
            alert.setContentText("El campo no puede estar vacío");
        } else {
            Usuario u = CRUDCliente.buscarUsuario(listaUsuarios, id); //Puede dar dos datos: Un objeto de tipo Usuario con datos y uno nulo.
            if (u==null){
                alert.setContentText("No se ha encontrado ese usuario en la lista");
            } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Información de usuario");
                alert.setContentText(u.toString()); //Si el usuario no es nulo, accede al objeto y saca su información.
                thisStage.close();
            }
        }
        alert.showAndWait();

    }

    public void initValues(ArrayList<Usuario> lista){ //Desde un controlador externo, pasa sus datos
        this.listaUsuarios = lista;
        thisStage = (Stage) idTField.getScene().getWindow(); //Esto sirve más que nada para controlar el stage, para decidir cuándo cerrarlo
    }
}
