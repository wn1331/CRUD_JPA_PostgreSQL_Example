package com.example.example.global.config;


import com.example.example.domain.Role;
import com.example.example.domain.user.User;
import com.example.example.global.config.oauth2.OAuth2MemberService;
import com.example.example.global.config.oauth2.PrincipalDetails;
import com.example.example.infrastructure.repository.user.UserRepository;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2MemberService oAuth2MemberService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/oauth/mypage").authenticated();
                auth.anyRequest().permitAll();
            })
            .oauth2Login(oauth2Configurer -> oauth2Configurer
                .loginPage("/oauth/main")
                .successHandler(successHandler()).userInfoEndpoint(
                    userInfoEndpointConfig -> userInfoEndpointConfig.userService(
                        oAuth2MemberService))
//                .defaultSuccessUrl("/oauth/mypage",true)

            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            PrincipalDetails defaultOAuth2User = (PrincipalDetails) authentication.getPrincipal();
//            System.out.println(defaultOAuth2User.getAttributes());
            String id = defaultOAuth2User.getAttributes().get("email").toString();
            String body = """
                {"id":"%s"}
                """.formatted(id);
            System.out.println("sdkdjskdjsdjskdj");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            PrintWriter writer = response.getWriter();
            writer.println(body);
            writer.flush();
        });
    }


}
