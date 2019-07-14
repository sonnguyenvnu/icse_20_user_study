package com.sohu.cache.stats.app.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.sohu.cache.constant.AppDataMigrateEnum;
import com.sohu.cache.constant.AppDataMigrateResult;
import com.sohu.cache.constant.AppDataMigrateStatusEnum;
import com.sohu.cache.constant.CommandResult;
import com.sohu.cache.constant.ErrorMessageEnum;
import com.sohu.cache.constant.RedisMigrateToolConstant;
import com.sohu.cache.dao.AppDataMigrateStatusDao;
import com.sohu.cache.entity.AppDataMigrateSearch;
import com.sohu.cache.entity.AppDataMigrateStatus;
import com.sohu.cache.entity.MachineInfo;
import com.sohu.cache.exception.SSHException;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.protocol.MachineProtocol;
import com.sohu.cache.redis.RedisCenter;
import com.sohu.cache.ssh.SSHUtil;
import com.sohu.cache.stats.app.AppDataMigrateCenter;
import com.sohu.cache.util.ConstUtils;
import com.sohu.cache.web.service.AppService;

/**
 * 数�?��?移(使用唯�?会的开�?工具redis-migrate-tool进行�?移)
 * 
 * @author leifu
 * @Date 2016-6-8
 * @Time 下�?�2:54:33
 */
public class AppDataMigrateCenterImpl implements AppDataMigrateCenter {

    private Logger logger = LoggerFactory.getLogger(AppDataMigrateCenterImpl.class);

    private AppService appService;

    private RedisCenter redisCenter;

    private MachineCenter machineCenter;
    
    private AppDataMigrateStatusDao appDataMigrateStatusDao;

    @Override
    public AppDataMigrateResult check(String migrateMachineIp, AppDataMigrateEnum sourceRedisMigrateEnum,
            String sourceServers,
            AppDataMigrateEnum targetRedisMigrateEnum, String targetServers, String redisSourcePass, String redisTargetPass) {

        // 1. 检查migrateMachineIp是�?�安装
        AppDataMigrateResult migrateMachineResult = checkMigrateMachine(migrateMachineIp);
        if (!migrateMachineResult.isSuccess()) {
            return migrateMachineResult;
        }

        // 2. 检查�?�?置
        AppDataMigrateResult sourceResult = checkMigrateConfig(migrateMachineIp, sourceRedisMigrateEnum, sourceServers, redisSourcePass, true);
        if (!sourceResult.isSuccess()) {
            return sourceResult;
        }

        // 3. 检查目标
        AppDataMigrateResult targetResult = checkMigrateConfig(migrateMachineIp, targetRedisMigrateEnum, targetServers, redisTargetPass, false);
        if (!targetResult.isSuccess()) {
            return targetResult;
        }

        return AppDataMigrateResult.success();
    }

