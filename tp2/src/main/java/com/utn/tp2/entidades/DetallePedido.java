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
public class DetallePedido implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int cantidad;
    private double subtotal;

    @ManyToOne
    private Producto producto;
}
