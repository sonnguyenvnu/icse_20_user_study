package com.sohu.cache.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.sohu.cache.redis.enums.InstanceAlertCheckCycleEnum;
import com.sohu.cache.redis.enums.InstanceAlertCompareTypeEnum;
import com.sohu.cache.redis.enums.InstanceAlertTypeEnum;

/**
 * 实例报警阀值�?置
 * @author leifu
 * @Date 2017年5月19日
 * @Time 上�?�11:09:16
 */
public class InstanceAlertConfig {
    
    /**
     * 自增id
     */
    private long id;
    
    /**
     * 报警�?置
     */
    private String alertConfig;

    /**
     * 报警阀值
     */
    private String alertValue;
    
    /**
     * 详�?CompareTypeEnumNew
     */
    private int compareType;

    /**
     * �?置说明
     */
    private String configInfo;
    
    /**
     * 详�?TypeEnum
     */
    private int type;
    
    /**
     * -1全局�?置，其他代表实例id
     */
    private long instanceId;
    
    /**
     * 实例信�?�
     */
    private InstanceInfo instanceInfo;

    /**
     * 相关StatusEnum
     */
    private int status;

    /**
     * 详�?CheckCycleEnum
     */
    private int checkCycle;
    
    /**
     * �?置更新时间
     */
    private Date updateTime;
    
    /**
     * 上次检测时间
     */
    private Date lastCheckTime;

    public InstanceAlertConfig() {
        super();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlertConfig() {
        return alertConfig;
    }

    public void setAlertConfig(String alertConfig) {
        this.alertConfig = alertConfig;
    }

    public String getAlertValue() {
        return alertValue;
    }

    public void setAlertValue(String alertValue) {
        this.alertValue = alertValue;
    }

    public int getCompareType() {
        return compareType;
    }
    
    public String getCompareInfo() {
        return InstanceAlertCompareTypeEnum.getInstanceAlertCompareTypeEnum(compareType).getInfo();
    }

    public void setCompareType(int compareType) {
        this.compareType = compareType;
    }

    public String getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(String configInfo) {
        this.configInfo = configInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCheckCycle() {
        return checkCycle;
    }

    public void setCheckCycle(int checkCycle) {
        this.checkCycle = checkCycle;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Date lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }
    
    public InstanceInfo getInstanceInfo() {
        return instanceInfo;
    }

    public void setInstanceInfo(InstanceInfo instanceInfo) {
        this.instanceInfo = instanceInfo;
    }

    public Long getCheckCycleMillionTime() {
        if (InstanceAlertCheckCycleEnum.ONE_MINUTE.getValue() == checkCycle) {
            return TimeUnit.MINUTES.toMillis(1);
        } else if (InstanceAlertCheckCycleEnum.FIVE_MINUTE.getValue() == checkCycle) {
            return TimeUnit.MINUTES.toMillis(5);
        } else if (InstanceAlertCheckCycleEnum.HALF_HOUR.getValue() == checkCycle) {
            return TimeUnit.MINUTES.toMillis(30);
        } else if (InstanceAlertCheckCycleEnum.ONE_HOUR.getValue() == checkCycle) {
            return TimeUnit.MINUTES.toMillis(60);
        } else if (InstanceAlertCheckCycleEnum.ONE_DAY.getValue() == checkCycle) {
            return TimeUnit.DAYS.toMillis(1);
        } 
        return null;
    }
    
    public boolean isSpecail() {
        return instanceId > 0 && type == InstanceAlertTypeEnum.INSTANCE_ALERT.getValue();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
