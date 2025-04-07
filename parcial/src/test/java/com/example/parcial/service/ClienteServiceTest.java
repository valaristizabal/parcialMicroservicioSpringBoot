package com.example.parcial.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.parcial.model.Cliente;
import com.example.parcial.repository.ClienteRepository;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;


    private Cliente cliente1;
    private Cliente cliente2;
    
    @BeforeEach
    void setUp() {
        cliente1 = new Cliente("cliente #1", "11111", "pruebaUno@gmail.com", "12345", 1);
        cliente2 = new Cliente("cliente #2", "22222", "pruebaDos@gmail.com", "6789", 2);
    }

    //caso #1 guardar cliente exitosamente
    @Test
    void guardarClienteTestExitoso(){
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(null);
        clienteService.guardarCliente(cliente1);
        verify(clienteRepository, times(1)).guardarCliente(cliente1);
    }

    //caso #2 guardar cliente con cedula duplicada
    @Test
    void guardarClienteTestCedulaDuplicada() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(cliente1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.guardarCliente(cliente1);
        });

        assertEquals("Esta cédula ya se encuentra registrada", exception.getMessage());
        verify(clienteRepository, never()).guardarCliente(cliente1);
    }

    //caso #3 obtener cliente por el id exitoso
    @Test
    void obtenerClienteByIdTestExitoso() {
        when(clienteRepository.obtenerClienteById(1)).thenReturn(cliente1);

        Cliente resultado = clienteService.obtenerClienteById(1);

        assertNotNull(resultado);
        assertEquals("cliente #1", resultado.getNombre());
        verify(clienteRepository, times(1)).obtenerClienteById(1);
    }

    //caso #4 obtener cliente por el id no existente
    @Test
    void obtenerClienteByIdTestIdNoExistente(){
        when(clienteRepository.obtenerClienteById(3)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.obtenerClienteById(3);
        });

        assertEquals("El cliente con id: 3 NO se encuentra registrado", exception.getMessage());
        verify(clienteRepository, times(1)).obtenerClienteById(3);
    }

    //caso #5 obtener cliente por cedula exitoso
    @Test
    void obtenerClienteByCedulaTestExitoso() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(cliente1);

        Cliente resultado = clienteService.obtenerClienteByCedula(cliente1.getCedula());

        assertNotNull(resultado);
        assertEquals("cliente #1", resultado.getNombre());
        verify(clienteRepository, times(1)).obtenerClienteByCedula(cliente1.getCedula());
    }

    //caso #6 obtener cliente por cedula no existente
    @Test
    void obtenerClienteByCedulaTestCedulaNoExistente() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.obtenerClienteByCedula(cliente1.getCedula());
        });

        assertEquals("El cliente con cédula: " + cliente1.getCedula() + " NO se encuentra registrado", exception.getMessage());
        verify(clienteRepository, times(1)).obtenerClienteByCedula(cliente1.getCedula());
    }

    //caso #7 obtener todos los clientes exitoso
    @Test
    void obtenerClientesTestExitoso() {
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.obtenerClientes()).thenReturn(clientes);

        List<Cliente> resultado = clienteService.obtenerClientes();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(clienteRepository, times(1)).obtenerClientes();
    }
    
    //caso #8 actualizar cliente exitoso
    @Test
    void actualizarClienteTestExito() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(cliente1);

        clienteService.actualizarCliente(cliente1);

        verify(clienteRepository, times(1)).actualizarCliente(cliente1);
    }
    //caso #9 actualizar cliente no existente
    @Test
    void actualizarClienteTestClienteNoExistente() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.actualizarCliente(cliente1);
        });

        assertEquals("El cliente con cédula: " + cliente1.getCedula() + " NO se encuentra registrado, por lo que no se puede actualizar", exception.getMessage());
        verify(clienteRepository, never()).actualizarCliente(cliente1);
    }

    //caso #10 eliminar cliente exitoso
    @Test
    void eliminarClienteTestExitoso() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(cliente1);

        clienteService.eliminarCliente(cliente1.getCedula());

        verify(clienteRepository, times(1)).eliminarCliente(cliente1.getCedula());
    }

    //caso #11 eliminar cliente no existente
    @Test
    void eliminarClienteTestClienteNoExistente() {
        when(clienteRepository.obtenerClienteByCedula(cliente1.getCedula())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.eliminarCliente(cliente1.getCedula());
        });

        assertEquals("El cliente con cédula: " + cliente1.getCedula() + " NO se encuentra registrado, por lo que no se puede eliminar", exception.getMessage());
        verify(clienteRepository, never()).eliminarCliente(cliente1.getCedula());
    }

}
