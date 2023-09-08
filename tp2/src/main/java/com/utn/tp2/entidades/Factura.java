package com.utn.tp2.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity //una clase manejada por un motor de persistencia
@Data //Getter and Setter
@AllArgsConstructor //crear un constructor con todos los argumentos
@NoArgsConstructor //crear un constructor vacio
@Builder //mandar atributos del constructor desordenados
public class Factura implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int numero;
    private Date fecha;
    private double descuento;
    private String formaPago;
    private int total;
}
