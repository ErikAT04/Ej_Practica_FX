package com.erikat.ej_repaso_fx.obj;

import java.util.ArrayList;

public class CRUDCliente {
    public static void insertarCliente(ArrayList<Usuario> lista, Usuario usu){
        if (buscarUsuario(lista, usu.getId())==null) { //Si no encuentra ningún usuario con ese id, añade el nuevo usuario. Si no, no modifica la lista.
            lista.add(usu);
        }
    }
    public static Usuario buscarUsuario(ArrayList<Usuario> lista, String correo){
        Usuario usuario = null; //Inicia un usuario en nulo
        for(Usuario u: lista){ //Recorre toda la lista y busca un usuario específico
            if (u.getId().equals(correo)){
                usuario = u;
                break;
            }
        }
        return usuario; //Si ha encontrado el usuario, devuelve el usuario. Si no, envía un usuario vacio
    }
    public static double totalIngresos(ArrayList<Usuario> lista){
        double ingresoTotal = 0;
        for (Usuario u : lista){
            ingresoTotal += ((u.isPremium()) ? 35.5:20.5) - u.discount; //En el caso de que el usuario sea premium, se suma 35.5, y al contrario, si no lo es, suma 20.5
        }
        return ingresoTotal;
    }
}
