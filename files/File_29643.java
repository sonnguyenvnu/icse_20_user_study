package com.sohu.cache.web.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.sohu.cache.entity.AppDesc;
import com.sohu.cache.entity.AppUser;

/**
 * 应用详情
 * @author leifu
 * @Time 2014年8月29日
 */
public class AppDetailVO {

    private AppDesc appDesc;

    /**
     * 内存空间
     */
    private long mem;

    /**
     * 当�?内存
     */
    private long currentMem;

    /**
     * 机器数
     */
    private int machineNum;

    /**
     * 主节点数
     */
    private int masterNum;

    /**
     * 从节点数
     */
    private int slaveNum;

    /**
     * 当�?对象数
     */
    private long currentObjNum;
    
    /**
     * 当�?连接数
     */
    private int conn;

    /**
     * 内存使用报警
     */
    private double memUseThreshold;

    /**
     * 命中率使用报警
     */
    private double hitPercentThreshold;

    /**
     * 内存使用率
     */
    private double memUsePercent;

    /**
     * 命中率
     */
    private double hitPercent;
    
    /**
     * 应用对应的用户
     */
    private List<AppUser> appUsers;

    public AppDesc getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(AppDesc appDesc) {
        this.appDesc = appDesc;
    }

    public long getMem() {
        return mem;
    }

    public void setMem(long mem) {
        this.mem = mem;
    }

    public long getCurrentMem() {
        return currentMem;
    }

    public void setCurrentMem(long currentMem) {
        this.currentMem = currentMem;
    }

    public int getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(int machineNum) {
        this.machineNum = machineNum;
    }

    public int getMasterNum() {
        return masterNum;
    }

    public void setMasterNum(int masterNum) {
        this.masterNum = masterNum;
    }

    public int getSlaveNum() {
        return slaveNum;
    }

    public void setSlaveNum(int slaveNum) {
        this.slaveNum = slaveNum;
    }

    public long getCurrentObjNum() {
        return currentObjNum;
    }

    public void setCurrentObjNum(long currentObjNum) {
        this.currentObjNum = currentObjNum;
    }

    public int getConn() {
        return conn;
    }

    public void setConn(int conn) {
        this.conn = conn;
    }

    public double getMemUseThreshold() {
        return memUseThreshold;
    }

    public void setMemUseThreshold(double memUseThreshold) {
        this.memUseThreshold = memUseThreshold;
    }

    public double getHitPercentThreshold() {
        return hitPercentThreshold;
    }

    public void setHitPercentThreshold(double hitPercentThreshold) {
        this.hitPercentThreshold = hitPercentThreshold;
    }

    public double getMemUsePercent() {
        return memUsePercent;
    }

    public void setMemUsePercent(double memUsePercent) {
        this.memUsePercent = memUsePercent;
    }

    public double getHitPercent() {
        return hitPercent;
    }

    public void setHitPercent(double hitPercent) {
        this.hitPercent = hitPercent;
    }

    public List<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}
	
	public List<String> getPhoneList(){
	    List<String> phoneList = new ArrayList<String>();
	    if(CollectionUtils.isNotEmpty(appUsers)){
	        for(AppUser appUser : appUsers){
	            String mobile = appUser.getMobile();
	            if(StringUtils.isNotBlank(mobile)){
	                phoneList.add(appUser.getMobile());
	            }
	        }
	    }
	    return phoneList;
	}
	
	public List<String> getEmailList(){
        List<String> emailList = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(appUsers)){
            for(AppUser appUser : appUsers){
                String email = appUser.getEmail();
                if(StringUtils.isNotBlank(email)){
                    emailList.add(appUser.getEmail());
                }
            }
        }
        return emailList;
    }

	@Override
    public String toString() {
        return "AppDetailVO{" +
                "appDesc=" + appDesc +
                ", mem=" + mem +
                ", currentMem=" + currentMem +
                ", machineNum=" + machineNum +
                ", masterNum=" + masterNum +
                ", slaveNum=" + slaveNum +
                ", currentObjNum=" + currentObjNum +
                ", conn=" + conn +
                ", memUsePercent=" + memUsePercent +
                ", hitPercent=" + hitPercent +
                ", appUsers=" + appUsers + 
                '}';
    }
}
