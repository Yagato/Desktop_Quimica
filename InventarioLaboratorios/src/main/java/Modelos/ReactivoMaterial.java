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
public class ReactivoMaterial extends RecursiveTreeObject<ReactivoMaterial>{
    
    private StringProperty Id, Nombre, Cantidad;

    public ReactivoMaterial(String Id, String Nombre, String Cantidad) {
        this.Id = new SimpleStringProperty(Id);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Cantidad = new SimpleStringProperty(Cantidad);
    }

    public String getId() {
        return Id.get();
    }

    public String getNombre() {
        return Nombre.get();
    }

    public String getCantidad() {
        return Cantidad.get();
    }

    public void setId(String Id) {
        this.Id = new SimpleStringProperty(Id);
    }

    public void setNombre(String Nombre) {
        this.Nombre = new SimpleStringProperty(Nombre);
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = new SimpleStringProperty(Cantidad);
    }
    
    
    
}
