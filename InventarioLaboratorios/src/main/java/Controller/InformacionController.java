/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static io.perfmark.PerfMark.link;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class InformacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private Text manual;

    @FXML
    void manual_de_usuario(MouseEvent event) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI("https://drive.google.com/file/d/1OLaxkW3dQLddjr6yOfk0j-l6RaRd2ZH0/view?usp=sharing"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void vincEnt(MouseEvent event) {
        manual.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }
    
     @FXML
    void vincExt(MouseEvent event) {
        manual.setStyle("-fx-text-fill: black; -fx-font-weight: regular;");
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
    }

}
