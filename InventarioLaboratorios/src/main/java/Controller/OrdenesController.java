/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelos.Productos;
import Modelos.ReactivoMaterial;
import com.code.inventariolaboratorios.MainApp;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Ludwing
 */
public class OrdenesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ReactivoMaterial rm = null;
    TreeTableView<ReactivoMaterial> treeTableView = new TreeTableView<ReactivoMaterial>();
    private ObservableList<ReactivoMaterial> listaReactivoMaterial = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtCantidad;

    @FXML
    private Label labelEstado;

    @FXML
    private JFXTreeTableView<ReactivoMaterial> tabla1;

    @FXML
    private TreeTableColumn<ReactivoMaterial, String> id;

    @FXML
    private TreeTableColumn<ReactivoMaterial, String> nombre;

    @FXML
    private TreeTableColumn<ReactivoMaterial, String> cantidad;

    @FXML
    void add(ActionEvent event) {
        if (!txtId.getText().equals("") && !txtNombre.getText().equals("") && !txtCantidad.getText().equals("")) {

            ReactivoMaterial reactivoMaterial = new ReactivoMaterial(txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim());

            labelEstado.setText("Dato agregado");
            listaReactivoMaterial.add(reactivoMaterial);
            setupTabla(listaReactivoMaterial);
            cleanFields();

        } else {
            labelEstado.setText("Ingrese todos los campos");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if (!txtId.getText().equals("") && !txtNombre.getText().equals("") && !txtCantidad.getText().equals("")) {

            if (rm != null) {
                listaReactivoMaterial.remove(tabla1.getSelectionModel().getSelectedItem().getValue());
                labelEstado.setText("Dato eliminado");
                refreshTablaLocal(tabla1.getSelectionModel().getSelectedIndex(), txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim());

            } else {
                labelEstado.setText("No se ha seleccionado una fila en la tabla");
            }
        } else {
            labelEstado.setText("Algunos campos están vacíos");
        }
        cleanFields();
        rm = null;
    }

    @FXML
    void edit(ActionEvent event) {
        if (!txtId.getText().equals("") && !txtNombre.getText().equals("") && !txtCantidad.getText().equals("")) {

            if (rm != null) {

                System.out.println("Se editó");
                if (!txtId.getText().trim().equals(rm.getId()) || !txtNombre.getText().trim().equals(rm.getNombre()) || !txtCantidad.getText().trim().equals(rm.getCantidad())) {
                    refreshTablaLocal(tabla1.getSelectionModel().getSelectedIndex(), txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim());
                    rm.setId(txtId.getText().trim());
                    rm.setNombre(txtNombre.getText().trim());
                    rm.setCantidad(txtCantidad.getText().trim());
                    listaReactivoMaterial.set(tabla1.getSelectionModel().getSelectedIndex(), rm);
                    labelEstado.setText("Dato editado");
                    cleanFields();

                    setupTabla(listaReactivoMaterial);

                } else {
                    labelEstado.setText("Dato no editado");
                }

                cleanFields();
                rm = null;
                tabla1.getSelectionModel().clearSelection();
            } else {
                labelEstado.setText("No se ha seleccionado una fila en la tabla");
            }
        } else {
            labelEstado.setText("Algunos campos están vacíos");
        }
    }

    @FXML
    void getRow(MouseEvent event) {
        int indice = tabla1.getSelectionModel().getSelectedIndex();
        System.out.println(indice);
        if (indice > -1) {
            rm = tabla1.getTreeItem(indice).getValue();
            txtId.setText(rm.getId());
            txtNombre.setText(rm.getNombre());
            txtCantidad.setText(rm.getCantidad());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setupColumnas();
    }

    private void setupTabla(ObservableList<ReactivoMaterial> listaReactivoMaterial) {
        TreeItem<ReactivoMaterial> value = new RecursiveTreeItem<>(listaReactivoMaterial, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tabla1.setRoot(value);
        tabla1.setShowRoot(false);
    }

    private void setupColumnas() {
        id.setCellValueFactory(new TreeItemPropertyValueFactory<ReactivoMaterial, String>("Id"));
        nombre.setCellValueFactory(new TreeItemPropertyValueFactory<ReactivoMaterial, String>("Nombre"));
        cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<ReactivoMaterial, String>("Cantidad"));
    }

    private void cleanFields() {
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
    }

    private void refreshTablaLocal(int indice, String Id, String Nombre, String Cantidad) {

        rm.setId(Id);
        rm.setNombre(Nombre);
        rm.setCantidad(Cantidad);
        tabla1.getTreeItem(indice).setValue(rm);
        tabla1.refresh();
    }

    @FXML
    void pdf(ActionEvent event) {
        if(listaReactivoMaterial.size() <= 0){
            System.out.println("Lista vacia");
            return;
        }

        String[] id = new String[listaReactivoMaterial.size()];
        String[] nombre = new String[listaReactivoMaterial.size()];
        String[] cantidad = new String[listaReactivoMaterial.size()];

        for(int i = 0; i < listaReactivoMaterial.size(); i++){
            id[i] = listaReactivoMaterial.get(i).getId();
            nombre[i] = listaReactivoMaterial.get(i).getNombre();
            cantidad[i] = listaReactivoMaterial.get(i).getCantidad();
        }

        try{
            Node node = (Node) event.getSource();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF",  "*.pdf"));
            File file = fileChooser.showSaveDialog(node.getScene().getWindow());
            if(file != null) {
                PdfController pdf = new PdfController(id, nombre, cantidad);
                pdf.generatePDF(file.getAbsolutePath());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
