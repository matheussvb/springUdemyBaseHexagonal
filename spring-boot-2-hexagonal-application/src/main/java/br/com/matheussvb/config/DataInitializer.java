package br.com.matheussvb.config;

import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.port.driver.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserPort userPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<UserDTO> users = userPort.findAll();
        if (users.isEmpty()) {
            this.createUsers(UserDTO.builder()
                    .password(passwordEncoder.encode("123456"))
                    .password("123456")
                    .name("Matheus1")
                    .email("admin")
                    .build());
            this.createUsers(UserDTO.builder()
                    .password(passwordEncoder.encode("matheus"))
                    .password("123456")
                    .name("Matheus2")
                    .email("matheus2.svb@hotmail.com")
                    .build());
            this.createUsers(UserDTO.builder()
                    .password(passwordEncoder.encode("matheus"))
                    .password("123456")
                    .name("Matheus3")
                    .email("matheus3.svb@hotmail.com")
                    .build());
        }
    }

    public void createUsers(UserDTO userDTO) {
        userPort.save(userDTO);
    }
}
