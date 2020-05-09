package br.com.matheussvb.adapter;

import br.com.matheussvb.entity.UserEntity;
import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.port.driven.UserJpaPort;
import br.com.matheussvb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service("UserJpaAdapter")
public class UserJpaAdapter implements UserJpaPort {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> findAll = userRepository.findAll();
        Type type = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> listaConvertida = modelMapper.map(findAll, type);
        return listaConvertida;
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, UserEntity.class));
    }
}
