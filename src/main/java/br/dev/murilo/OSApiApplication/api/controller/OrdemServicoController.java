package br.dev.murilo.OSApiApplication.api.controller;

import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import br.dev.murilo.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.dev.murilo.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController 
{
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico)
    {
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping
    public List<OrdemServico> listar()
    {
        return ordemServicoRepository.findAll(); 
    }
    
    @GetMapping("/{ordem_servicoID}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordem_servicoID)
    {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordem_servicoID);
        
        if(ordemServico.isPresent())
        {
            // Retorna status 200 (OK) e o corpo da Ordem de Serviço
            return ResponseEntity.ok(ordemServico.get());
        }
        else
        {
            // Retorna status 404 (Not Found) se não existir no banco
            return ResponseEntity.notFound().build();
        }
    }
    
@PutMapping("/{ordem_servicoID}")
    public ResponseEntity<OrdemServico> atualizar(@PathVariable Long ordem_servicoID,
                                                  @Valid @RequestBody OrdemServico ordemServico)
    {
        // Busca a Ordem de Serviço original no banco de dados
        Optional<OrdemServico> osAtual = ordemServicoRepository.findById(ordem_servicoID);
        
        // Se não existir, retorna 404 Not Found
        if(osAtual.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        
        OrdemServico osExistente = osAtual.get();
        
        osExistente.setDescricao(ordemServico.getDescricao());
        osExistente.setPreco(ordemServico.getPreco());
        osExistente.setCliente(ordemServico.getCliente());
        
        osExistente = ordemServicoService.salvar(osExistente);
        
        return ResponseEntity.ok(osExistente);
    }
    
    @DeleteMapping("/{ordem_servicoID}")
    public ResponseEntity<Void> apagar(@PathVariable Long ordem_servicoID)
    {
        // Verifica se a ordem de serviço existe na base de dados
        if(!ordemServicoRepository.existsById(ordem_servicoID))
        {
            // Se não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
        
        // Se existir, chama o serviço para apagar
        ordemServicoService.apagar(ordem_servicoID);
        
        return ResponseEntity.noContent().build();
    }
    
    
    
    
    
}
