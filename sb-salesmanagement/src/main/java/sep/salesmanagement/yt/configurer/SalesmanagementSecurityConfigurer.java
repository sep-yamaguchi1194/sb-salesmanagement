package sep.salesmanagement.yt.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SalesmanagementSecurityConfigurer extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
        //未ログイン時でもアクセスできるURIの指定(つまり、ログインページ)
                .mvcMatchers("/salesmanagement/login").permitAll()
        //未ログイン時のログインページ以外へのアクセスを許可しない
                .anyRequest().authenticated()
                .and()
        //CSRF対策を無効化
                .csrf().disable();

        httpSecurity.formLogin()
                .loginProcessingUrl("/salesmanagement/login")
                .loginPage("/salesmanagement/login")
                .defaultSuccessUrl("/salesmanagement/order_list");
    }
}
