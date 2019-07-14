public Jedis getJedis(String channel){
  return returnRetriesJedis(channel,this.redirections,false,false);
}
