package br.dev.murilo.OSApiApplication.domain.service;

import br.dev.murilo.OSApiApplication.domain.exception.DomainException;
import br.dev.murilo.OSApiApplication.domain.model.Cliente;
import br.dev.murilo.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService 
{
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente)
    {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        // Verifica se o cliente existe
        if (clienteExistente != null && !clienteExistente.equals(cliente))
        {
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }
        
        return clienteRepository.save(cliente);
    }
    
    public void excluir(Long clienteId)
    {
        clienteRepository.deleteById(clienteId);
    }
}
