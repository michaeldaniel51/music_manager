package danny.musicmanager.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import danny.musicmanager.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class UserJwtAuthorizationFilter extends BasicAuthenticationFilter {


    public UserJwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{


        String header = req.getHeader("Authorization");


        System.out.println("str=art");

        if (header == null || !header.startsWith("Bearer ")){

            chain.doFilter(req, res);
            return;

        }

        System.out.println("end");

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req,res);

    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req){

        try{

            String token = req.getHeader("Authorization");

            if (token != null){

                String user = JWT.require(Algorithm.HMAC512(TokenUtils.SECRET_KEY.getBytes()))
                        .build()
                        .verify(token.replace("Bearer ",""))
                        .getSubject();

                if(user != null){

                    return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
                }

                return null;
            }


        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

}
