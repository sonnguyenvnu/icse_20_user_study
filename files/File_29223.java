package com.sohu.cache.machine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.sohu.cache.dao.MachineDao;
import com.sohu.cache.dao.MachineStatsDao;
import com.sohu.cache.dao.ServerStatusDao;
import com.sohu.cache.entity.MachineInfo;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.machine.MachineDeployCenter;

/**
 * 机器部署相关
 * @author leifu
 * changed @Date 2016-4-24
 * @Time 下�?�5:07:30
 */
public class MachineDeployCenterImpl implements MachineDeployCenter {
    private Logger logger = LoggerFactory.getLogger(MachineDeployCenterImpl.class);

    private MachineDao machineDao;

    private MachineCenter machineCenter;

    private MachineStatsDao machineStatsDao;
    
    private ServerStatusDao serverStatusDao;

    /**
     * 将机器加入资�?池并统计�?监控
     *
     * @param machineInfo
     * @return
     */
    @Override
    public boolean addMachine(MachineInfo machineInfo) {
        boolean success = true;

        if (machineInfo == null || Strings.isNullOrEmpty(machineInfo.getIp())) {
            logger.error("machineInfo is null or ip is valid.");
            return false;
        }
        // 将机器信�?��?存到db中
        try {
            machineDao.saveMachineInfo(machineInfo);
        } catch (Exception e) {
            logger.error("save machineInfo: {} to db error.", machineInfo.toString(), e);
            return false;
        }

        // 为机器添加统计和监控的定时任务
        try {
            MachineInfo thisMachine = machineDao.getMachineInfoByIp(machineInfo.getIp());
            if (thisMachine != null) {
                long hostId = thisMachine.getId();
                String ip = thisMachine.getIp();
                if (!machineCenter.deployMachineCollection(hostId, ip)) {
                    logger.error("deploy machine collection error, machineInfo: {}", thisMachine.toString());
                    success = false;
                }
                if (!machineCenter.deployMachineMonitor(hostId, ip)) {
                    logger.error("deploy machine monitor error, machineInfo: {}", thisMachine.toString());
                    success = false;
                }
                if(thisMachine.getCollect() == 1) {
                	if (!machineCenter.deployServerCollection(hostId, ip)) {
                		logger.error("deploy server monitor error, machineInfo: {}", thisMachine.toString());
                		success = false;
                	}
                } else {
                	if (!machineCenter.unDeployServerCollection(hostId, ip)) {
                		logger.error("undeploy server monitor error, machineInfo: {}", thisMachine.toString());
                		success = false;
                	}
                }
            }
        } catch (Exception e) {
            logger.error("query machineInfo from db error, ip: {}", machineInfo.getIp(), e);
        }

        if (success) {
            logger.info("save and deploy machine ok, machineInfo: {}", machineInfo.toString());
        }
        return success;
    }

    /**
     * 删除机器，并删除相关的定时任务
     *
     * @param machineInfo
     * @return
     */
    @Override
    public boolean removeMachine(MachineInfo machineInfo) {
        if (machineInfo == null || Strings.isNullOrEmpty(machineInfo.getIp())) {
            logger.warn("machineInfo is null or ip is empty.");
            return false;
        }
        String machineIp = machineInfo.getIp();
        
        //从quartz中删除相关的定时任务
        try {
            MachineInfo thisMachine = machineDao.getMachineInfoByIp(machineIp);
            long hostId = thisMachine.getId();
            
            if (!machineCenter.unDeployMachineCollection(hostId, machineIp)) {
                logger.error("remove trigger for machine error: {}", thisMachine.toString());
                return false;
            }
            if (!machineCenter.unDeployMachineMonitor(hostId, machineIp)) {
                logger.error("remove trigger for machine monitor error: {}", thisMachine.toString());
                return false;
            }
            if (!machineCenter.unDeployServerCollection(hostId, machineIp)) {
                logger.error("remove trigger for server monitor error: {}", thisMachine.toString());
                return false;
            }
        } catch (Exception e) {
            logger.error("query machineInfo from db error: {}", machineInfo.toString());
        }
        
        // 从db中删除machine和相关统计信�?�
        try {
            machineDao.removeMachineInfoByIp(machineIp);
            machineStatsDao.deleteMachineStatsByIp(machineIp);
            serverStatusDao.deleteServerInfo(machineIp);
        } catch (Exception e) {
            logger.error("remove machineInfo from db error, machineInfo: {}", machineInfo.toString(), e);
            return false;
        }
        logger.info("remove and undeploy machine ok: {}", machineInfo.toString());
        return true;
    }


    public void setMachineDao(MachineDao machineDao) {
        this.machineDao = machineDao;
    }

    public void setMachineCenter(MachineCenter machineCenter) {
        this.machineCenter = machineCenter;
    }

    public void setMachineStatsDao(MachineStatsDao machineStatsDao) {
        this.machineStatsDao = machineStatsDao;
    }

	public void setServerStatusDao(ServerStatusDao serverStatusDao) {
		this.serverStatusDao = serverStatusDao;
	}
}
