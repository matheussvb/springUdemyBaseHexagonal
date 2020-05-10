package br.com.matheussvb.service;

import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.model.user.page.UserPageDTO;
import br.com.matheussvb.port.driven.UserJpaPort;
import br.com.matheussvb.port.driver.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service("UserAdapter")
public class UserAdapter implements UserPort {

    @Autowired
    private UserJpaPort userJpaPort;

    @Override
    public List<UserDTO> findAll() {
        if (userJpaPort.findAll().isEmpty()) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "nenhum usuário localizado");
        }
        return userJpaPort.findAll();
    }

    @Override
    public UserPageDTO findPageable(int tamanho, int pagina) {
        return userJpaPort.findPageable(tamanho, pagina);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userJpaPort.save(userDTO);
    }

    @Override
    public UserDTO edit(UserDTO userDTO) {
        UserDTO byId = userJpaPort.findById(userDTO.getId().intValue());
        if (null != byId.getId() && !"".equals(byId.getId())) {
            return userJpaPort.edit(userDTO);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    @Override
    public UserDTO findById(Integer id) {
        return userJpaPort.findById(id);
    }

    @Override
    public void deletar(Integer id) {
        UserDTO userDTO = userJpaPort.findById(id);
        userJpaPort.deletar(userDTO);
    }
}
