package com.example.Posicion.entity;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

//import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prestamo {
    private Integer idPrestamo;
    private Integer numeroCuenta;
    private Double montoTotalPrestamo;
    private Integer plazoMeses;
    private Calendar fechaInicio;
    private Float tasaInteres;

}
