package danny.musicmanager.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String title;
    private int status;
    private String detail;
    private String timestamp;
    private Map<String, List<ValidationError>> errors = new HashMap<>();



}
