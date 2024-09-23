package com.erikat.ej_repaso_fx;

import com.erikat.ej_repaso_fx.obj.CRUDCliente;
import com.erikat.ej_repaso_fx.obj.Usuario;
import com.erikat.ej_repaso_fx.obj.Utils;
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
        //Comienzo creando un String que guarda la información del bloque de texto.
        if (id.isEmpty()){ //En caso de que no haya escrito nada, el alert solo dice que tiene que haber texto.
            Utils.AlertShow(Alert.AlertType.ERROR, "El campo no puede estar vacío", "Error de Búsqueda");
        } else {
            Usuario u = CRUDCliente.buscarUsuario(listaUsuarios, id); //Puede dar dos datos: Un objeto de tipo Usuario con datos y uno nulo.
            if (u==null){
                Utils.AlertShow(Alert.AlertType.ERROR, "No se ha encontrado ese usuario en la lista", "Error de Búsqueda");
            } else {
                Utils.AlertShow(Alert.AlertType.INFORMATION, u.toString(), "Información de Usuario"); //Si el usuario no es nulo, accede al objeto y saca su información.
                thisStage.close();
            }
        }
    }

    public void initValues(ArrayList<Usuario> lista){ //Desde un controlador externo, pasa sus datos
        this.listaUsuarios = lista;
        thisStage = (Stage) idTField.getScene().getWindow(); //Esto sirve más que nada para controlar el stage, para decidir cuándo cerrarlo
    }
}
