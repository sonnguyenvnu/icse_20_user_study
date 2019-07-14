package com.sohu.cache.entity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 基于应用全局耗时统计(uniquekey: app_id, command, collect_time)
 * @author leifu
 * @Date 2015年6月26日
 * @Time 下�?�4:26:54
 */
public class AppClientCostTimeTotalStat {
    
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 命令
     */
    private String command;

    /**
     * 调用总次数
     */
    private long totalCount;
    
    /**
     * 调用总耗时
     */
    private double totalCost;

    /**
     * 中�?值
     */
    private int median;

    /**
     * 平�?�值
     */
    private double mean;

    /**
     * 90%最大值
     */
    private int ninetyPercentMax;

    /**
     * 99%最大值
     */
    private int ninetyNinePercentMax;

    /**
     * 100%最大值
     */
    private int hundredMax;
    
    /**
     * 实例ip
     */
    private String maxInstanceHost;

    /**
     * 实例port
     */
    private int maxInstancePort;
    
    /**
     * 实例id
     */
    private long maxInstanceId;
    
    /**
     * 客户端
     */
    private String maxClientIp;
    

    public AppClientCostTimeTotalStat(long id, long appId, long collectTime, Date createTime, String command,
            long totalCount, double totalCost, int median, double mean, int ninetyPercentMax, int ninetyNinePercentMax,
            int hundredMax, String maxInstanceHost, int maxInstancePort, long maxInstanceId, String maxClientIp) {
        this.id = id;
        this.appId = appId;
        this.collectTime = collectTime;
        this.createTime = createTime;
        this.command = command;
        this.totalCount = totalCount;
        this.totalCost = totalCost;
        this.median = median;
        this.mean = mean;
        this.ninetyPercentMax = ninetyPercentMax;
        this.ninetyNinePercentMax = ninetyNinePercentMax;
        this.hundredMax = hundredMax;
        this.maxInstanceHost = maxInstanceHost;
        this.maxInstancePort = maxInstancePort;
        this.maxInstanceId = maxInstanceId;
        this.maxClientIp = maxClientIp;
    }

    public AppClientCostTimeTotalStat() {
    }

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

    public int getMedian() {
        return median;
    }

    public void setMedian(int median) {
        this.median = median;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public int getNinetyPercentMax() {
        return ninetyPercentMax;
    }

    public void setNinetyPercentMax(int ninetyPercentMax) {
        this.ninetyPercentMax = ninetyPercentMax;
    }

    public int getNinetyNinePercentMax() {
        return ninetyNinePercentMax;
    }

    public void setNinetyNinePercentMax(int ninetyNinePercentMax) {
        this.ninetyNinePercentMax = ninetyNinePercentMax;
    }

    public int getHundredMax() {
        return hundredMax;
    }

    public void setHundredMax(int hundredMax) {
        this.hundredMax = hundredMax;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getMaxInstanceHost() {
        return maxInstanceHost;
    }

    public void setMaxInstanceHost(String maxInstanceHost) {
        this.maxInstanceHost = maxInstanceHost;
    }

    public int getMaxInstancePort() {
        return maxInstancePort;
    }

    public void setMaxInstancePort(int maxInstancePort) {
        this.maxInstancePort = maxInstancePort;
    }

    public long getMaxInstanceId() {
        return maxInstanceId;
    }

    public void setMaxInstanceId(long maxInstanceId) {
        this.maxInstanceId = maxInstanceId;
    }

    public String getMaxClientIp() {
        return maxClientIp;
    }

    public void setMaxClientIp(String maxClientIp) {
        this.maxClientIp = maxClientIp;
    }
    
    public Long getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = sdf.parse(String.valueOf(this.collectTime));
            return date.getTime();
        } catch (ParseException e) {
            return 0L;
        }
        
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

    public static AppClientCostTimeTotalStat getFromAppClientCostTimeStat(AppClientCostTimeStat stat) {
        AppClientCostTimeTotalStat appClientCostTimeTotalStat = new AppClientCostTimeTotalStat();
        appClientCostTimeTotalStat.setAppId(stat.getAppId());
        appClientCostTimeTotalStat.setCollectTime(stat.getCollectTime());
        appClientCostTimeTotalStat.setCommand(stat.getCommand());
        appClientCostTimeTotalStat.setCreateTime(stat.getCreateTime());
        appClientCostTimeTotalStat.setMean(stat.getMean());
        appClientCostTimeTotalStat.setMedian(stat.getMedian());
        appClientCostTimeTotalStat.setHundredMax(stat.getHundredMax());
        appClientCostTimeTotalStat.setNinetyPercentMax(stat.getNinetyPercentMax());
        appClientCostTimeTotalStat.setNinetyNinePercentMax(stat.getNinetyNinePercentMax());
        appClientCostTimeTotalStat.setMaxClientIp(stat.getClientIp());
        appClientCostTimeTotalStat.setMaxInstanceHost(stat.getInstanceHost());
        appClientCostTimeTotalStat.setMaxInstancePort(stat.getInstancePort());
        appClientCostTimeTotalStat.setMaxInstanceId(stat.getInstanceId());
        appClientCostTimeTotalStat.setMaxClientIp(stat.getClientIp());
        //�?留两�?�?数
        DecimalFormat df = new DecimalFormat("#.00");
        appClientCostTimeTotalStat.setTotalCost(NumberUtils.toDouble(df.format(stat.getMean() * stat.getCount())));
        appClientCostTimeTotalStat.setTotalCount(stat.getCount());
        return appClientCostTimeTotalStat;
    }
    
}
