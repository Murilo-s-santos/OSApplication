package br.dev.murilo.OSApiApplication.domain.dto;

import br.dev.murilo.OSApiApplication.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

public record AtualizaStatusDTO(
    
        @NotNull(message = "Status é obrigatório")
        StatusOrdemServico status
){}
