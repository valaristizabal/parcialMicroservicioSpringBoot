package com.example.parcial.repository;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.parcial.model.Cliente;

import jakarta.persistence.*;


@Repository
public class ClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void guardarCliente(Cliente cliente){
        entityManager.persist(cliente);
    }

    public Cliente obtenerClienteById(Integer id){
        return entityManager.find(Cliente.class, id);
    }

    public Cliente obtenerClienteByCedula(String cedula){
        List<Cliente> clientes = entityManager
        .createQuery("SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class)
        .setParameter("cedula", cedula)
        .getResultList();

        if (clientes.isEmpty()) {
            return null;
        } else {
            return clientes.get(0);
        }
    }

    public List<Cliente> obtenerClientes(){
        return entityManager.createQuery("Select c FROM Cliente c", Cliente.class).getResultList();
    }

    public void actualizarCliente(Cliente cliente){
        entityManager.merge(cliente);
    }
    
    public void eliminarCliente(String cedula){
        Cliente cliente = obtenerClienteByCedula(cedula);
        if (cliente != null) {
            entityManager.remove(cliente);
        }
    }
}
