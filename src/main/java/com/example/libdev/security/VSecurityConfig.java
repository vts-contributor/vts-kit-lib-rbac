package com.example.libdev.security;

import com.example.libdev.security.jwt.JWTConfigurer;
import com.example.libdev.security.jwt.VTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.filter.CorsFilter;

@Configuration
//@Import(SecurityProblemSupport.class)
@Import(CorsFilter.class)
public class VSecurityConfig {


//    private final JHipsterProperties jHipsterProperties;

    private final VTokenProvider VTokenProvider;

    private final CorsFilter corsFilter;
//    private final SecurityProblemSupport problemSupport;

    public VSecurityConfig(
            VTokenProvider VTokenProvider,
            CorsFilter corsFilter
//            JHipsterProperties jHipsterProperties,
//            SecurityProblemSupport problemSupport
    ) {
        this.VTokenProvider = VTokenProvider;
        this.corsFilter = corsFilter;
//        this.problemSupport = problemSupport;
//        this.jHipsterProperties = jHipsterProperties;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf()
                .disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
//                .authenticationEntryPoint(problemSupport)
//                .accessDeniedHandler(problemSupport)
                .and()
                    .headers()
                    .contentSecurityPolicy(VAuthoritiesConstants.contentSecurityPolicy)
                .and()
                  .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                .and()
                    .permissionsPolicy().policy("camera=(), fullscreen=(self), geolocation=(), gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()")
                .and()
                    .frameOptions()
                    .sameOrigin()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/app/**/*.{js,html}").permitAll()
                    .antMatchers("/i18n/**").permitAll()
                    .antMatchers("/content/**").permitAll()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/test/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/activate").permitAll()
                    .antMatchers("/api/account/reset-password/init").permitAll()
                    .antMatchers("/api/account/reset-password/finish").permitAll()
                    .antMatchers("/api/admin/**").hasAuthority(VAuthoritiesConstants.ADMIN)
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/management/health").permitAll()
                    .antMatchers("/management/health/**").permitAll()
                    .antMatchers("/management/info").permitAll()
                    .antMatchers("/management/prometheus").permitAll()
                    .antMatchers("/management/**").hasAuthority(VAuthoritiesConstants.ADMIN)
                    .anyRequest().authenticated()
                .and()
                    .httpBasic()
                .and()
                    .apply(securityConfigurerAdapter());
        return http.build();
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(VTokenProvider);
    }

}
