@Override public List<String> getClientList(int instanceId){
  if (instanceId <= 0) {
    return Collections.emptyList();
  }
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  if (instanceInfo == null) {
    return Collections.emptyList();
  }
  if (TypeUtil.isRedisType(instanceInfo.getType())) {
    Jedis jedis=null;
    try {
      jedis=getJedis(instanceInfo.getAppId(),instanceInfo.getIp(),instanceInfo.getPort(),REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
      jedis.clientList();
      List<String> resultList=new ArrayList<String>();
      String clientList=jedis.clientList();
      if (StringUtils.isNotBlank(clientList)) {
        String[] array=clientList.split("\n");
        resultList.addAll(Arrays.asList(array));
      }
      return resultList;
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
 finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }
  return Collections.emptyList();
}
