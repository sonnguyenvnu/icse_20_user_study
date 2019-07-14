package com.sohu.cache.entity;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;

import com.alibaba.fastjson.JSONObject;
import com.sohu.cache.web.vo.AppDetailVO;

/**
 * 应用日报数�?�
 * @author leifu
 * @Date 2016年8月10日
 * @Time 下�?�5:12:45
 */
public class AppDailyData {

    /**
     * 应用id
     */
    private long appId;
    
    /**
     * 开始日期
     */
    private Date startDate;

    
    /**
     * 结�?�日期
     */
    private Date endDate;
    
    /**
     * 日期
     */
    private Date date;
    
    /**
     * bigkey次数
     */
    private long bigKeyTimes;
    
    /**
     * bigkey信�?�
     */
    private String bigKeyInfo;
    
    /**
     * 慢查询次数
     */
    private long slowLogCount;
    
    /**
     * 客户端异常个数
     */
    private long clientExceptionCount;
    
    /**
     * �?分钟最大客户端连接数
     */
    private long maxMinuteClientCount;
    
    /**
     * �?分钟平�?�客户端连接数
     */
    private long avgMinuteClientCount;
    
    /**
     * �?分钟最大命令数
     */
    private long maxMinuteCommandCount;
    
    /**
     * �?分钟平�?�命令数
     */
    private long avgMinuteCommandCount;
    
    /**
     * 平�?�命中率
     */
    private double avgHitRatio;
    
    /**
     * �?分钟最�?命中率
     */
    private double minMinuteHitRatio;
    
    /**
     * �?分钟最大命中率
     */
    private double maxMinuteHitRatio;
    
    /**
     * 平�?�内存使用�?
     */
    private long avgUsedMemory;
    
    /**
     * 最大内存使用�?
     */
    private long maxUsedMemory;
    
    /**
     * 过期键个数
     */
    private long expiredKeysCount;
    
    /**
     * 剔除键个数
     */
    private long evictedKeysCount;
    
    /**
     * �?分钟平�?�网络input�?
     */
    private double avgMinuteNetInputByte;
    
    /**
     * �?分钟最大网络input�?
     */
    private double maxMinuteNetInputByte;
    
    /**
     * �?分钟平�?�网络output�?
     */
    private double avgMinuteNetOutputByte;
    
    /**
     * �?分钟最大网络output�?
     */
    private double maxMinuteNetOutputByte;
    
    /**
     * 键个数平�?�值
     */
    private long avgObjectSize;
    
    /**
     * 键个数最大值
     */
    private long maxObjectSize;
    
    /**
     * 值分布
     */
    private Map<String, Long> valueSizeDistributeCountMap;
    
    /**
     * 应用详情
     */
    private AppDetailVO appDetailVO;

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getSlowLogCount() {
        return slowLogCount;
    }

    public void setSlowLogCount(long slowLogCount) {
        this.slowLogCount = slowLogCount;
    }

    public long getClientExceptionCount() {
        return clientExceptionCount;
    }

    public void setClientExceptionCount(long clientExceptionCount) {
        this.clientExceptionCount = clientExceptionCount;
    }

    public long getMaxMinuteClientCount() {
        return maxMinuteClientCount;
    }

    public void setMaxMinuteClientCount(long maxMinuteClientCount) {
        this.maxMinuteClientCount = maxMinuteClientCount;
    }

    public long getAvgMinuteClientCount() {
        return avgMinuteClientCount;
    }

    public void setAvgMinuteClientCount(long avgMinuteClientCount) {
        this.avgMinuteClientCount = avgMinuteClientCount;
    }

    public long getMaxMinuteCommandCount() {
        return maxMinuteCommandCount;
    }

    public void setMaxMinuteCommandCount(long maxMinuteCommandCount) {
        this.maxMinuteCommandCount = maxMinuteCommandCount;
    }

    public long getAvgMinuteCommandCount() {
        return avgMinuteCommandCount;
    }

    public void setAvgMinuteCommandCount(long avgMinuteCommandCount) {
        this.avgMinuteCommandCount = avgMinuteCommandCount;
    }

    public double getAvgHitRatio() {
        return avgHitRatio;
    }

    public void setAvgHitRatio(double avgHitRatio) {
        this.avgHitRatio = avgHitRatio;
    }

