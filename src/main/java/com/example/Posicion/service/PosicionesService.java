package com.example.Posicion.service;

import com.example.Posicion.entity.Cuenta;
import com.example.Posicion.entity.PlazoFijo;
import com.example.Posicion.entity.Prestamo;
import com.example.Posicion.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PosicionesService {
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();


    public List<Cuenta> getCuentas(String userName) {
        ResponseEntity<Cuenta[]> cuentaResponseEntity = restTemplate.getForEntity("http://localhost:8081/cuenta/buscarPorUsuario/" + userName, Cuenta[].class);
        Cuenta[] cuenta = cuentaResponseEntity.getBody();
        List<Cuenta> cuentas = Arrays.asList(cuenta);
        return cuentas;
    }


    public List<String> getPrestamos(Integer numeroCuenta) {
        ResponseEntity<String[]> prestamoResponseEntity = restTemplate.getForEntity("http://localhost:8083/prestamos/prestamos-por-cuenta/" + numeroCuenta, String[].class);
        String[] prestamo = prestamoResponseEntity.getBody();
        List<String> prestamos = Arrays.asList(prestamo);
        return prestamos;
    }

    public List<Usuario> getUsuarios() {
        ResponseEntity<Usuario[]> usuarioResponseEntity = restTemplate.getForEntity("http://localhost:8080/api/auth/", Usuario[].class);
        Usuario[] usuario = usuarioResponseEntity.getBody();
        List<Usuario> usuarios = Arrays.asList(usuario);
        return usuarios;
    }

    public List<PlazoFijo> getPlazoFijos(Integer numeroCuenta) {
        ResponseEntity<PlazoFijo[]> plazoFijoResponseEntity = restTemplate.getForEntity("http://localhost:8084/plazofijo/listadoplazofijo/" + numeroCuenta, PlazoFijo[].class);
        PlazoFijo[] plazoFijo = plazoFijoResponseEntity.getBody();
        List<PlazoFijo> plazoFijos = Arrays.asList(plazoFijo);
        //if (plazoFijoResponseEntity.getStatusCode().is5xxServerError()) {
          //  plazoFijos = null;
        //}
        return plazoFijos;
    }

    public List<PlazoFijo> getPlazosPorCuenta(Cuenta cuenta){
        return getPlazoFijos(cuenta.getNumeroCuenta());
    }
    //listadoplazofijo
    public Usuario getUsuario(String userName) {
        List<Usuario> usuarios = getUsuarios();
        List<Cuenta> cuentas;
        List<PlazoFijo> plazoFijos;
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(userName)) {
                cuentas = getCuentas(u.getUsername());
                for (Cuenta c : cuentas) {
                    c.setPlazoFijos(getPlazosPorCuenta(c));
                    c.setPrestamos(getPrestamos(c.getNumeroCuenta()));
                }
                u.setCuentas(cuentas);
                return u;

            }
        }
        return null;
    }
}
