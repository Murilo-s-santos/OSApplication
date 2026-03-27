package br.dev.murilo.OSApiApplication.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioInputDTO 
{
    @NotBlank(message = "A descrição do comentário é obrigatória.")
    private String descricao;

    public String getDescricao() 
    { 
        return descricao; 
    }
    public void setDescricao(String descricao) 
    { 
        this.descricao = descricao; 
    }
}
