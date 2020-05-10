package br.com.matheussvb.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonTypeName("data")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class UserResponse {

    @JsonProperty("user_id")
    private Long id;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("user_email")
    private String email;
    @JsonProperty("user_password")
    private String password;

}
