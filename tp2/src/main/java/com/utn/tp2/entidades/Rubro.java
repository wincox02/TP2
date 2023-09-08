package com.utn.tp2.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity //una clase manejada por un motor de persistencia
@Data //Getter and Setter
@AllArgsConstructor //crear un constructor con todos los argumentos
@NoArgsConstructor //crear un constructor vacio
@Builder //mandar atributos del constructor desordenados
public class Rubro implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
    public void agregaProducto(Producto prd){
        productos.add(prd);
    }
    public void mostrarProductos() {
        System.out.println("Detalle pedido con id: " + this.id);
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipo() + " , TiempoEstimadoCocina: " + producto.getTiempoEstimadoCocina() + " , Denominacion: " + producto.getDenominacion() + " , precioVenta: " + producto.getPrecioVenta() + " , precioCompra: " + producto.getPrecioCompra() + " , stockActual: " + producto.getStockActual() + " , stockMinimo: " + producto.getStockMinimo() + " , unidadMedida: " + producto.getUnidadMedida() + " , receta: " + producto.getReceta());
        }
    }
}
