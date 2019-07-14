public T run(List<String> keys){
  if (keys == null || keys.isEmpty()) {
    return null;
  }
  Map<JedisPool,List<String>> poolKeysMap=getPoolKeyMap(keys);
  Map<String,Object> resultMap=new HashMap<String,Object>();
  for (  Map.Entry<JedisPool,List<String>> entry : poolKeysMap.entrySet()) {
    JedisPool jedisPool=entry.getKey();
    List<String> subkeys=entry.getValue();
    if (subkeys == null || subkeys.isEmpty()) {
      continue;
    }
    Jedis jedis=null;
    Pipeline pipeline=null;
    List<Object> subResultList=null;
    try {
      jedis=jedisPool.getResource();
      pipeline=jedis.pipelined();
      pipelineCommand(pipeline,subkeys);
      subResultList=pipeline.syncAndReturnAll();
    }
 catch (    JedisConnectionException e) {
      logger.error("RedisConnectionError-{}:{} keys={}",jedisPool.getHost(),jedisPool.getPort(),subkeys);
      logger.error(e.getMessage(),e);
    }
catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
 finally {
      if (pipeline != null)       pipeline.clean();
      if (jedis != null) {
        jedis.close();
      }
    }
    if (subResultList == null || subResultList.isEmpty()) {
      continue;
    }
    if (subResultList.size() == subkeys.size()) {
      for (int i=0; i < subkeys.size(); i++) {
        String key=subkeys.get(i);
        Object result=subResultList.get(i);
        resultMap.put(key,result);
      }
    }
 else {
      logger.error("PipelineClusterCommand:subkeys={} subResultList={}",subkeys,subResultList);
    }
  }
  return getResult(resultMap);
}
