package br.dev.murilo.OSApiApplication.domain.repository;

import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>
{
    
}
