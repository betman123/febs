package cc.mrbird.febs.server.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "FEBS-Server-System",contextId = "helloServiceClient",
        fallbackFactory = HelloServiceFallback .class)
public interface IHelloService {
    @GetMapping("hello")
    String hello(@RequestParam String name);

}

/**
 *@FeignClient注解标注表明这是一个Feign Client
 * value指定远程服务的名称;
 * contextId指定这个Feign Client的别名;
 * fallbackFactory指定了回退方法,当我们调用远程服务出现异常时执行。
 */
