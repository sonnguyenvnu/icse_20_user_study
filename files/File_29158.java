package com.sohu.cache.entity;


import java.util.Date;

import com.sohu.cache.constant.MachineInfoEnum;

/**
 * 机器的属性信�?�
 *
 * Created by lingguo on 14-6-27.
 */
public class MachineInfo {
    /**
     * 机器id
     */
    private long id;
    
    /**
     * ssh用户�??
     */
    private String sshUser;
    
    /**
     * ssh密�?
     */
    private String sshPasswd;
    
    /**
     * ip地�?�
     */
    private String ip;
    
    /**
     * 机房
     */
    private String room;
    
    /**
     * 内存，�?��?G
     */
    private int mem;
    
    /**
     * cpu数�?
     */
    private int cpu;
    
    /**
     * 是�?�虚机，0�?�，1是
     */
    private int virtual;
    
    /**
     * 宿主机ip
     */
    private String realIp; 
    
    /**
     * 上线时间
     */
    private Date serviceTime;
    
    /**
     * 故障次数
     */
    private int faultCount;
    
    /**
     * 修改时间
     */
    private Date modifyTime;
    
    /**
     * 是�?��?�用报警，0�?�，1是
     */
    private int warn;
    
    /**
     * 是�?��?�用，MachineInfoEnum.AvailableEnum
     */
    private int available;
    
    /**
     * 机器类型：详�?MachineInfoEnum.TypeEnum
     */
    private int type;           
    
    /**
     * groupId
     */
    private int groupId;
    
    /**
     * �?外说明:(例如本机器有其他web或者其他�?务)
     */
    private String extraDesc;
    
    /**
     * 是�?�收集�?务器信�?�，0�?�，1是
     */
    private int collect;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}

	public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshPasswd() {
        return sshPasswd;
    }

    public void setSshPasswd(String sshPasswd) {
        this.sshPasswd = sshPasswd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getMem() {
        return mem;
    }

    public void setMem(int mem) {
        this.mem = mem;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getVirtual() {
        return virtual;
    }

    public void setVirtual(int virtual) {
        this.virtual = virtual;
    }

    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getFaultCount() {
        return faultCount;
    }

    public void setFaultCount(int faultCount) {
        this.faultCount = faultCount;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getWarn() {
        return warn;
    }

    public void setWarn(int warn) {
        this.warn = warn;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getExtraDesc() {
        return extraDesc;
    }

    public void setExtraDesc(String extraDesc) {
        this.extraDesc = extraDesc;
    }
    
    @Override
    public String toString() {
        return "MachineInfo{" +
                "id=" + id +
                ", sshUser='" + sshUser + '\'' +
                ", sshPasswd='" + sshPasswd + '\'' +
                ", ip='" + ip + '\'' +
                ", room='" + room + '\'' +
                ", mem=" + mem +
                ", cpu=" + cpu +
                ", virtual=" + virtual +
                ", realIp='" + realIp + '\'' +
                ", serviceTime=" + serviceTime +
                ", faultCount=" + faultCount +
                ", modifyTime=" + modifyTime +
                ", warn=" + warn +
                ", available=" + available +
                ", type=" + type +
                ", groupId=" + groupId +
                ", extraDesc=" + extraDesc +
                ", collect=" + collect +
                '}';
    }

    /**
     * 判断机器是�?�已�?下线
     * @return
     */
    public boolean isOffline() {
        return MachineInfoEnum.AvailableEnum.NO.getValue() == this.available;
    }
}
