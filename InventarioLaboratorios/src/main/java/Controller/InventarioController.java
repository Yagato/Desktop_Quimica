/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Firebase.CRUDFirebase;
import Modelos.Productos;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import static com.sun.javafx.application.PlatformImpl.addListener;
import java.util.UUID;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ludwing
 */
public class InventarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Productos p = null;

    private ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
    private ObservableList<Productos> listaProductosFiltro = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtBuscar;

    @FXML
    private Label labelEstado;

    @FXML
    private JFXTreeTableView<Productos> tabla1;

    @FXML
    private TreeTableColumn<Productos, String> id;

    @FXML
    private TreeTableColumn<Productos, String> nombre;

    @FXML
    private TreeTableColumn<Productos, String> cantidad;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtCantidad;

    private final CRUDFirebase cRUDFirebase = new CRUDFirebase();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        read();
        setupColumnas();
        textFieldBuscarListener();
    }

    @FXML
    void add(ActionEvent event) {
        if (!txtId.getText().equals("") && !txtNombre.getText().equals("") && !txtCantidad.getText().equals("")) {
            
            Productos productos = new Productos(UUID.randomUUID().toString(), txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim());
            if (cRUDFirebase.addFirebase(productos)) {
                labelEstado.setText("Dato agregado");
                listaProductos.add(productos);
                setupTabla(listaProductos);
                cleanFields();
            }
        } else {
            labelEstado.setText("Ingrese todos los campos");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if (!txtId.getText().equals("") && !txtNombre.getText().equals("") && !txtCantidad.getText().equals("")) {

            if (p != null) {
                if (cRUDFirebase.deleteFirebase(p.getProductoID())) {

                    listaProductos.remove(tabla1.getSelectionModel().getSelectedItem().getValue());
                    setupTabla(listaProductos);
                    labelEstado.setText("Dato eliminado");
//                    refreshTablaLocal(tabla1.getSelectionModel().getSelectedIndex(), txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim());
                } else {
                    labelEstado.setText("Dato no eliminado");
                }

            } else {
                System.out.println("No se ha seleccionado una fila en la tabla");
            }
        } else {
            System.out.println("Algunos campos estan vacios");
        }
        cleanFields();
        p = null;
        tabla1.getSelectionModel().clearSelection();
    }

    @FXML
    void edit(ActionEvent event) {

        if (!txtId.getText().equals("") && !txtNombre.getText().equals("") && !txtCantidad.getText().equals("")) {

            if (p != null) {
                System.out.println("Editando");
                if (cRUDFirebase.updateFirebase(p.getProductoID(), txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim())) {
                    System.out.println("Se edito");
                    if (!txtId.getText().trim().equals(p.getId()) || !txtNombre.getText().trim().equals(p.getNombre()) || !txtCantidad.getText().trim().equals(p.getCantidad())) {
//                        refreshTablaLocal(tabla1.getSelectionModel().getSelectedIndex(), txtId.getText().trim(), txtNombre.getText().trim(), txtCantidad.getText().trim());
                        refreshTabla();
//                        p.setId(txtId.getText().trim());
//                        p.setNombre(txtNombre.getText().trim());
//                        p.setCantidad(txtCantidad.getText().trim());
//                        listaProductos.set(tabla1.getSelectionModel().getSelectedIndex(), p);
                        labelEstado.setText("Dato editado");
                        cleanFields();
//                        listaProductosFiltro.;                        

                        setupTabla(listaProductos);

                    } else {
                        labelEstado.setText("Dato no editado");
                    }
                } else {
                    System.out.println("No se edito");

                }

                cleanFields();
                p = null;
                tabla1.getSelectionModel().clearSelection();
            } else {
                System.out.println("No se ha seleccionado una fila en la tabla");
            }
        } else {
            System.out.println("Algunos campos estan vacios");
        }
    }

    @FXML
    void getRow(MouseEvent event) {
        int indice = tabla1.getSelectionModel().getSelectedIndex();
        System.out.println(indice);
        if (indice > -1) {
            p = tabla1.getTreeItem(indice).getValue();
            txtId.setText(p.getId());
            txtNombre.setText(p.getNombre());
            txtCantidad.setText(p.getCantidad());
        }
    }

    //metodos
    private void read() {
        if (cRUDFirebase.readFirebase()) {
            System.out.println("No hubo errores");
            ObservableList<Productos> listaProductos1 = cRUDFirebase.getListaProductos();
            if (listaProductos1.size() > 0) {
                listaProductos = listaProductos1;
                setupTabla(this.listaProductos);

            }
        } else {
            System.out.println("Error");
        }
    }

    private void setupColumnas() {
        id.setCellValueFactory(new TreeItemPropertyValueFactory<Productos, String>("Id"));
        nombre.setCellValueFactory(new TreeItemPropertyValueFactory<Productos, String>("Nombre"));
        cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<Productos, String>("Cantidad"));
    }

    private void setupTabla(ObservableList<Productos> listaProductos) {
        TreeItem<Productos> value = new RecursiveTreeItem<>(listaProductos, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tabla1.setRoot(value);
        tabla1.setShowRoot(false);
    }

    private void cleanFields() {
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
    }

    private void refreshTabla() {
        listaProductos.clear();
        read();
    }

//    private void refreshTablaLocal(int indice, String Id, String Nombre, String Cantidad) {
//
//        p.setId(Id);
//        p.setNombre(Nombre);
//        p.setCantidad(Cantidad);
//        tabla1.getTreeItem(indice).setValue(p);
//        tabla1.refresh();
//    }

    private void textFieldBuscarListener() {
        txtBuscar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)
                -> {
            tabla1.setRoot(null);
            if (txtBuscar.getText().equals("")) {
                setupTabla(listaProductos);
            }
            if (newValue.equals("")) {
                setupTabla(listaProductos);
                System.out.println("vacio");
                labelEstado.setText("");
            } else {
                System.out.println(newValue);
                listaProductosFiltro.clear();
                labelEstado.setText("");
                for (Productos productos : listaProductos) {
                    if (productos.getNombre().toLowerCase().startsWith(newValue.toLowerCase())) {
                        listaProductosFiltro.add(productos);
                    } else {
                        setupTabla(listaProductosFiltro);
                    }
                }
            }
        });

    }
}
