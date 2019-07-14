package com.sohu.cache.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 客户端值分布统计
 * @author leifu
 * @Date 2015年1月20日
 * @Time 上�?�11:44:09
 */
public class AppClientValueDistriStat {
    
    private long id;

    /**
     * 应用id
     */
    private long appId;

    /**
     * 格�?yyyyMMddHHmm00
     */
    private long collectTime;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 上报时间
     */
    private Date reportTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 命令
     */
    private String command;

    /**
     * 值分布值
     */
    private String distributeValue;

    /**
     * 值分布类型
     */
    private int distributeType;

    /**
     * 调用次数
     */
    private int count;

    /**
     * 实例ip
     */
    private String instanceHost;

    /**
     * 实例port
     */
    private int instancePort;
    
    /**
     * 实例id
     */
    private long instanceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(long collectTime) {
        this.collectTime = collectTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDistributeValue() {
        return distributeValue;
    }

    public void setDistributeValue(String distributeValue) {
        this.distributeValue = distributeValue;
    }

    public int getDistributeType() {
        return distributeType;
    }

    public void setDistributeType(int distributeType) {
        this.distributeType = distributeType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }
    
}
