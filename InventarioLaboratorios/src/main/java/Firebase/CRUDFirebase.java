/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Firebase;

import Modelos.Productos;
import com.code.inventariolaboratorios.MainApp;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ludwing
 */
public class CRUDFirebase {

    private boolean key;
    private ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
    private Productos productos;

    public ObservableList<Productos> getListaProductos() {
        return listaProductos;
    }

    public boolean addFirebase(Productos productos) {
        key = false;
        // Create a Map to store the data we want to set
        Map<String, Object> docProducto = new HashMap<>();
        docProducto.put("Id", productos.getId());
        docProducto.put("Nombre", productos.getNombre());
        docProducto.put("Cantidad", productos.getCantidad());
        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = MainApp.bd.collection("Productos").document(productos.getProductoID()).set(docProducto);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return key;
    }

    public boolean readFirebase() {
        key = false;

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = MainApp.bd.collection("Productos").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                System.out.println("Leyendo Datos...");
                for (QueryDocumentSnapshot document : documents) {
                    System.out.println(document.getId() + " => " + document.getData().get("Id"));
                    productos = new Productos(document.getId(), document.getData().get("Id").toString(), document.getData().get("Nombre").toString(), document.getData().get("Cantidad").toString());
                    listaProductos.add(productos);
                }
            } else {
                System.out.println("No hay datos que leer");
            }
            key = true;
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return key;
    }

    public boolean updateFirebase(String ProductoID, String Id, String Nombre, String Cantidad) {

        key = false;

        // Update an existing document
        DocumentReference docRef = MainApp.bd.collection("Productos").document(ProductoID);

        try {
            // (async) Update one field
            ApiFuture<WriteResult> future = docRef.update("Id", Id, "Nombre", Nombre, "Cantidad", Cantidad);

            System.out.println("Update: " + future.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return key;
    }

    public boolean deleteFirebase(String ProductoID) {
        key = false;

        try {
            // asynchronously delete a document
            ApiFuture<WriteResult> writeResult = MainApp.bd.collection("Productos").document(ProductoID).delete();
            System.out.println("Delete time : " + writeResult.get().getUpdateTime());
            key = true;
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return key;
    }

}
