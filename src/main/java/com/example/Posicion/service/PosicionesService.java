package com.example.Posicion.service;

import com.example.Posicion.entity.Cuenta;
import com.example.Posicion.entity.PlazoFijo;
import com.example.Posicion.entity.Prestamo;
import com.example.Posicion.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PosicionesService {
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();


    public List<Cuenta> getCuentas(String userName) {
        ResponseEntity<Cuenta[]> cuentaResponseEntity = restTemplate.getForEntity("https://cuentasequipod.herokuapp.com/cuenta/buscarPorUsuario/" + userName, Cuenta[].class);
        Cuenta[] cuenta = cuentaResponseEntity.getBody();
        List<Cuenta> cuentas = Arrays.asList(cuenta);
        return cuentas;
    }


    public String getPrestamos(Integer numeroCuenta) {
        ResponseEntity<String> prestamoResponseEntity = restTemplate.getForEntity("https://equipod-prestamos.herokuapp.com/prestamos/prestamos-por-cuenta/" + numeroCuenta, String.class);
        String prestamo = prestamoResponseEntity.getBody();
        String prestamos = prestamo;
        //System.out.println(prestamoResponseEntity.getStatusCode().toString());
        return prestamos;
    }

    public List<Usuario> getUsuarios() {
        ResponseEntity<Usuario[]> usuarioResponseEntity = restTemplate.getForEntity("https://equipod-onboarding.herokuapp.com/api/auth/", Usuario[].class);
        Usuario[] usuario = usuarioResponseEntity.getBody();
        List<Usuario> usuarios = Arrays.asList(usuario);
        return usuarios;
    }

    public List<PlazoFijo> getPlazoFijos(Integer numeroCuenta) {
        ResponseEntity<PlazoFijo[]> plazoFijoResponseEntity = restTemplate.getForEntity("https://inversionesequipod.herokuapp.com/plazofijo/listadoplazofijo/" + numeroCuenta, PlazoFijo[].class);
        PlazoFijo[] plazoFijo = plazoFijoResponseEntity.getBody();
        List<PlazoFijo> plazoFijos = Arrays.asList(plazoFijo);
        //if (plazoFijoResponseEntity.getStatusCode().is5xxServerError()) {
          //  plazoFijos = null;
        //}
        return plazoFijos;
    }
/*
    public List<Usuario> getUsuarios() {
        ResponseEntity<Usuario[]> usuarioResponseEntity = restTemplate.getForEntity("https://equipod-onboarding.herokuapp.com/api/auth/", Usuario[].class);
        Usuario[] usuario = usuarioResponseEntity.getBody();
        List<Usuario> usuarios = Arrays.asList(usuario);
        return usuarios;
    }
*/
    public List<PlazoFijo> getPlazosPorCuenta(Cuenta cuenta){
        return getPlazoFijos(cuenta.getNumeroCuenta());
    }

    public Usuario getUsuario(String userName) {
        List<Usuario> usuarios = getUsuarios();
        List<Cuenta> cuentas;
        List<String> prestamos=new ArrayList<>();
        List<PlazoFijo> plazoFijos;
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(userName)) {
                cuentas = getCuentas(u.getUsername());
                for (Cuenta c : cuentas) {
                    c.setPlazoFijos(getPlazosPorCuenta(c));
                    if(c.getNumeroCuenta()!=null) {
                        prestamos.add(getPrestamos(c.getNumeroCuenta()));
                    }
                    c.setPrestamos(prestamos);
                }
                u.setCuentas(cuentas);
                return u;

            }
        }
        return null;
    }

}
