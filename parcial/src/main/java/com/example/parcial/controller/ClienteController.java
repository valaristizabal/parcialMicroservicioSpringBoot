package com.example.parcial.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

    /**
     * * Endpoint para guardar un cliente
     * Se utiliza ResponseEntity para manejar la respuesta HTTP
     * El método retorna un mensaje de éxito o error dependiendo del resultado de la operación
     */
    @PostMapping("/guardar-cliente")
    public ResponseEntity<String> guardarCliente(@RequestBody Cliente cliente){
        try{
            clienteService.guardarCliente(cliente);
            return ResponseEntity.status(200).body("Cliente guardado exitosamente");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * * Endpoint para obtener un cliente por su ID
     * Se utiliza ResponseEntity para manejar la respuesta HTTP
     * El método retorna ? ya que puede retornar un cliente (cuando es exitoso) o un mensaje de error (cuando no se encuentra el cliente)
     */
    @GetMapping("/cliente-id/{id}")
    public ResponseEntity<?> obtenerClienteById(@PathVariable Integer id) {
        try {
            Cliente cliente = clienteService.obtenerClienteById(id);
            return ResponseEntity.status(200).body(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    /**
     * * Endpoint para obtener un cliente por su cédula
     * Se utiliza ResponseEntity para manejar la respuesta HTTP
     * El método retorna ? ya que puede retornar un cliente (cuando es exitoso) o un mensaje de error (cuando no se encuentra el cliente)
     */
    @GetMapping("/cliente-cedula/{cedula}")
    public ResponseEntity<?> obtenerClienteByCedula(@PathVariable String cedula) {
        try {
            Cliente cliente = clienteService.obtenerClienteByCedula(cedula);
            return ResponseEntity.status(200).body(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    /**
     * * Endpoint para obtener todos los clientes
     */
    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    /**
     * * Endpoint para actualizar un cliente
     * Se utiliza ResponseEntity para manejar la respuesta HTTP
     * El método retorna un mensaje de éxito o error dependiendo del resultado de la operación
     */
    @PutMapping("/actualizar-cliente")
    public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.actualizarCliente(cliente);
            return ResponseEntity.status(200).body("Cliente actualizado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * * Endpoint para eliminar un cliente
     * Se utiliza ResponseEntity para manejar la respuesta HTTP
     * El método retorna un mensaje de éxito o error dependiendo del resultado de la operación
     */
    @DeleteMapping("/eliminar-cliente/{cedula}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String cedula) {
        try {
            clienteService.eliminarCliente(cedula);
            return ResponseEntity.status(200).body("Cliente eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
