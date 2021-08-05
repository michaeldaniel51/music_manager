package danny.musicmanager.dtos;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLogin {


    @NotNull(message = "Email is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;





}
