package br.com.matheussvb.controller;

import br.com.matheussvb.model.RestResponse;
import br.com.matheussvb.model.user.UserResponse;
import br.com.matheussvb.port.driver.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserPort userPort;

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
}
