package com.erikat.ej_repaso_fx.obj;

import javafx.scene.control.Alert;

public class Utils {
    public static void AlertShow (Alert.AlertType alertType, String content, String title){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
