package xdu.bdilab.springbootstarter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import xdu.bdilab.springbootstarter.common.CodeAndMsg;
import xdu.bdilab.springbootstarter.common.CustomPermissionEvaluator;
import xdu.bdilab.springbootstarter.common.Response;
import xdu.bdilab.springbootstarter.filter.JwtAuthenticationTokenFilter;
import xdu.bdilab.springbootstarter.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cwz
 * @date 2019/05/12
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private String loginUrl = "/login";

    private String logoutUrl = "/logout";

    private String sessionHeader = "JSESSIONID";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/token/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/trace/users/**","/swagger-ui.html","/swagger-resources/**","/images/**",
                        "/webjars/**","/v2/api-docs","/configuration/ui","/configuration/security").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                    .loginProcessingUrl(loginUrl).permitAll()
                    .successHandler(new AjaxAuthenticationSuccessHandler())
                    .failureHandler(new AjaxAuthenticationFailureHandler())
                .and().logout()
                    .logoutUrl(logoutUrl)
                    .logoutSuccessHandler(new AjaxLogoutSuccessHandler())
                    .invalidateHttpSession(true)
                    .deleteCookies(sessionHeader)
                .and().exceptionHandling()
                    .accessDeniedHandler(new AjaxAccessDeniedHandler())
                    .authenticationEntryPoint(new AjaxAuthenticationEntryPoint());
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    /**
     * 匿名用户访问无权限资源处理
     * 401
     */
    private class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                AuthenticationException e
        ) throws IOException, ServletException {
            Response response = Response.failed(CodeAndMsg.authorization_failed);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            mapper.writeValue(
                    httpServletResponse.getWriter(),
                    response);
        }
    }


    /**
     * 登录失败处理类
     * 401
     */
    private class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                AuthenticationException e
        ) throws IOException, ServletException {
            Response response = Response.failed(CodeAndMsg.authentication_failed);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            mapper.writeValue(
                    httpServletResponse.getWriter(),
                    response);
        }
    }


    /**
     * 登录成功处理类
     * 200
     */
    private class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                Authentication authentication
        ) throws IOException, ServletException {
            Response response = Response.succeed(CodeAndMsg.login_succeed, userService.findByUsername(authentication.getName()));
            httpServletResponse.setStatus(HttpStatus.ACCEPTED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            mapper.writeValue(
                    httpServletResponse.getWriter(),
                    response);
        }
    }


    /**
     * 登出成功处理类
     * 200
     */
    private class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                Authentication authentication
        ) throws IOException, ServletException {
            Response response = Response.succeed(CodeAndMsg.logout_succeed, null);
            httpServletResponse.setStatus(HttpStatus.ACCEPTED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            mapper.writeValue(
                    httpServletResponse.getWriter(),
                    response);
        }
    }

    /**
     * 无权限处理类
     * 403
     */
    private class AjaxAccessDeniedHandler implements AccessDeniedHandler {

        @Override
        public void handle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             AccessDeniedException e) throws IOException, ServletException {
            Response response = Response.failed(CodeAndMsg.authorization_failed);
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            mapper.writeValue(
                    httpServletResponse.getWriter(),
                    response);
        }
    }
}
