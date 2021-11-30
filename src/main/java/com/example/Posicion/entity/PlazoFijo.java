package com.example.Posicion.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlazoFijo {
    private Integer idPlazoFijo;
    private Integer numeroCuenta;
    private Double monto;
    private Integer plazoDias;
    private String usuario;
    private String fechaVencimiento;
    private Double capital;
    private Double intereses;
    private Double montoTotal;
    private Double tasa;
    private String status;

}
