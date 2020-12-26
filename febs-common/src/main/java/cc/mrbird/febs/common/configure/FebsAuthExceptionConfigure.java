package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.handler.FebsAccessDeniedHandler;
import cc.mrbird.febs.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 因為并不是一个Spring Boot项目，所以即使在这两个类上使用@Component注解标注，
 * 它们也不能被成功注册到各个微服务子系统的Spring IOC容器中。
 * 使用@Enable模块驱动的方式来解决这个问题
 *
 * 定义了一个注解来驱动该配置类EnableFebsAuthExceptionHandler
 */
public class FebsAuthExceptionConfigure {
    /**
     * @ConditionalOnMissingBean 注解的意思是，当IOC容器中没有指定名称或类型的Bean的时候，就注册它
     * 这样做的好处在于，子系统可以自定义自个儿的资源服务器异常处理器，覆盖我们在febs-common通用模块里定义的。
     */
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public FebsAccessDeniedHandler accessDeniedHandler(){
        return new FebsAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public FebsAuthExceptionEntryPoint authenticationEntryPoint() {
        return new FebsAuthExceptionEntryPoint();
    }

}
