package com.example.parcial.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.parcial.model.Cliente;
import com.example.parcial.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@ActiveProfiles("test")
@SpringBootTest
public class ClienteRepositoryIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @Transactional
    public void guardarClienteTest(){
        Cliente cliente = new Cliente("Juan", "12345", "juan@example.com", "3000000000", 20);
        clienteRepository.guardarCliente(cliente);
        assertNotNull(cliente.getId());
        assertEquals("Juan", cliente.getNombre());

    }

    @Test
    @Transactional
    public void obtenerClienteByIdTest(){
        Cliente cliente = new Cliente("Ana", "45678", "ana@example.com", "4000000000", 21);
        clienteRepository.guardarCliente(cliente);
        Cliente clienteObtenido = clienteRepository.obtenerClienteById(cliente.getId());
        assertNotNull(clienteObtenido);
        assertEquals("Ana", clienteObtenido.getNombre());
    }

    @Test
    @Transactional
    public void obtenerClienteByCedulaTest(){
        Cliente cliente = new Cliente("Carlos", "2468", "carlos@example.com", "1000000000", 10);
        clienteRepository.guardarCliente(cliente);
        Cliente clienteObtenido = clienteRepository.obtenerClienteByCedula(cliente.getCedula());
        assertNotNull(clienteObtenido);
        assertEquals("Carlos", clienteObtenido.getNombre());
    }

    @Test
    @Transactional
    public void actualizarClienteTest(){
        Cliente cliente = new Cliente("Isa", "3579", "isa@example.com", "6000000000", 59);
        clienteRepository.guardarCliente(cliente);
        cliente.setNombre("Isabel");

        clienteRepository.actualizarCliente(cliente);

        Cliente clienteActualizado = clienteRepository.obtenerClienteByCedula(cliente.getCedula());
        assertNotNull(clienteActualizado);
        assertEquals("Isabel", clienteActualizado.getNombre());
    }

    @Test 
    @Transactional
    public void eliminarClienteTest(){
        Cliente cliente = new Cliente("Abi", "1654", "abi@example.com", "7000000000", 20);
        clienteRepository.guardarCliente(cliente);
        clienteRepository.eliminarCliente(cliente.getCedula());
        Cliente clienteEliminado = clienteRepository.obtenerClienteByCedula(cliente.getCedula());
        assertEquals(null, clienteEliminado);

    }
}
