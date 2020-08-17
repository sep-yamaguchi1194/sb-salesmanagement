package sep.salesmanagement.yt.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import sep.salesmanagement.yt.service.SalesManagementUserDetailService;

@Configuration
@EnableWebSecurity
public class SalesmanagementSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private SalesManagementUserDetailService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setSalesManagementUserDetailService(SalesManagementUserDetailService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        //passwordのBCryptPasswordEncoderを使用
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
        //未ログイン時でもアクセスできるURIの指定
                .mvcMatchers("/salesmanagement/login","/salesmanagement/signup", "/salesmanagement/signup_confirm").permitAll()
        //CSS, JavaScriptファイルのアクセス許可
                .antMatchers("/css/**","/js/**").permitAll()
                .antMatchers("/salesmanagement/user_create").hasRole("ADMIN")
        //未ログイン時のログインページ以外へのアクセスを許可しない
                .anyRequest().authenticated()
                .and()
        //CSRF対策を無効化
                .csrf().disable();

        httpSecurity.formLogin()
        //ログイン認証に使うaction:URI
                .loginProcessingUrl("/salesmanagement/login")
        //ログインページURI
                .loginPage("/salesmanagement/login")
                //ログインページの<input>のname属性
                .usernameParameter("email")
                .passwordParameter("password")
        //ログイン認証成功時のデフォルト遷移先URI
                .defaultSuccessUrl("/salesmanagement/order_list")
            .and()
                .logout()
                .logoutUrl("/salesmanagement/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                //Cookie削除
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .eraseCredentials(true)
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    private LogoutSuccessHandler logoutSuccessHandler = (request, response, auth) -> {
        if(response.isCommitted()) {
            return;
        }
        if(request.isRequestedSessionIdValid()) {
            request.changeSessionId();
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, "/salesmanagement/login");
    };
    /*
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        SalesManagementUserDetailService salesManagementUserDetailService;

    }
    */
}
