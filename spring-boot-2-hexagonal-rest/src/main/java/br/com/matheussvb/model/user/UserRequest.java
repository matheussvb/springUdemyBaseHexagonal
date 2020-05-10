package br.com.matheussvb.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequest {

    @NotNull(message = "nome invalido")
    private String name;
    @NotNull(message = "email invalido")
    private String email;
    @NotNull(message = "senha invalida")
    private String password;

}
