/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ludwing
 */
public class PrincipalJefeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private JFXButton BtnInfo;

    @FXML
    private JFXButton BtnInventario;

    @FXML
    private JFXButton BtnUsuario;

    @FXML
    private JFXButton BtnCerrarSesion;

    @FXML
    private Pane principalPane;

    @FXML
    void CerrarSesion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        Image Icon = new Image("/imagenes/itcm1.png");
        stage.getIcons().add(Icon);
        stage.setTitle("Control de Inventarios");
        stage.setScene(scene);
        
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();
        stage.initOwner(BtnCerrarSesion.getScene().getWindow());
        Stage Stage2 = (Stage) BtnCerrarSesion.getScene().getWindow();
        Stage2.close();
        stage.show();
    }

    @FXML
    void Color1(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #1C1C1C;");
        BtnInventario.setStyle("-fx-background-color: #000000;");
        BtnUsuario.setStyle("-fx-background-color: #000000;");
    }

    @FXML
    void Color2(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #000000;");
        BtnInventario.setStyle("-fx-background-color: #1C1C1C;");
        BtnUsuario.setStyle("-fx-background-color: #000000;");
    }

    @FXML
    void Color4(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #000000;");
        BtnInventario.setStyle("-fx-background-color: #000000;");
        BtnUsuario.setStyle("-fx-background-color: #1C1C1C;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void paginaInfo2(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/InformacionJefe.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }

    @FXML
    void paginaInventario2(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/InventarioJefe.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }

    @FXML
    void paginaUsuario2(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/Usuario.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }
    
       
    
}
