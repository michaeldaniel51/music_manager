package danny.musicmanager.securities;


import danny.musicmanager.jwt.UserJwtAuthenticationFilter;
import danny.musicmanager.jwt.UserJwtAuthorizationFilter;
import danny.musicmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userService;
    private final String expires;

    public SecurityConfig(UserService userService, @Value( "${app.token.expires:5}") String expires){
        this.userService = userService;
        this.expires = expires;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests().antMatchers("/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST,"/users/**","/auth/users","/auth/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/users/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new UserJwtAuthenticationFilter(authenticationManager(),expires))
                .addFilter(new UserJwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                "/configuration/security", "/configuration/**", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**", "/h2-console");
    }

        @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
