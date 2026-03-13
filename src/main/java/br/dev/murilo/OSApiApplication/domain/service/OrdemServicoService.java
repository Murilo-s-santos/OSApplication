package br.dev.murilo.OSApiApplication.domain.service;

import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import br.dev.murilo.OSApiApplication.domain.model.StatusOrdemServico;
import br.dev.murilo.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OrdemServicoService 
{
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico)
    {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    public OrdemServico salvar(OrdemServico os)
    {
        return ordemServicoRepository.save(os);
    }
    
    public void apagar(Long id)
    {
        ordemServicoRepository.deleteById(id);
    }
    
}
