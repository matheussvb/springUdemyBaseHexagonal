package br.com.matheussvb.config;

import br.com.matheussvb.model.user.UserDTO;
import br.com.matheussvb.port.driver.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserPort userPort;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<UserDTO> users = userPort.findAll();
        if (users.isEmpty()) {
            this.createUsers(UserDTO.builder()
                    .password("matheus")
                    .name("Matheus1")
                    .email("matheus1.svb@hotmail.com")
                    .build());
            this.createUsers(UserDTO.builder()
                    .password("matheus")
                    .name("Matheus2")
                    .email("matheus2.svb@hotmail.com")
                    .build());
            this.createUsers(UserDTO.builder()
                    .password("matheus")
                    .name("Matheus3")
                    .email("matheus3.svb@hotmail.com")
                    .build());
        }
    }

    public void createUsers(UserDTO userDTO) {
        userPort.save(userDTO);
    }
}
