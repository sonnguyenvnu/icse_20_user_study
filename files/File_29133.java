package com.sohu.cache.entity;

import java.util.Date;

/**
 * 应用下实例与客户端对应关系
 * 
 * @author leifu
 * @Date 2016年5月3日
 * @Time 下�?�6:50:04
 */
public class AppInstanceClientRelation {

    /**
     * 应用id
     */
    private long appId;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 节点ip
     */
    private String instanceHost;

    /**
     * 节点端�?�
     */
    private int instancePort;

    /**
     * 节点端�?�
     */
    private long instanceId;

    /**
     * 日期
     */
    private Date day;

    public AppInstanceClientRelation(long appId, String clientIp, String instanceHost, int instancePort,
            long instanceId, Date day) {
        this.appId = appId;
        this.clientIp = clientIp;
        this.instanceHost = instanceHost;
        this.instancePort = instancePort;
        this.instanceId = instanceId;
        this.day = day;
    }

    public AppInstanceClientRelation() {
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getInstanceHost() {
        return instanceHost;
    }

    public void setInstanceHost(String instanceHost) {
        this.instanceHost = instanceHost;
    }

    public int getInstancePort() {
        return instancePort;
    }

    public void setInstancePort(int instancePort) {
        this.instancePort = instancePort;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "AppInstanceClientRelation [appId=" + appId + ", clientIp=" + clientIp + ", instanceHost="
                + instanceHost + ", instancePort=" + instancePort + ", instanceId=" + instanceId + ", day=" + day + "]";
    }

    public static AppInstanceClientRelation generateFromAppClientCostTimeStat(
            AppClientCostTimeStat appClientCostTimeStat) {
        if (appClientCostTimeStat == null) {
            return null;
        } else {
            return new AppInstanceClientRelation(appClientCostTimeStat.getAppId(),
                    appClientCostTimeStat.getClientIp(), appClientCostTimeStat.getInstanceHost(), appClientCostTimeStat
                            .getInstancePort(), appClientCostTimeStat.getInstanceId(), new Date(
                            System.currentTimeMillis()));
        }
    }

}
