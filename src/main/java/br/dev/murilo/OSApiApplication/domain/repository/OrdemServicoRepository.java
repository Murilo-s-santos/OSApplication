package br.dev.murilo.OSApiApplication.domain.repository;

import br.dev.murilo.OSApiApplication.domain.model.OrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>
{
    List<OrdemServico> findByClienteId(Long clienteId);
}
