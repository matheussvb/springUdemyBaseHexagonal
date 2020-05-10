package br.com.matheussvb.model.user.page;

import br.com.matheussvb.model.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPageDTO {
    private List<UserDTO> userDTO;
    private InformacoesPaginaDTO pagina;
}