    public double getMinMinuteHitRatio() {
        return minMinuteHitRatio;
    }

    public void setMinMinuteHitRatio(double minMinuteHitRatio) {
        this.minMinuteHitRatio = minMinuteHitRatio;
    }

    public double getMaxMinuteHitRatio() {
        return maxMinuteHitRatio;
    }

    public void setMaxMinuteHitRatio(double maxMinuteHitRatio) {
        this.maxMinuteHitRatio = maxMinuteHitRatio;
    }

    public long getAvgUsedMemory() {
        return avgUsedMemory;
    }

    public void setAvgUsedMemory(long avgUsedMemory) {
        this.avgUsedMemory = avgUsedMemory;
    }

    public long getMaxUsedMemory() {
        return maxUsedMemory;
    }

    public void setMaxUsedMemory(long maxUsedMemory) {
        this.maxUsedMemory = maxUsedMemory;
    }

    public long getExpiredKeysCount() {
        return expiredKeysCount;
    }

    public void setExpiredKeysCount(long expiredKeysCount) {
        this.expiredKeysCount = expiredKeysCount;
    }

    public long getEvictedKeysCount() {
        return evictedKeysCount;
    }

    public void setEvictedKeysCount(long evictedKeysCount) {
        this.evictedKeysCount = evictedKeysCount;
    }

    public double getAvgMinuteNetInputByte() {
        return avgMinuteNetInputByte;
    }

    public void setAvgMinuteNetInputByte(double avgMinuteNetInputByte) {
        this.avgMinuteNetInputByte = avgMinuteNetInputByte;
    }

    public double getMaxMinuteNetInputByte() {
        return maxMinuteNetInputByte;
    }

    public void setMaxMinuteNetInputByte(double maxMinuteNetInputByte) {
        this.maxMinuteNetInputByte = maxMinuteNetInputByte;
    }

    public double getAvgMinuteNetOutputByte() {
        return avgMinuteNetOutputByte;
    }

    public void setAvgMinuteNetOutputByte(double avgMinuteNetOutputByte) {
        this.avgMinuteNetOutputByte = avgMinuteNetOutputByte;
    }

    public double getMaxMinuteNetOutputByte() {
        return maxMinuteNetOutputByte;
    }

    public void setMaxMinuteNetOutputByte(double maxMinuteNetOutputByte) {
        this.maxMinuteNetOutputByte = maxMinuteNetOutputByte;
    }

    public long getAvgObjectSize() {
        return avgObjectSize;
    }

    public void setAvgObjectSize(long avgObjectSize) {
        this.avgObjectSize = avgObjectSize;
    }

    public long getMaxObjectSize() {
        return maxObjectSize;
    }

    public void setMaxObjectSize(long maxObjectSize) {
        this.maxObjectSize = maxObjectSize;
    }

    public Map<String, Long> getValueSizeDistributeCountMap() {
        return valueSizeDistributeCountMap;
    }
    
    public String getValueSizeDistributeCountDesc() {
        if (MapUtils.isEmpty(valueSizeDistributeCountMap)) {
            return "无";
        }
        StringBuffer desc = new StringBuffer();
        for(Entry<String, Long> entry : valueSizeDistributeCountMap.entrySet()) {
            desc.append(entry.getKey()).append(":").append(entry.getValue()).append("次<br/>");
        }
        return desc.toString();
    }
    
    public String getValueSizeDistributeCountDescHtml() {
        return bigKeyInfo.replace("\n", "<br/>").replace(":", ":\t");
    }

    public void setValueSizeDistributeCountMap(Map<String, Long> valueSizeDistributeCountMap) {
        this.valueSizeDistributeCountMap = valueSizeDistributeCountMap;
    }

    public AppDetailVO getAppDetailVO() {
        return appDetailVO;
    }

    public void setAppDetailVO(AppDetailVO appDetailVO) {
        this.appDetailVO = appDetailVO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getBigKeyTimes() {
        return bigKeyTimes;
    }

    public void setBigKeyTimes(long bigKeyTimes) {
        this.bigKeyTimes = bigKeyTimes;
    }

    public String getBigKeyInfo() {
        return bigKeyInfo;
    }

    public void setBigKeyInfo(String bigKeyInfo) {
        this.bigKeyInfo = bigKeyInfo;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
