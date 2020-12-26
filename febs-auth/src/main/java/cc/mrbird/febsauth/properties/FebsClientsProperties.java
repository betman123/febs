package cc.mrbird.febsauth.properties;

import lombok.Data;

@Data
public class FebsClientsProperties {
    private String client;
    private String secret;
    /**对应当前令牌支持的认证类型*/
    private String grantType = "password,authorization_code,refresh_token";
    /**对应认证范围*/
    private String scope = "all";
}