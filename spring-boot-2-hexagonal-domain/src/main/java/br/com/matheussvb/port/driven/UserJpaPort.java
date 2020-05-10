package br.com.matheussvb.port.driven;

import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.model.user.page.UserPageDTO;

import java.util.List;

public interface UserJpaPort {
    List<UserDTO> findAll();

    UserDTO save(UserDTO userDTO);

    UserPageDTO findPageable(int tamanho, int pagina);

    UserDTO edit(UserDTO userDTO);

    UserDTO findById(Integer id);

    void deletar(UserDTO userDTO);

}
