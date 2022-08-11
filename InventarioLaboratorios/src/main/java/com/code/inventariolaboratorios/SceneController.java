package com.code.inventariolaboratorios;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
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

    @FXML
    void showPrincipal(ActionEvent event) throws IOException {

        if (!txtUsuario.getText().equals("") && !txtContra.getText().equals("")) {
            
            getDataByEmail();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));
            
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
            
        } else {

        }

        

        
//        stage.initModality(Modality.APPLICATION_MODAL);
        

    }

    private void getDataByEmail() {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(txtUsuario.getText());
            // See the UserRecord reference doc for the contents of userRecord.
            
            System.out.println("Successfully fetched user data: " + userRecord.getEmail());
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
