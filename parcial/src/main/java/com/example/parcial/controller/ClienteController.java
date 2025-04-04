package com.example.parcial.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcial.model.Cliente;
import com.example.parcial.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/guardar-cliente")
    public void guardarCliente(@RequestBody Cliente cliente){
        clienteService.guardarCliente(cliente);
    }

    @GetMapping("/cliente-id/{id}")
    public Cliente obtenerClienteById(@PathVariable Integer id){
        return clienteService.obtenerClienteById(id);
    }

    @GetMapping("/cliente-cedula/{cedula}")
    public Cliente obtenerClienteByCedula(@PathVariable String cedula) {
        return clienteService.obtenerClienteByCedula(cedula);
    }
    
    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @PutMapping("/actualizar-cliente")
    public void actualizarCliente(@RequestBody Cliente cliente) {
        clienteService.actualizarCliente(cliente);
    }

    @DeleteMapping("/eliminar-cliente/{id}")
    public void eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
    }

}
