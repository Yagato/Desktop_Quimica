/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ludwing
 */
public class Productos extends RecursiveTreeObject<Productos>{
    private StringProperty ProductoID, Id, Nombre, Cantidad;
    
    public Productos(String ProductoID, String Id, String Nombre, String Cantidad){
        this.ProductoID = new SimpleStringProperty(ProductoID);
        this.Id = new SimpleStringProperty(Id);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Cantidad = new SimpleStringProperty(Cantidad);
    }

    public String getProductoID() {
        return ProductoID.get();
    }

    public void setProductoID(String ProductoID) {
        this.ProductoID = new SimpleStringProperty(ProductoID);
    }
    
    
    
    public String getId() {
        return Id.get();
    }

    public void setId(String Id) {
        this.Id = new SimpleStringProperty(Id);
    }

    public String getNombre() {
        return Nombre.get();
    }

    public void setNombre(String Nombre) {
        this.Nombre = new SimpleStringProperty(Nombre);
    }

    public String getCantidad() {
        return Cantidad.get();
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = new SimpleStringProperty(Cantidad);
    }
    
}
