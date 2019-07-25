package com.github.vole.mps.constants;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * çŸ­ä¿¡é€šé?“æ¨¡æ?¿
 */
public class GrantType {

//    /**
//     * å®¢æˆ·ç«¯éªŒè¯?
//     */
//    CLIENT("client", "å®¢æˆ·ç«¯éªŒè¯?"),
//    /**
//     * ç”¨æˆ·å??å¯†ç ?éªŒè¯?
//     */
//    PASSWORD("password", "ç”¨æˆ·å??å¯†ç ?éªŒè¯?"),
//    /**
//     * åˆ·æ–°éªŒè¯?
//     */
//    REFRESH_TOKEN("refresh_token","åˆ·æ–°éªŒè¯?"),
//    /**
//     * æŽˆæ?ƒéªŒè¯?
//     */
//    AUTHORIZATION_CODE("authorization_code", "æŽˆæ?ƒéªŒè¯?");


    /**
     * æ¨¡æ?¿å??ç§°
     */
    @Getter
    @Setter
    private String key;
    /**
     * æ¨¡æ?¿ç­¾å??
     */
    @Getter
    @Setter
    private String name;

    GrantType(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static List<GrantType> list(){
        List<GrantType>  grantTypes = new ArrayList<GrantType>();
        GrantType client = new GrantType("client", "å®¢æˆ·ç«¯éªŒè¯?");
        grantTypes.add(client);
        GrantType password = new GrantType("password", "ç”¨æˆ·å??å¯†ç ?éªŒè¯?");
        grantTypes.add(password);
        GrantType refreshToken = new GrantType("refresh_token", "åˆ·æ–°éªŒè¯?");
        grantTypes.add(refreshToken);
        GrantType authorizationCode = new GrantType("authorization_code", "æŽˆæ?ƒéªŒè¯?");
        grantTypes.add(authorizationCode);
        return grantTypes;
    }
}
