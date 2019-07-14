/** 
 * ??pid???redis-migrate-tool??
 * @param migrateMachineIp
 * @param pid
 * @return
 * @throws SSHException
 */
private Boolean checkPidWhetherIsRmt(String migrateMachineIp,int pid){
  try {
    String cmd="/bin/ps -ef | grep redis-migrate-tool | grep -v grep | grep " + pid;
    String response=SSHUtil.execute(migrateMachineIp,cmd);
    if (StringUtils.isNotBlank(response)) {
      return true;
    }
 else {
      return false;
    }
  }
 catch (  SSHException e) {
    logger.error(e.getMessage(),e);
    return null;
  }
}
