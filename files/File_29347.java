package com.sohu.cache.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.cache.async.AsyncService;
import com.sohu.cache.async.AsyncThreadPoolFactory;
import com.sohu.cache.async.KeyCallable;
import com.sohu.cache.server.data.OSInfo;
import com.sohu.cache.server.data.Server;
import com.sohu.cache.server.nmon.NMONService;
import com.sohu.cache.ssh.SSHTemplate;
import com.sohu.cache.ssh.SSHTemplate.DefaultLineProcessor;
import com.sohu.cache.ssh.SSHTemplate.Result;
import com.sohu.cache.ssh.SSHTemplate.SSHCallback;
import com.sohu.cache.ssh.SSHTemplate.SSHSession;
import com.sohu.cache.web.service.ServerDataService;

/**
 * æœ?åŠ¡å™¨çŠ¶æ€?ç›‘æŽ§æœ?åŠ¡
 */
public class ServerStatusCollector {
	private static final Logger logger = LoggerFactory.getLogger(ServerStatusCollector.class);

	//èŽ·å?–ç›‘æŽ§ç»“æžœ
	public static final String COLLECT_SERVER_STATUS = 
			  "[ -e \""+NMONService.SOCK_LOG+"\" ] && /bin/cat " + NMONService.SOCK_LOG + " >> " + NMONService.NMON_LOG
			+ ";[ -e \""+NMONService.ULIMIT_LOG+"\" ] && /bin/cat " + NMONService.ULIMIT_LOG + " >> " + NMONService.NMON_LOG
			+ ";/bin/mv " + NMONService.NMON_LOG + " " + NMONService.NMON_OLD_LOG
			+ ";[ $? -eq 0 ] && /bin/cat " + NMONService.NMON_OLD_LOG;
	
	//nmonæœ?åŠ¡
	private NMONService nmonService;
	//ssh æ¨¡æ?¿ç±»
	private SSHTemplate sshTemplate;
	//æŒ?ä¹…åŒ–
	private ServerDataService serverDataService;
	
	private AsyncService asyncService;
	
	public void init() {
		asyncService.assemblePool(AsyncThreadPoolFactory.MACHINE_POOL, 
				AsyncThreadPoolFactory.MACHINE_THREAD_POOL);
	}
	
	//å¼‚æ­¥æ‰§è¡Œä»»åŠ¡
	public void asyncFetchServerStatus(final String ip) {
		String key = "collect-server-"+ip;
		asyncService.submitFuture(AsyncThreadPoolFactory.MACHINE_POOL, new KeyCallable<Boolean>(key) {
            public Boolean execute() {
                try {
                	fetchServerStatus(ip);
                    return true;
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    return false;
                }
            }
        });
	}
	
	/**
	 * æŠ“å?–æœ?åŠ¡å™¨çŠ¶æ€?
	 * @param ip
	 */
	public void fetchServerStatus(final String ip) {
		try {
			sshTemplate.execute(ip, new SSHCallback() {
				public Result call(SSHSession session) {
					//å°?è¯•æ”¶é›†æœ?åŠ¡å™¨è¿?è¡ŒçŠ¶å†µ
					collectServerStatus(ip, session);
					//å?¯åŠ¨nmonæ”¶é›†æœ?åŠ¡å™¨è¿?è¡ŒçŠ¶å†µ
					OSInfo info = nmonService.start(ip, session);
					saveServerStatus(ip, info);
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("fetchServerStatus "+ip+" err", e);
		}
	}
	
	/**
	 * æ”¶é›†ç³»ç»ŸçŠ¶å†µ
	 * @param ip
	 * @param session
	 */
	private void collectServerStatus(String ip, SSHSession session) {
		final Server server = new Server();
		server.setIp(ip);
		Result result = session.executeCommand(COLLECT_SERVER_STATUS, new DefaultLineProcessor() {
			public void process(String line, int lineNum) throws Exception {
				server.parse(line, null);
			}
		});
		if(!result.isSuccess()) {
			logger.error("collect " + ip + " err:" + result.getResult(), result.getExcetion());
		}
		//ä¿?å­˜æœ?åŠ¡å™¨é?™æ€?ä¿¡æ?¯
		serverDataService.saveAndUpdateServerInfo(server);
		//ä¿?å­˜æœ?åŠ¡å™¨çŠ¶å†µä¿¡æ?¯
		serverDataService.saveServerStat(server);
	}
	
	/**
	 * ä¿?å­˜æœ?åŠ¡å™¨distä¿¡æ?¯
	 * @param ip
	 * @param OSInfo
	 */
	private void saveServerStatus(String ip, OSInfo osInfo) {
		if(osInfo == null) {
			return;
		}
		serverDataService.saveServerInfo(ip, osInfo.getIssue());
	}
	
	public void setNmonService(NMONService nmonService) {
		this.nmonService = nmonService;
	}
	public void setSshTemplate(SSHTemplate sshTemplate) {
		this.sshTemplate = sshTemplate;
	}
	public void setServerDataService(ServerDataService serverDataService) {
		this.serverDataService = serverDataService;
	}
	public void setAsyncService(AsyncService asyncService) {
		this.asyncService = asyncService;
	}
}
