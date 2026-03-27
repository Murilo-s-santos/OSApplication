package br.dev.murilo.OSApiApplication.domain.repository;

import br.dev.murilo.OSApiApplication.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>
{
    
}
