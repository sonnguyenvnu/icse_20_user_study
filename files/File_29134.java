package com.sohu.cache.entity;

import com.sohu.cache.web.util.Page;

/**
 * �?�索实体
 * 
 * @author leifu
 * @Date 2014年11月14日
 * @Time 上�?�10:44:01
 */
public class AppSearch {
    /**
     * 应用id
     */
    private Long appId;

    /**
     * 应用�??
     */
    private String appName;

    /**
     * 应用类型
     */
    private Integer appType;

    /**
     * 申请状�?
     */
    private Integer appStatus;
    
    /**
     * 命中率排�?
     */
    private String orderBy;
    
    /**
     * �?�?度
     */
    private Integer importantLevel;
    
    /**
     * 分页
     */
    private Page page;
    
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(Integer importantLevel) {
        this.importantLevel = importantLevel;
    }

    @Override
    public String toString() {
        return "AppSearch [appId=" + appId + ", appName=" + appName + ", appType=" + appType + ", appStatus="
                + appStatus + ", orderBy=" + orderBy + ", importantLevel=" + importantLevel + ", page=" + page + "]";
    }


}
