package br.dev.murilo.OSApiApplication.domain.dto;

import java.time.LocalDateTime;

public class ComentarioDTO 
{
    private Long id;
    private String descricao;
    private LocalDateTime dataEnvio;

    // Getters e Setters
    public Long getId() 
    { 
        return id; 
    }
    public void setId(Long id) 
    { 
        this.id = id; 
    }

    public String getDescricao() 
    { 
        return descricao; 
    }
    public void setDescricao(String descricao) 
    { 
        this.descricao = descricao; 
    }

    public LocalDateTime getDataEnvio()
    { return dataEnvio; 
    }
    public void setDataEnvio(LocalDateTime dataEnvio) 
    { 
        this.dataEnvio = dataEnvio; 
    }
}
