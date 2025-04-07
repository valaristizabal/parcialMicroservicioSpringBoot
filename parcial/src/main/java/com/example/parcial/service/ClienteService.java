package com.example.parcial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.parcial.model.Cliente;
import com.example.parcial.repository.ClienteRepository;

import org.springframework.transaction.annotation.Transactional;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void guardarCliente(Cliente cliente) {
        Cliente clienteAux = clienteRepository.obtenerClienteByCedula(cliente.getCedula());
        if (clienteAux != null) {
            throw new IllegalArgumentException("Esta cédula ya se encuentra registrada");
        }
        clienteRepository.guardarCliente(cliente);
    }
    
    public Cliente obtenerClienteById(Integer id) {
        Cliente cliente = clienteRepository.obtenerClienteById(id);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente con id: " + id + " NO se encuentra registrado");
        }
        return cliente;
    }

    public Cliente obtenerClienteByCedula(String cedula) {
        Cliente cliente = clienteRepository.obtenerClienteByCedula(cedula);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente con cédula: " + cedula + " NO se encuentra registrado");
        }
        return cliente;
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.obtenerClientes();
    }

    @Transactional
    public void actualizarCliente(Cliente cliente) {
        Cliente clienteAux = clienteRepository.obtenerClienteByCedula(cliente.getCedula());
        if (clienteAux == null) {
            throw new IllegalArgumentException("El cliente con cédula: " + cliente.getCedula() + " NO se encuentra registrado, por lo que no se puede actualizar");
        }
        clienteRepository.actualizarCliente(cliente);
        
    }

    @Transactional
    public void eliminarCliente(String cedula) {
        Cliente clienteAux = clienteRepository.obtenerClienteByCedula(cedula);
        if (clienteAux == null) {
            throw new IllegalArgumentException("El cliente con cédula: " + cedula + " NO se encuentra registrado, por lo que no se puede eliminar");
        }
        clienteRepository.eliminarCliente(cedula);
    }
    
}
