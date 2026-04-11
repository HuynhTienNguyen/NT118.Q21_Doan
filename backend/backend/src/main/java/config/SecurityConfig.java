package config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS = {
    };
    //gọi đối tượng xử lý HttpSecurity là httpSecurity
    public SecurityConfig(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS ).permitAll()
                        .anyRequest()
                        .authenticated());
    }



}
