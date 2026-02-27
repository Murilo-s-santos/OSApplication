package br.dev.murilo.OSApiApplication.domain.service;

import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import br.dev.murilo.OSApiApplication.domain.model.StatusOrdemServico;
import br.dev.murilo.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
