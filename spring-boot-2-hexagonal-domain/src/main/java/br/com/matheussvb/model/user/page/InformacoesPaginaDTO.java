package br.com.matheussvb.model.user.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPaginaDTO {
    private long total;
    private int pagina;
    private int tamanho;
}
