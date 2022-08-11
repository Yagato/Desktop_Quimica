/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class UsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton btnRestablecer;

    @FXML
    private JFXTextField correoR;
    
    @FXML
    private Label labelEstado;
    
    private String email = "";
    
    private FirebaseAuth mAuth;

    @FXML
    void btnRestablecer(ActionEvent event) {
        
//        mAuth = FirebaseAuth.getInstance();
//        
//        email = btnRestablecer.getText().toString();
//        
//        if (!btnRestablecer.getText().equals("")) {
//            resetPassword();
//        } else{
//            labelEstado.setText("Ingrese el email");
//        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
//    UserRecord userRecord = FirebaseAuth.getInstance().generatePasswordResetLink(string);

    private void resetPassword() {
//        try {
//            mAuth.generatePasswordResetLink(btnRestablecer.getText().toString());
//            labelEstado.setText("Se ha enviado el correo para reestablecer la contraseña");
//        } catch (FirebaseAuthException ex) {
//            labelEstado.setText("No se pudo enviar el correo de reestablecer la contraseña");
//            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
