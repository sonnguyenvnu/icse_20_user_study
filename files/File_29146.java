package com.sohu.cache.entity;

import com.sohu.cache.constant.InstanceStatusEnum;
import com.sohu.cache.util.ConstUtils;

import java.io.Serializable;

/**
 * 实例信�?�
 * User: lingguo
 * Date: 14-5-29
 * Time: 下�?�9:38
 */
public class InstanceInfo implements Serializable {
    private static final long serialVersionUID = -903896025243493024L;

    /**
     * 实例id
     */
    private int id;

    /**
     * 应用id
     */
    private long appId;

    /**
     * host id
     */
    private long hostId;

    /**
     * ip
     */
    private String ip;

    /**
     * 端�?�
     */
    private int port;

    /**
     * 是�?��?�用 0:节点异常,1:正常�?�用,2:节点下线
     */
    private int status;

    /**
     * 开�?�的内存
     */
    private int mem;

    /**
     * 连接数
     */
    private int conn;

    /**
     * �?�动命令 或者 redis-sentinel的masterName
     */
    private String cmd;

    private int type;

    private String typeDesc;

    private int masterInstanceId;

    private String masterHost;

    private int masterPort;

    private String roleDesc;
    
    private int groupId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMem() {
        return mem;
    }

    public void setMem(int mem) {
        this.mem = mem;
    }

    public int getConn() {
        return conn;
    }

    public void setConn(int conn) {
        this.conn = conn;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeDesc() {
        if (type <= 0) {
            return "";
        } else if (type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER) {
            return "redis-cluster";
        } else if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
            return "redis-sentinel";
        } else if (type == ConstUtils.CACHE_REDIS_STANDALONE) {
            return "redis-standalone";
        }
        return "";
    }

    @Override
    public String toString() {
        return "InstanceInfo{" +
                "id=" + id +
                ", appId=" + appId +
                ", hostId=" + hostId +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", status=" + status +
                ", mem=" + mem +
                ", conn=" + conn +
                ", cmd='" + cmd + '\'' +
                ", type=" + type +
                ", group=" + groupId +
                '}';
    }

    public int getMasterInstanceId() {
        return masterInstanceId;
    }

    public void setMasterInstanceId(int masterInstanceId) {
        this.masterInstanceId = masterInstanceId;
    }

    public String getMasterHost() {
        return masterHost;
    }

    public void setMasterHost(String masterHost) {
        this.masterHost = masterHost;
    }

    public int getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getStatusDesc() {
        InstanceStatusEnum instanceStatusEnum = InstanceStatusEnum.getByStatus(status);
        if (instanceStatusEnum != null) {
            return instanceStatusEnum.getInfo();
        }
        return "";
    }

    /**
     * 判断当�?节点是�?�下线
     *
     * @return
     */
    public boolean isOffline() {
        if (status == InstanceStatusEnum.OFFLINE_STATUS.getStatus()) {
            return true;
        }
        return false;
    }

    public void setRoleDesc(Boolean isMaster) {
        if (isMaster == null) {
            roleDesc = "未知";
        } else if (isMaster) {
            roleDesc = "master";
        } else {
            roleDesc = "slave";
        }
    }

    public String getRoleDesc() {
        if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
            return "sentinel";
        } else {
            return roleDesc;
        }
    }
    
    public String getHostPort() {
    	return ip + ":" + port;
    }

}
