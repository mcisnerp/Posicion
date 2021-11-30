package com.example.Posicion.controller;

import com.example.Posicion.entity.PlazoFijo;
import com.example.Posicion.entity.Usuario;
import com.example.Posicion.service.PosicionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posicion")
public class PosicionController {
    @Autowired
    private PosicionesService posicionesService;

    @GetMapping("/{userName}")
    public Usuario getUsuario(@PathVariable String userName){
        return posicionesService.getUsuario(userName);
    }
    @GetMapping("/plazosfijos/{numeroCuenta}")
    public List<PlazoFijo> getplazofijos(@PathVariable Integer numeroCuenta){
        return posicionesService.getPlazoFijos(numeroCuenta);
    }
}
