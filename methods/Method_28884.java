/** 
 * ???script????????????
 * @param script
 * @return
 */
public String loadScruptAllNodes(String script){
  Map<String,JedisPool> nodeMap=getClusterNodes();
  String md5=null;
  for (  JedisPool jedisPool : nodeMap.values()) {
    Jedis jedis=null;
    try {
      jedis=jedisPool.getResource();
      String returnMd5=jedis.scriptLoad(script);
      if (returnMd5 != null) {
        md5=returnMd5;
      }
    }
  finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }
  return md5;
}
