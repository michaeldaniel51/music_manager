package danny.musicmanager.errors;

import lombok.Data;

@Data
public class ValidationError {


    private String code;
    private String message;

}
