package com.utn.tp2.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity //una clase manejada por un motor de persistencia
@Data //Getter and Setter
@AllArgsConstructor //crear un constructor con todos los argumentos
@NoArgsConstructor //crear un constructor vacio
@Builder //mandar atributos del constructor desordenados
public class Pedido implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String estado;
    private Date fecha;
    private String tipoEnvio;
    private double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();
    public void agregarDetallePedido(DetallePedido detped){
        detallePedidos.add(detped);
    }
    public void mostrarDetallePedidos() {
        System.out.println("Detalle del Pedido con id: " + this.id);
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("Cantidad: " + detallePedido.getCantidad() + ", Subtotal: " + detallePedido.getSubtotal());
        }
    }
}
