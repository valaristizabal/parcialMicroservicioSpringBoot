package com.example.parcial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.parcial.model.Cliente;
import com.example.parcial.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void guardarCliente(Cliente cliente) {
        Cliente clienteAux = clienteRepository.obtenerClienteByCedula(cliente.getCedula());
        if (clienteAux != null) {
            throw new IllegalArgumentException("Esta cédula ya se encuentra registrada");
        }
        clienteRepository.guardarCliente(cliente);
    }
    

    public Cliente obtenerClienteById(Integer id) {
        return clienteRepository.obtenerClienteById(id);
    }

    public Cliente obtenerClienteByCedula(String cedula) {
        return clienteRepository.obtenerClienteByCedula(cedula);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.obtenerClientes();
    }

    public void actualizarCliente(Cliente cliente) {
        clienteRepository.actualizarCliente(cliente);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.eliminarCliente(id);
    }
    
}
