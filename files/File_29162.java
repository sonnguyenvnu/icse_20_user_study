package com.sohu.cache.entity;
/**
 * �?务器信�?�
 */
public class ServerInfo {
	private String ip;
	private String host;
	//逻辑cpu个数
	private int cpus;
	//nmon版本
	private String nmon;
	//cpu型�?�
	private String cpuModel;
	//内核版本
	private String kernel;
	//�?�行版本
	private String dist;
	//ulimit
	private String ulimit;
	
	public String getUlimit() {
		return ulimit;
	}
	public void setUlimit(String ulimit) {
		this.ulimit = ulimit;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getCpus() {
		return cpus;
	}
	public void setCpus(int cpus) {
		this.cpus = cpus;
	}
	public String getNmon() {
		return nmon;
	}
	public void setNmon(String nmon) {
		this.nmon = nmon;
	}
	public String getCpuModel() {
		return cpuModel;
	}
	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}
	public String getKernel() {
		return kernel;
	}
	public void setKernel(String kernel) {
		this.kernel = kernel;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
}
