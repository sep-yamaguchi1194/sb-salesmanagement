package sep.salesmanagement.yt.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sep.salesmanagement.yt.service.SalesManagementUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SalesmanagementSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Bean
    PasswordEncoder passwordEncoder() {
        //ハッシュ化無し(非推奨)
        return NoOpPasswordEncoder.getInstance();
    }

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {

	}

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
        //未ログイン時でもアクセスできるURIの指定
                .mvcMatchers("/salesmanagement/login").permitAll()
        //CSS, JavaScriptファイルのアクセス許可
                .antMatchers("/css/**","/js/**").permitAll()
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
                .usernameParameter("username")
                .passwordParameter("password")
        //ログイン認証成功時のデフォルト遷移先URI
                .defaultSuccessUrl("/salesmanagement/order_list");

    }

    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
    	@Autowired
    	SalesManagementUserDetailService salesManagementUserDetailService;

    }
}
