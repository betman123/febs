package cc.mrbird.febsauth.configure;

import cc.mrbird.febsauth.service.FebsUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**WebSecurityConfigurerAdapter适配器用于通过会话对用户进行身份验证,
 * 默认情况下spring security的http配置
*/
@Order(2)
@EnableWebSecurity
public class FebsSecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private FebsUserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 该类是一个接口，定义了几个和密码加密校验相关的方法，这里我们使用的是Spring Security内部实现好的BCryptPasswordEncoder
     * ,BCryptPasswordEncoder的特点就是，对于一个相同的密码，每次加密出来的加密串都不同
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //安全配置类只对/oauth/开头的请求有效
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
