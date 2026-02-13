package br.dev.murilo.OSApiApplication.api.controller;

import br.dev.murilo.OSApiApplication.domain.model.Cliente;
import br.dev.murilo.OSApiApplication.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController 
{
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/clientes")
    public List<Cliente> listas()
    {
        //return clienteRepository.findAll();
        return clienteRepository.findByNome("ednhfuoehfgurg");
        // return clienteRepository.findByNomeContaining("Silva");
    }
}
