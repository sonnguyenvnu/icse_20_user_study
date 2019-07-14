/** 
 * ??sentinel?masterName
 * @param ip
 * @param port
 * @return
 */
private String getSentinelMasterName(final String ip,final int port){
  final StringBuilder masterName=new StringBuilder();
  new IdempotentConfirmer(){
    @Override public boolean execute(){
      Jedis jedis=null;
      try {
        String password=null;
        jedis=redisCenter.getJedis(ip,port,password);
        jedis.getClient().setConnectionTimeout(Protocol.DEFAULT_TIMEOUT * (timeOutFactor++));
        jedis.getClient().setSoTimeout(Protocol.DEFAULT_TIMEOUT * (timeOutFactor++));
        List<Map<String,String>> mapList=jedis.sentinelMasters();
        String targetKey="name";
        for (        Map<String,String> map : mapList) {
          if (map.containsKey(targetKey)) {
            masterName.append(MapUtils.getString(map,targetKey,""));
          }
        }
        return true;
      }
 catch (      Exception e) {
        logger.warn("{}:{} error message is {} ",ip,port,e.getMessage());
        return false;
      }
 finally {
        if (jedis != null) {
          jedis.close();
        }
      }
    }
  }
.run();
  return masterName.toString();
}
