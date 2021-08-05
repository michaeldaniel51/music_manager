package danny.musicmanager.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import danny.musicmanager.dtos.Response;
import danny.musicmanager.dtos.UserLogin;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final String PORT;
    private final ObjectMapper objectMapper;
//    private final String contextPath;




    @Autowired
    public AuthenticationController(@Value("${server.port}") String PORT,
                                    ObjectMapper objectMapper) {
        this.PORT = PORT;
        this.objectMapper = objectMapper;
      //  this.contextPath = contextPath;
    }


    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLogin userLogin) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("http://localhost:" + PORT + "/auth/users");
        httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(userLogin)));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        Response<?> responseEntity = objectMapper.readValue(response.getEntity().getContent(), Response.class);

        if (response.getStatusLine().getStatusCode() == 200) {
            return ok(responseEntity);

        } else {
            return status(401).contentType(APPLICATION_JSON)
                    .body(responseEntity);
        }

    }

}
