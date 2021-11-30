package com.example.Posicion.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    private Integer idCuenta;
    private String tipoCuenta;
    private String usuario;
    private String monedaCuenta;
    private Double balance;
    private Integer numeroCuenta;

    private List<String> prestamos;
    private List<Tarjeta> tarjetas;
    private List<PlazoFijo> plazoFijos;

}
