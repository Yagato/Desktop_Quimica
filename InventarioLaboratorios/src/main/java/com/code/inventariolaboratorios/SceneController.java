package com.code.inventariolaboratorios;

import Modelos.Usuarios;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SceneController {

    @FXML
    private JFXPasswordField txtContra;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXButton inicio;

    private Usuarios user;

    @FXML
    void showPrincipal(ActionEvent event) throws IOException {

        String usuario = txtUsuario.getText().trim();
        String password = txtContra.getText().trim();
        Parent root = null;

        if (usuario.equals("") || password.equals("")) {
            System.out.println("Llene los campos");
            return;
        }

        if (!login(usuario, password))
            return;

        if(user.getPrivilegio().equals("consulta"))
            root = FXMLLoader.load(getClass().getResource("/fxml/PrincipalJefe.fxml"));
        else if(user.getPrivilegio().equals("edicion"))
            root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        Image Icon = new Image("/imagenes/itcm1.png");
        stage.getIcons().add(Icon);
        stage.setTitle("Control de Inventarios");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();

        stage.initOwner(inicio.getScene().getWindow());
        Stage Stage2 = (Stage) inicio.getScene().getWindow();
        Stage2.close();
        stage.show();
        //stage.initModality(Modality.APPLICATION_MODAL);
    }

    public boolean login(String email, String password) {
        try{
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("Usuarios")
                        .whereEqualTo("correo", email)
                        .whereEqualTo("contraseña", password)
                        .get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if(documents.size() < 1){
                System.out.println("No existe ese usuario");
                return false;
            }

            for(DocumentSnapshot document : documents){
                user = document.toObject(Usuarios.class);
                System.out.println(document.getId() + " => " + user.getCorreo() + " "
                        + user.getContraseña() + " " + user.getPrivilegio());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

}
