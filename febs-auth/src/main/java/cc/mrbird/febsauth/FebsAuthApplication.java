package cc.mrbird.febsauth;

import cc.mrbird.febs.common.annotation.FebsCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@FebsCloudApplication
public class FebsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsAuthApplication.class, args);
    }

}
