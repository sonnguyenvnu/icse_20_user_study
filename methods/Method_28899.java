@Override public Long hincrBy(String key,String field,long value){
  Jedis j=getShard(key);
  return j.hincrBy(key,field,value);
}
