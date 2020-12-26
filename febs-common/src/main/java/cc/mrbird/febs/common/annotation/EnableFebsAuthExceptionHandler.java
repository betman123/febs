package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.configure.FebsAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsAuthExceptionConfigure.class)
public @interface EnableFebsAuthExceptionHandler {

}

/*
 * 使用@Import将FebsAuthExceptionConfigure配置类引入了进来.
 *
 * 因为febs-auth，febs-server-system，febs-server-test都是资源服务器，
 * 所以它们三个都需要使用@EnableFebsAuthExceptionHandler注解实现资源服务器异常处理
 */
