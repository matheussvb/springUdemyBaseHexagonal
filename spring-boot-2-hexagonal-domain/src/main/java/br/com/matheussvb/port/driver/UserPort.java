package br.com.matheussvb.port.driver;

import br.com.matheussvb.model.user.UserDTO;

import java.util.List;

public interface UserPort {
    List<UserDTO> findAll();
    void save(UserDTO userDTO);
}
