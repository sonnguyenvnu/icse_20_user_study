private Jedis returnRetriesJedis(String key,int redirections,boolean tryRandomNode,boolean asking){
  if (redirections <= 0) {
    throw new JedisClusterMaxRedirectionsException("Too many Cluster redirections? key=" + key);
  }
  Jedis jedis=null;
  try {
    if (asking) {
      jedis=askConnection.get();
      jedis.asking();
      asking=false;
    }
 else     if (tryRandomNode) {
      jedis=connectionHandler.getConnection();
    }
 else {
      jedis=connectionHandler.getConnectionFromSlot(JedisClusterCRC16.getSlot(key));
    }
    return jedis;
  }
 catch (  JedisConnectionException jce) {
    if (tryRandomNode) {
      throw jce;
    }
    releaseConnection(jedis);
    return returnRetriesJedis(key,redirections--,true,asking);
  }
catch (  JedisRedirectionException jre) {
    if (jre instanceof JedisMovedDataException) {
      this.connectionHandler.renewSlotCache(jedis);
    }
    releaseConnection(jedis);
    jedis=null;
    if (jre instanceof JedisAskDataException) {
      asking=true;
      askConnection.set(this.connectionHandler.getConnectionFromNode(jre.getTargetNode()));
    }
 else     if (jre instanceof JedisMovedDataException) {
    }
 else {
      throw new JedisClusterException(jre);
    }
    return returnRetriesJedis(key,redirections - 1,false,asking);
  }
}
