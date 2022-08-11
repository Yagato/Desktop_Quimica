package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton BtnCerrarSesion;

    @FXML
    private JFXButton BtnInfo;

    @FXML
    private JFXButton BtnInventario;

    @FXML
    private JFXButton BtnOrden;

    @FXML
    private JFXButton BtnUsuario;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void paginaInfo(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/Informacion.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }

    @FXML
    void paginaInventario(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/Inventario.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }

    @FXML
    void paginaOrdenes(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/Ordenes.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }

    @FXML
    void paginaUsuario(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/Usuario.fxml"));
        principalPane.getChildren().removeAll();
        principalPane.getChildren().setAll(fxml);
    }

    @FXML
    void Color1(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #1C1C1C;");
        BtnInventario.setStyle("-fx-background-color: #000000;");
        BtnOrden.setStyle("-fx-background-color: #000000;");
        BtnUsuario.setStyle("-fx-background-color: #000000;");
    }

    @FXML
    void Color2(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #000000;");
        BtnInventario.setStyle("-fx-background-color: #1C1C1C;");
        BtnOrden.setStyle("-fx-background-color: #000000;");
        BtnUsuario.setStyle("-fx-background-color: #000000;");
    }

    @FXML
    void Color3(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #000000;");
        BtnInventario.setStyle("-fx-background-color: #000000;");
        BtnOrden.setStyle("-fx-background-color: #1C1C1C;");
        BtnUsuario.setStyle("-fx-background-color: #000000;");
    }

    @FXML
    void Color4(MouseEvent event) {
        BtnInfo.setStyle("-fx-background-color: #000000;");
        BtnInventario.setStyle("-fx-background-color: #000000;");
        BtnOrden.setStyle("-fx-background-color: #000000;");
        BtnUsuario.setStyle("-fx-background-color: #1C1C1C;");
    }

}
