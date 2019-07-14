/** 
 * ????ps -ef | grep redis | grep -v 'grep'
 * @param ip
 * @param sshPort
 * @return
 * @throws SSHException
 */
public static String getMaxPortStr(String ip,int sshPort) throws SSHException {
  String redisPidCmd="ps -ef | grep redis | grep -v 'grep'";
  String redisProcessStr=SSHUtil.execute(ip,sshPort,ConstUtils.USERNAME,ConstUtils.PASSWORD,redisPidCmd);
  if (StringUtils.isBlank(redisProcessStr)) {
    return EmptyObjectConstant.EMPTY_STRING;
  }
  int maxPort=0;
  String[] lines=redisProcessStr.split(SymbolConstant.ENTER);
  for (  String line : lines) {
    if (StringUtils.isBlank(line)) {
      continue;
    }
    int redisServerIndex=line.indexOf("redis-server");
    int redisSentinelIndex=line.indexOf("redis-sentinel");
    if (redisServerIndex >= 0) {
      line=line.substring(redisServerIndex);
    }
    if (redisSentinelIndex >= 0) {
      line=line.substring(redisSentinelIndex);
    }
    if (redisServerIndex < 0 && redisSentinelIndex < 0) {
      continue;
    }
    String[] items=line.split(SymbolConstant.SPACE);
    if (items.length >= 2) {
      String hostPort=items[1];
      if (StringUtils.isBlank(hostPort)) {
        continue;
      }
      String[] hostPortArr=hostPort.split(SymbolConstant.COLON);
      if (hostPortArr.length != 2) {
        continue;
      }
      String portStr=hostPortArr[1];
      if (!NumberUtils.isDigits(portStr)) {
        continue;
      }
      int port=NumberUtils.toInt(portStr);
      if (port > maxPort) {
        maxPort=port;
      }
    }
  }
  return maxPort == 0 ? EmptyObjectConstant.EMPTY_STRING : String.valueOf(maxPort);
}
