package br.com.matheussvb.controller;

import br.com.matheussvb.model.RestResponse;
import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.model.user.UserRequest;
import br.com.matheussvb.model.user.UserResponse;
import br.com.matheussvb.model.user.page.UserPageDTO;
import br.com.matheussvb.model.user.page.UserPageResponse;
import br.com.matheussvb.port.driver.UserPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserPort userPort;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public RestResponse<List<UserResponse>> list() {
        List<UserResponse> allUser = userPort.findAll().stream()
                .map(
                        user -> UserResponse.builder()
                                .email(user.getEmail())
                                .id(user.getId())
                                .name(user.getName())
                                .password(user.getPassword())
                                .build()
                ).collect(Collectors.toList());

        return new RestResponse<>(allUser);
    }

    @GetMapping(value = "/paginacao")
    public RestResponse<UserPageResponse> list(@RequestParam(value = "page") int pagina,
                                               @RequestParam("size") int tamanho) {

        UserPageDTO pageable = userPort.findPageable(tamanho, pagina);
        UserPageResponse userPageResponse = UserPageResponse.builder()
                .pagina(pageable.getPagina())
                .userDTO(pageable.getUserDTO())
                .build();
        return new RestResponse<>(userPageResponse);
//        Page<UserEntity> pageable = userPort.findPageable(tamanho, pagina);
//        return new RestResponse<>(pageable);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<UserResponse> save(@RequestBody @Valid UserRequest userRequest) {
        UserDTO userDTO = UserDTO.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .build();

        UserDTO userSave = userPort.save(userDTO);

        UserResponse userResponse = UserResponse.builder()
                .email(userSave.getEmail())
                .id(userSave.getId())
                .name(userSave.getName())
                .password(userSave.getPassword())
                .build();

        return new RestResponse<>(userResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public RestResponse<UserResponse> edit(@RequestBody @Valid UserRequest userRequest,
                                           @PathVariable("id")
                                           @NotBlank(message = "necessário informar o ID")
                                           @Size(min = 1, max = 100, message = "tamanho de 1 - 100")
                                                   Integer id) {
        UserDTO userDTO = modelMapper.map(userRequest, UserDTO.class);
        userDTO.setId(Long.valueOf(id));
        UserDTO userEditado = userPort.edit(userDTO);
        return new RestResponse<>(modelMapper.map(userEditado, UserResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") Integer id) {
        userPort.deletar(id);
    }
}
