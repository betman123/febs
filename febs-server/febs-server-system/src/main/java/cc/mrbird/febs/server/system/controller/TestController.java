package cc.mrbird.febs.server.system.controller;

import netscape.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("info")
    public String test(){
        return "febs-server-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name){
        return "hello " + name;
    }
}
