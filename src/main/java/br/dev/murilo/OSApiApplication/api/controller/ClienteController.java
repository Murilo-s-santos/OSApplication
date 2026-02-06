package br.dev.murilo.OSApiApplication.api.controller;

import br.dev.murilo.OSApiApplication.domain.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController 
{
    List<Cliente> listaClientes;
    
    @GetMapping("/clientes")
    public List<Cliente> listas()
    {
        listaClientes = new ArrayList<Cliente>();
        
        listaClientes.add(new Cliente(1, "KGe", "Kge@teste.com", "11-99999-9999"));
        listaClientes.add(new Cliente(1, "Maria", "maria@teste.com", "11-88888-8888"));
        listaClientes.add(new Cliente(1, "João", "joao@teste.com", "11-77777-7777"));
        
        return listaClientes;
    }
}
