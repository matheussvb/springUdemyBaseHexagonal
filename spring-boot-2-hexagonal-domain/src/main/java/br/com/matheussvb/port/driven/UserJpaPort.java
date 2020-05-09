package br.com.matheussvb.port.driven;

import br.com.matheussvb.model.user.UserDTO;

import java.util.List;

public interface UserJpaPort {
    List<UserDTO> findAll();
    void save(UserDTO userDTO);
}
