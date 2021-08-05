package danny.musicmanager.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static danny.musicmanager.dtos.ResponseStatus.Created;
import static danny.musicmanager.dtos.ResponseStatus.Successful;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {



    private int code;
    private ResponseStatus status;
    private String description;
    private T data;
    private T error;



    private Response(){}

    public static <T> Response <T> build(ResponseStatus status,T data){

        Response<T> response = new Response<>();
        response.setCode(status.getCode());
        response.setStatus(status);
        response.setDescription(status.getMessage());

        if((status.equals(Successful)) || status.equals(Created)){
            response.setData(data);
        }else {

            response.setError(data);
        }

        return response;

    }


}
