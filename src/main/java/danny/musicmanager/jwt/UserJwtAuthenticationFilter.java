package danny.musicmanager.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import danny.musicmanager.dtos.Response;
import danny.musicmanager.dtos.ResponseStatus;
import danny.musicmanager.dtos.TokenResponse;
import danny.musicmanager.dtos.UserLogin;
import danny.musicmanager.entities.User;
import danny.musicmanager.exceptions.CustomException;
import danny.musicmanager.utils.TokenUtils;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Long.parseLong;
import static java.time.LocalDateTime.now;
import static java.util.Date.from;


public class UserJwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String tokenExpiresAt;


    public UserJwtAuthenticationFilter(AuthenticationManager authenticationManager, String tokenExpiresAt){
        this.authenticationManager = authenticationManager;
        this.tokenExpiresAt = tokenExpiresAt;

        setFilterProcessesUrl("/auth/users");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {

        try {

            UserLogin creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLogin.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new CustomException(e.getMessage(), getClass());
        }
    }

        @Override
         protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
                FilterChain chain,
        Authentication auth) throws IOException{

        User user = (User)auth.getPrincipal();
        Date issuedAt = from(Instant.now());
        Date expiresAt = from(now().plusMinutes(parseLong(tokenExpiresAt)).toInstant(ZoneOffset.UTC));

        String token = com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(issuedAt)
                .withClaim("first_name",user.getUsername())
                .withClaim("phone_number", user.getPhoneNumber())
                .sign(Algorithm.HMAC512(TokenUtils.SECRET_KEY.getBytes()));


    TokenResponse tokenResponse = TokenResponse.builder()
            .expiresAt(expiresAt)
            .issuedAt(issuedAt)
            .token(token)
            .build();

            Response<?> response = Response.build( ResponseStatus.Successful,tokenResponse);

            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response));
            res.getWriter().flush();
        }

        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException{


        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Response<?> res = Response.build(ResponseStatus.Unauthorized,"Incorrect Credentials");


        response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(res));
        response.getWriter().flush();

    }


}






