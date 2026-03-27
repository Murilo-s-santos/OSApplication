package br.dev.murilo.OSApiApplication.api.controller;

import br.dev.murilo.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.dev.murilo.OSApiApplication.domain.dto.ComentarioDTO;
import br.dev.murilo.OSApiApplication.domain.dto.ComentarioInputDTO;
import br.dev.murilo.OSApiApplication.domain.model.Comentario;
import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import br.dev.murilo.OSApiApplication.domain.model.StatusOrdemServico;
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

import br.dev.murilo.OSApiApplication.domain.dto.ComentarioDTO;
import br.dev.murilo.OSApiApplication.domain.dto.ComentarioInputDTO;
import br.dev.murilo.OSApiApplication.domain.exception.DomainException;
import br.dev.murilo.OSApiApplication.domain.model.Comentario;
import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<OrdemServico>> listarPorCliente(@PathVariable Long clienteId)
    {
        // Chama o método mágico que criamos no repositório
        List<OrdemServico> ordens = ordemServicoRepository.findByClienteId(clienteId);
        
        // Retorna a lista com status 200 OK (mesmo se a lista estiver vazia, é o correto!)
        return ResponseEntity.ok(ordens);
    }
    
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
        @PathVariable Long ordemServicoID,
        @Valid @RequestBody AtualizaStatusDTO statusDTO)
    {
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
                ordemServicoID,
                statusDTO.status());
        
        if (optOS.isPresent())
        {
            return ResponseEntity.ok(optOS.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{ordemServicoId}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioDTO adicionarComentario(@PathVariable Long ordemServicoId,
                                              @Valid @RequestBody ComentarioInputDTO comentarioInput)
    {
        Comentario comentarioSalvo = ordemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
        return toModel(comentarioSalvo);
    }
    
    @GetMapping("/{ordemServicoId}/comentarios")
    public List<ComentarioDTO> listarComentarios(@PathVariable Long ordemServicoId)
    {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new DomainException("Ordem de servico nao encontrada"));
        
        return toCollectionModel(ordemServico.getComentarios());
    }
    
    private ComentarioDTO toModel(Comentario comentario)
    {
        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(comentario.getId());
        dto.setDescricao(comentario.getDescricao());
        dto.setDataEnvio(comentario.getDataEnvio());
        return dto;
    }
    
    private List<ComentarioDTO> toCollectionModel(List<Comentario> comentarios)
    {
        return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
    }
    
    
    
    
    
}
