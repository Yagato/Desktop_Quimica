/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
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
    private JFXPasswordField contraActual;

    @FXML
    private JFXPasswordField nuevaContra;

    @FXML
    private JFXPasswordField confirmarContra;
    
    @FXML
    private Label labelEstado;
    
    private String email = "", contraActualString = "", nuevaContraString = "", confirmarContraString = "";
    //private FirebaseAuth mAuth;

    private Usuarios user;
    private String userID = "";

    Firestore db = FirestoreClient.getFirestore();

    @FXML
    void btnRestablecer(ActionEvent event) throws ExecutionException, InterruptedException {
        email = correoR.getText().trim();
        contraActualString = contraActual.getText().trim();
        nuevaContraString = nuevaContra.getText().trim();
        confirmarContraString = confirmarContra.getText().trim();

        if(email.equals("") || contraActualString.equals("") ||
                nuevaContraString.equals("") || confirmarContraString.equals("")){
            System.out.println("Llene los campos");
            return;
        }

        if(!checkUser(email, contraActualString))
            return;

        if(!nuevaContraString.equals(confirmarContraString)){
            System.out.println("Las contrasenias no coinciden");
            return;
        }

        Map<String, Object> update = new HashMap<>();
        update.put("contraseña", nuevaContraString);

        DocumentReference documentReference = db.collection("Usuarios").document(userID);

        ApiFuture<WriteResult> writeResult = documentReference.update(update);
        //System.out.println("test contrasenia");

        
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

    public boolean checkUser(String email, String password) {
        try{
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
                userID = document.getId();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return true;
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
