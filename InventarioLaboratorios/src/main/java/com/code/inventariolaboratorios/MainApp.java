package com.code.inventariolaboratorios;

import Firebase.ConexionFirebase;
import com.google.cloud.firestore.Firestore;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    private final ConexionFirebase conexionFirebase = new ConexionFirebase();
    
    public static Firestore bd;

    @Override
    public void start(Stage stage) throws Exception {
        
        bd = conexionFirebase.iniciarFirebase();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Image Icon = new Image("/imagenes/itcm1.png");
        stage.getIcons().add(Icon);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.sizeToScene();
        
        stage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

}
