package br.com.matheussvb.service;

import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.port.driven.UserJpaPort;
import br.com.matheussvb.port.driver.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
        if(userJpaPort.findAll().isEmpty()){
           // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "nenhum usuário localizado");
        }
        return userJpaPort.findAll();
    }

    @Override
    public void save(UserDTO userDTO) {
        userJpaPort.save(userDTO);
    }
}
