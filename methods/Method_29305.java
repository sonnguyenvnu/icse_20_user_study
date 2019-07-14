private String failedInfo(Jedis jedis,int slot){
  return String.format(" failed %s:%d slot=%d",jedis.getClient().getHost(),jedis.getClient().getPort(),slot);
}
