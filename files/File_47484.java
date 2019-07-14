package com.us.example.domain;

/**
 * Created by yangyibo on 17/1/20.
 */
public class Permission {

    private int id;
    //æ?ƒé™?å??ç§°
    private String name;

    //æ?ƒé™?æ??è¿°
    private String descritpion;

    //æŽˆæ?ƒé“¾æŽ¥
    private String url;

    //çˆ¶èŠ‚ç‚¹id
    private int pid;

    //è¯·æ±‚æ–¹æ³•
    private String method;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
