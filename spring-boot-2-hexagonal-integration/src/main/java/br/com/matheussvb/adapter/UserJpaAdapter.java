package br.com.matheussvb.adapter;

import br.com.matheussvb.entity.UserEntity;
import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.model.user.page.InformacoesPaginaDTO;
import br.com.matheussvb.model.user.page.UserPageDTO;
import br.com.matheussvb.port.driven.UserJpaPort;
import br.com.matheussvb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service("UserJpaAdapter")
public class UserJpaAdapter implements UserJpaPort {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> findAll = userRepository.findAll();
        Type type = new TypeToken<List<UserDTO>>() {
        }.getType();
        List<UserDTO> listaConvertida = modelMapper.map(findAll, type);
        return listaConvertida;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userRepository.save(modelMapper.map(userDTO, UserEntity.class));
        UserDTO userSaveDto = modelMapper.map(userEntity, UserDTO.class);
        return userSaveDto;
    }

    @Override
    public UserPageDTO findPageable(int tamanho, int pagina) {
        Page<UserEntity> userEntity = userRepository.findAll(PageRequest.of(pagina, tamanho));

        List<UserEntity> content = userEntity.getContent();
        List<UserDTO> userPageDTOList = content.stream().map(
                user -> {
                    return UserDTO.builder()
                            .password(user.getPassword())
                            .name(user.getName())
                            .id(user.getId())
                            .email(user.getEmail())
                            .build();
                }).collect(Collectors.toList());
        InformacoesPaginaDTO informacoesPaginaDTO = InformacoesPaginaDTO.builder()
                .pagina(userEntity.getPageable().getPageNumber())
                .tamanho(userEntity.getPageable().getPageSize())
                .total(userEntity.getTotalElements())
                .build();
        return UserPageDTO.builder()
                .userDTO(userPageDTOList)
                .pagina(informacoesPaginaDTO)
                .build();


//        InformacoesPaginaDTO informacoesPaginaDTO = InformacoesPaginaDTO.builder()
//                .pagina(userEntityPage.getPageable().getPageNumber())
//                .tamanho(userEntityPage.getPageable().getPageSize())
//                .total(userEntityPage.getTotalElements())
//                .build();

    }

    @Override
    public UserDTO edit(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity save = userRepository.save(userEntity);
        return modelMapper.map(save, UserDTO.class);
    }

    @Override
    public UserDTO findById(Integer id) {
        UserDTO userDTO = userRepository.findById((long) id).map(
                userEntity -> {
                    return UserDTO.builder()
                            .email(userEntity.getEmail())
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .password(userEntity.getPassword())
                            .build();
                }).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user não encontrado")
        );
        return userDTO;
    }

    @Override
    public void deletar(UserDTO userDTO) {
        userRepository.delete(modelMapper.map(userDTO, UserEntity.class));
    }

}
