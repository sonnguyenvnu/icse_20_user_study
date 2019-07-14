private Map<JedisPool,List<String>> getPoolKeyMap(List<String> keys){
  Map<JedisPool,List<String>> poolKeysMap=new LinkedHashMap<JedisPool,List<String>>();
  try {
    for (    String key : keys) {
      JedisPool jedisPool;
      int slot=JedisClusterCRC16.getSlot(key);
      jedisPool=connectionHandler.getJedisPoolFromSlot(slot);
      if (poolKeysMap.containsKey(jedisPool)) {
        poolKeysMap.get(jedisPool).add(key);
      }
 else {
        List<String> subKeyList=new ArrayList<String>();
        subKeyList.add(key);
        poolKeysMap.put(jedisPool,subKeyList);
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return poolKeysMap;
}
