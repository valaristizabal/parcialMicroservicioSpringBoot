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

    //caso #3 obtener cliente por el id
    @Test
    void obtenerClienteByIdTestExitoso() {
        when(clienteRepository.obtenerClienteById(1)).thenReturn(cliente1);

        Cliente resultado = clienteService.obtenerClienteById(1);

        assertNotNull(resultado);
        assertEquals("cliente #1", resultado.getNombre());
        verify(clienteRepository, times(1)).obtenerClienteById(1);
    }

    // void obtenerClienteByIdTestIdNoExistente(){
    //     when(clienteRepository.obtenerClienteById(3)).thenReturn(null);
    //     Cliente resultado = clienteService.obtenerClienteById(3);
    // }


    //
    @Test
    void obtenerClientesTest() {
        when(clienteRepository.obtenerClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.obtenerClientes();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        verify(clienteRepository, times(1)).obtenerClientes();
    }
    


}