    /**
     * 检查�?移的机器是�?�正常
     * 
     * @param migrateMachineIp
     * @return
     */
    private AppDataMigrateResult checkMigrateMachine(String migrateMachineIp) {
        if (StringUtils.isBlank(migrateMachineIp)) {
            return AppDataMigrateResult.fail("redis-migrate-tool所在机器的IP�?能为空");
        }
        // 1. 检查机器是�?�存在在机器列表中
        try {
            MachineInfo machineInfo = machineCenter.getMachineInfoByIp(migrateMachineIp);
            if (machineInfo == null) {
                return AppDataMigrateResult.fail(migrateMachineIp + "没有在机器管�?�列表中");
            } else if (machineInfo.isOffline()) {
                return AppDataMigrateResult.fail(migrateMachineIp + ",该机器已�?被删除");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AppDataMigrateResult.fail("检测�?�生异常，请观察日志");
        }
        // 2. 检查是�?�安装redis-migrate-tool
        try {
            String cmd = ConstUtils.getRedisMigrateToolCmd();
            String response = SSHUtil.execute(migrateMachineIp, cmd);
            if (StringUtils.isBlank(response) || !response.contains("source") || !response.contains("target")) {
                return AppDataMigrateResult.fail(migrateMachineIp + "下，" + cmd + "执行失败，请确�?redis-migrate-tool安装正确!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AppDataMigrateResult.fail("检测�?�生异常，请观察日志");
        }

        // 3. 检查是�?�有�?行的redis-migrate-tool
        // 3.1 从数�?�库里检测，�?次�?移记录�?移的详情,状�?�?太好控制，暂时去掉
//        try {
//            int count = appDataMigrateStatusDao.getMigrateMachineStatCount(migrateMachineIp, AppDataMigrateStatusEnum.START.getStatus());
//            if (count > 0) {
//                return AppDataMigrateResult.fail(migrateMachineIp + "下有redis-migrate-tool进程，请确�?�?�有一�?�机器�?�有一个�?移任务进行");
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }

        // 3.2 查看进程是�?�存在
        try {
            String cmd = "/bin/ps -ef | grep redis-migrate-tool | grep -v grep | grep -v tail";
            String response = SSHUtil.execute(migrateMachineIp, cmd);
            if (StringUtils.isNotBlank(response)) {
                return AppDataMigrateResult.fail(migrateMachineIp + "下有redis-migrate-tool进程，请确�?�?�有一�?�机器�?�有一个�?移任务进行");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AppDataMigrateResult.fail("检测�?�生异常，请观察日志");
        }

        return AppDataMigrateResult.success();
    }

    /**
     * 检测�?置
     * 
     * @param migrateMachineIp
     * @param redisMigrateEnum
     * @param servers
     * @param redisSourcePass �?密�?
     * @return
     */
    private AppDataMigrateResult checkMigrateConfig(String migrateMachineIp, AppDataMigrateEnum redisMigrateEnum,
            String servers, String redisPassword, boolean isSource) {
        //target如果是rdb是没有路径的，�?需�?检测
        if (isSource || !AppDataMigrateEnum.isFileType(redisMigrateEnum)) {
            if (StringUtils.isBlank(servers)) {
                return AppDataMigrateResult.fail("�?务器信�?��?能为空!");
            }
        }
        List<String> serverList = Arrays.asList(servers.split(ConstUtils.NEXT_LINE));
        if (CollectionUtils.isEmpty(serverList)) {
            return AppDataMigrateResult.fail("�?务器信�?�格�?有问题!");
        }
        for (String server : serverList) {
            if (AppDataMigrateEnum.isFileType(redisMigrateEnum)) {
                if (!isSource) {
                    continue;
                }
                // 检查文件是�?�存在 
                String filePath = server;
                String cmd = "head " + filePath;
                try {
                    String headResult = SSHUtil.execute(migrateMachineIp, cmd);
                    if (StringUtils.isBlank(headResult)) {
                        return AppDataMigrateResult.fail(migrateMachineIp + "上的rdb:" + filePath + "�?存在或者为空!");
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    return AppDataMigrateResult.fail(migrateMachineIp + "上的rdb:" + filePath + "读�?�异常!");
                }
            } else {
                // 1. 检查是�?�为ip:port格�?(简�?�检查一下，无需正则表达�?)
                // 2. 检查Redis节点是�?�存在
                String[] instanceItems = server.split(":");
                if (instanceItems.length != 2) {
                    return AppDataMigrateResult.fail("实例信�?�" + server + "格�?错误，必须为ip:port格�?");
                }
                String ip = instanceItems[0];
                String portStr = instanceItems[1];
                boolean portIsDigit = NumberUtils.isDigits(portStr);
                if (!portIsDigit) {
                    return AppDataMigrateResult.fail(server + "中的port�?是整数");
                }
                int port = NumberUtils.toInt(portStr);
                boolean isRun = redisCenter.isRun(ip, port, redisPassword);
                if (!isRun) {
                    return AppDataMigrateResult.fail(server + "�?是存活的或者密�?错误!");
                }
            }
        }

        return AppDataMigrateResult.success();
    }

    @Override
	public boolean migrate(String migrateMachineIp, AppDataMigrateEnum sourceRedisMigrateEnum, String sourceServers,
			AppDataMigrateEnum targetRedisMigrateEnum, String targetServers, long sourceAppId, long targetAppId,
			String redisSourcePass, String redisTargetPass, long userId) {
        // 1. 生�?�?置
        int migrateMachinePort = ConstUtils.REDIS_MIGRATE_TOOL_PORT;
        String configContent = generateConfig(migrateMachinePort, sourceRedisMigrateEnum, sourceServers, targetRedisMigrateEnum,
                targetServers, redisSourcePass, redisTargetPass);
        // 2. 上传�?置
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String confileFileName = "rmt-" + timestamp + ".conf";
        String logFileName = "rmt-" + timestamp + ".log";
        boolean uploadConfig = createRemoteFile(migrateMachineIp, confileFileName, configContent);
        if (!uploadConfig) {
            return false;
        }
        // 3. 开始执行: 指定的�?置�??�?目录�?日志�??
        String cmd = ConstUtils.getRedisMigrateToolCmd() + " -c " + ConstUtils.getRedisMigrateToolDir() + confileFileName
                + " -o " + ConstUtils.getRedisMigrateToolDir() + logFileName + " -d";
        logger.warn(cmd);
        try {
            SSHUtil.execute(migrateMachineIp, cmd);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }

        // 4. 记录执行记录
        AppDataMigrateStatus appDataMigrateStatus = new AppDataMigrateStatus();
        appDataMigrateStatus.setMigrateMachineIp(migrateMachineIp);
        appDataMigrateStatus.setMigrateMachinePort(migrateMachinePort);
        appDataMigrateStatus.setStartTime(new Date());
        appDataMigrateStatus.setSourceMigrateType(sourceRedisMigrateEnum.getIndex());
        appDataMigrateStatus.setSourceServers(sourceServers);
        appDataMigrateStatus.setTargetMigrateType(targetRedisMigrateEnum.getIndex());
        appDataMigrateStatus.setTargetServers(targetServers);
        appDataMigrateStatus.setLogPath(ConstUtils.getRedisMigrateToolDir() + logFileName);
        appDataMigrateStatus.setConfigPath(ConstUtils.getRedisMigrateToolDir() + confileFileName);
        appDataMigrateStatus.setUserId(userId);
        appDataMigrateStatus.setSourceAppId(sourceAppId);
        appDataMigrateStatus.setTargetAppId(targetAppId);
        appDataMigrateStatus.setStatus(AppDataMigrateStatusEnum.START.getStatus());
        appDataMigrateStatusDao.save(appDataMigrateStatus);

        return true;
    }

    /**
     * 生�?�?置
     * 
     * @param sourceRedisMigrateEnum
     * @param sourceServers
     * @param targetRedisMigrateEnum
     * @param targetServers
     * @return
     */
    public String generateConfig(int listenPort, AppDataMigrateEnum sourceRedisMigrateEnum, String sourceServers,
            AppDataMigrateEnum targetRedisMigrateEnum, String targetServers, String redisSourcePass, String redisTargetPass) {
        // source
        StringBuffer config = new StringBuffer();
        config.append("[source]" + ConstUtils.NEXT_LINE);
        config.append("type: " + sourceRedisMigrateEnum.getType() + ConstUtils.NEXT_LINE);
        config.append("servers:" + ConstUtils.NEXT_LINE);
        List<String> sourceServerList = Arrays.asList(sourceServers.split(ConstUtils.NEXT_LINE));
        for (String server : sourceServerList) {
            config.append(" - " + server + ConstUtils.NEXT_LINE);
        }
        if (StringUtils.isNotBlank(redisSourcePass)) {
            config.append("redis_auth: " + redisSourcePass + ConstUtils.NEXT_LINE);
        }
        config.append(ConstUtils.NEXT_LINE);

        // target
        config.append("[target]" + ConstUtils.NEXT_LINE);
        config.append("type: " + targetRedisMigrateEnum.getType() + ConstUtils.NEXT_LINE);
        if (!AppDataMigrateEnum.isFileType(targetRedisMigrateEnum)) {
            config.append("servers:" + ConstUtils.NEXT_LINE);
            List<String> targetServerList = Arrays.asList(targetServers.split(ConstUtils.NEXT_LINE));
            for (String server : targetServerList) {
                config.append(" - " + server + ConstUtils.NEXT_LINE);
            }
            if (StringUtils.isNotBlank(redisTargetPass)) {
                config.append("redis_auth: " + redisTargetPass + ConstUtils.NEXT_LINE);
            }
            config.append(ConstUtils.NEXT_LINE);
        }

        // common:使用最简�?置
        config.append("[common]" + ConstUtils.NEXT_LINE);
        config.append("listen: 0.0.0.0:" + listenPort + ConstUtils.NEXT_LINE);
        config.append("dir: " + ConstUtils.getRedisMigrateToolDir());

        return config.toString();
    }

    /**
     * 创建远程文件
     * 
     * @param host
     * @param fileName
     * @param content
     */
    public boolean createRemoteFile(String host, String fileName, String content) {
        /**
         * 1. 创建本地文件
         */
        // 确认目录
        String localAbsolutePath = MachineProtocol.TMP_DIR + fileName;
        File tmpDir = new File(MachineProtocol.TMP_DIR);
        if (!tmpDir.exists()) {
            if (!tmpDir.mkdirs()) {
                logger.error("cannot create /tmp/cachecloud directory.");
            }
        }
        Path path = Paths.get(MachineProtocol.TMP_DIR + fileName);
        // 将�?置文件的内容写到本地
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = Files.newBufferedWriter(path, Charset.forName(MachineProtocol.ENCODING_UTF8));
            bufferedWriter.write(content);
        } catch (IOException e) {
            logger.error("write rmt file error, ip: {}, filename: {}, content: {}", host, fileName, content, e);
            return false;
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                	logger.error(e.getMessage(), e);
                }
            }
        }

        /**
         * 2. 将�?置文件推�?到目标机器上
         */
        try {
            SSHUtil.scpFileToRemote(host, localAbsolutePath, ConstUtils.getRedisMigrateToolDir());
        } catch (SSHException e) {
            logger.error("scp rmt file to remote server error: ip: {}, fileName: {}", host, fileName, e);
            return false;
        }

        /**
         * 3. 删除临时文件
         */
        File file = new File(localAbsolutePath);
        if (file.exists()) {
            file.delete();
        }

        return true;
    }
    
    @Override
	public List<AppDataMigrateStatus> search(AppDataMigrateSearch appDataMigrateSearch) {
    	try {
            return appDataMigrateStatusDao.search(appDataMigrateSearch);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
	}
    
    
    @Override
    public String showDataMigrateLog(long id, int pageSize) {
        AppDataMigrateStatus appDataMigrateStatus = appDataMigrateStatusDao.get(id);
        if (appDataMigrateStatus == null) {
            return "";
        }
        String logPath = appDataMigrateStatus.getLogPath();
        String host = appDataMigrateStatus.getMigrateMachineIp();
        StringBuilder command = new StringBuilder();
        command.append("/usr/bin/tail -n").append(pageSize).append(" ").append(logPath);
        try {
            return SSHUtil.execute(host, command.toString());
        } catch (SSHException e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }
    
    @Override
    public String showDataMigrateConf(long id) {
        AppDataMigrateStatus appDataMigrateStatus = appDataMigrateStatusDao.get(id);
        if (appDataMigrateStatus == null) {
            return "";
        }
        String configPath = appDataMigrateStatus.getConfigPath();
        String host = appDataMigrateStatus.getMigrateMachineIp();
        String command = "cat " + configPath;
        try {
            return SSHUtil.execute(host, command);
        } catch (SSHException e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }
    
    @Override
    public Map<RedisMigrateToolConstant, Map<String, Object>> showMiragteToolProcess(long id) {
        AppDataMigrateStatus appDataMigrateStatus = appDataMigrateStatusDao.get(id);
        if (appDataMigrateStatus == null) {
            return Collections.emptyMap();
        }
        String info = "";
        String host = appDataMigrateStatus.getMigrateMachineIp();
        int port = appDataMigrateStatus.getMigrateMachinePort();
        Jedis jedis = null;
        try {
            jedis = new Jedis(host, port, 5000);
            info = jedis.info();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        if (StringUtils.isBlank(info)) {
            return Collections.emptyMap();
        }

        return processRedisMigrateToolStats(info);
        
    }
    
    /**
     * 处�?��?移工具状�?
     * @param statResult
     * @return
     */
    private Map<RedisMigrateToolConstant, Map<String, Object>> processRedisMigrateToolStats(String statResult) {
        Map<RedisMigrateToolConstant, Map<String, Object>> redisStatMap = new HashMap<RedisMigrateToolConstant, Map<String, Object>>();
        String[] data = statResult.split("\r\n");
        String key;
        int i = 0;
        int length = data.length;
        while (i < length) {
            if (data[i].contains("#")) {
                int index = data[i].indexOf('#');
                key = data[i].substring(index + 1);
                ++i;
                RedisMigrateToolConstant redisMigrateToolConstant = RedisMigrateToolConstant.value(key.trim());
                if (redisMigrateToolConstant == null) {
                    continue;
                }
                Map<String, Object> sectionMap = new LinkedHashMap<String, Object>();
                while (i < length && data[i].contains(":")) {
                    String[] pair = data[i].split(":");
                    sectionMap.put(pair[0], pair[1]);
                    i++;
                }
                redisStatMap.put(redisMigrateToolConstant, sectionMap);
            } else {
                i++;
            }
        }
        return redisStatMap;
    }
    
    
    @Override
    public CommandResult sampleCheckData(long id, int nums) {
        AppDataMigrateStatus appDataMigrateStatus = appDataMigrateStatusDao.get(id);
        if (appDataMigrateStatus == null) {
            return null;
        }
        String ip = appDataMigrateStatus.getMigrateMachineIp();
        String configPath = appDataMigrateStatus.getConfigPath();
        String sampleCheckDataCmd = ConstUtils.getRedisMigrateToolCmd() + " -c " + configPath + " -C" + " 'redis_check " + nums + "'";
        logger.warn("sampleCheckDataCmd: {}", sampleCheckDataCmd);
        try {
            return new CommandResult(sampleCheckDataCmd, SSHUtil.execute(ip, sampleCheckDataCmd));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new CommandResult(sampleCheckDataCmd, ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
        }
    }
    
    @Override
    public AppDataMigrateResult stopMigrate(long id) {
        // 获�?�基本信�?�
        AppDataMigrateStatus appDataMigrateStatus = appDataMigrateStatusDao.get(id);
        if (appDataMigrateStatus == null) {
            return AppDataMigrateResult.fail("id=" + id + "�?移记录�?存在!");
        }
        // 获�?�进程�?�
        String migrateMachineIp = appDataMigrateStatus.getMigrateMachineIp();
        String migrateMachineHostPort = migrateMachineIp + ":" + appDataMigrateStatus.getMigrateMachinePort();
        Map<RedisMigrateToolConstant, Map<String, Object>> redisMigrateToolStatMap = showMiragteToolProcess(id);
        if (MapUtils.isEmpty(redisMigrateToolStatMap)) {
            return AppDataMigrateResult.fail("获�?�" + migrateMachineHostPort + "相关信�?�失败，�?�能是进程�?存在或者客户端超时，请查找原因或�?试!");
        }
        Map<String, Object> serverMap = redisMigrateToolStatMap.get(RedisMigrateToolConstant.Server);
        int pid = MapUtils.getInteger(serverMap, "process_id", -1);
        if (pid <= 0) {
            return AppDataMigrateResult.fail("获�?�" + migrateMachineHostPort + "的进程�?�" + pid + "异常");
        }

        // 确认进程�?�是redis-migrate-tool进程
        Boolean exist = checkPidWhetherIsRmt(migrateMachineIp, pid);
        if (exist == null) {
            return AppDataMigrateResult.fail("执行过程中�?�生异常,请查看系统日志!");
        } else if (exist.equals(false)) {
            return AppDataMigrateResult.fail(migrateMachineIp + "进程�?�" + pid + "�?存在,请确认!");
        }

        // kill掉进程
        try {
            String cmd = "kill " + pid;
            SSHUtil.execute(migrateMachineIp, cmd);
            exist = checkPidWhetherIsRmt(migrateMachineIp, pid);
            if (exist == null) {
                return AppDataMigrateResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
            } else if (exist.equals(false)) {
                // 更新记录完�?更新
                appDataMigrateStatusDao.updateStatus(id, AppDataMigrateStatusEnum.END.getStatus());
                return AppDataMigrateResult.success("已�?�?功�?�止了id=" + id + "的�?移任务");
            } else {
                return AppDataMigrateResult.fail(migrateMachineIp + "进程�?�" + pid + "�?然存在,没有kill掉,请确认!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AppDataMigrateResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
        }
    }

    /**
     * 检查pid是�?�是redis-migrate-tool进程
     * @param migrateMachineIp
     * @param pid
     * @return
     * @throws SSHException
     */
    private Boolean checkPidWhetherIsRmt(String migrateMachineIp, int pid){
        try {
            String cmd = "/bin/ps -ef | grep redis-migrate-tool | grep -v grep | grep " + pid;
            String response = SSHUtil.execute(migrateMachineIp, cmd);
            if (StringUtils.isNotBlank(response)) {
                return true;
            } else {
                return false;
            }
        } catch (SSHException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public void setRedisCenter(RedisCenter redisCenter) {
        this.redisCenter = redisCenter;
    }

    public void setMachineCenter(MachineCenter machineCenter) {
        this.machineCenter = machineCenter;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public void setAppDataMigrateStatusDao(AppDataMigrateStatusDao appDataMigrateStatusDao) {
        this.appDataMigrateStatusDao = appDataMigrateStatusDao;
    }

	

    

}
