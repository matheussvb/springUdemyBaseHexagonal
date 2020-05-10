package br.com.matheussvb.port.driver;

import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.model.user.page.UserPageDTO;

import java.util.List;

public interface UserPort {
    List<UserDTO> findAll();

    UserPageDTO findPageable(int tamanho, int pagina);

    UserDTO save(UserDTO userDTO);

    UserDTO edit(UserDTO userDTO);

    UserDTO findById(Integer id);

    void deletar(Integer id);
}

