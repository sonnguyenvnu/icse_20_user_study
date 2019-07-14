package com.sohu.cache.entity;

import java.util.Date;

/**
 * 实例�?置模�?�
 * @author leifu
 * @Date 2016年6月22日
 * @Time 下�?�5:45:29
 */
public class InstanceConfig {
    
    private long id;
    
    /**
     * �?置�??:为了防止与key冲�?
     */
    private String configKey;
    
    /**
     * �?置值:为了防止与value冲�?
     */
    private String configValue;
    
    /**
     * �?置说明
     */
    private String info;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * Redis类型(�?�考ConstUtil)
     */
    private int type;
    
    /**
     * 状�?，1有效0无效
     */
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }
    
    public String getStatusDesc() {
        if (1 == status) {
            return "有效";
        } else if (0 == status) {
            return "无效";
        } else {
            return "";
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public boolean isEffective() {
        if (1 == getStatus()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "InstanceConfig [id=" + id + ", configKey=" + configKey + ", configValue=" + configValue
                + ", info=" + info  + ", updateTime=" + updateTime
                + ", type=" + type + ", status=" + status + "]";
    }
    
    

    
}
