@Override public void initGroup(String groupId){
  redisTemplate.opsForHash().put(REDIS_GROUP_PREFIX + groupId,"root","");
  redisTemplate.expire(REDIS_GROUP_PREFIX + groupId,managerConfig.getDtxTime() + 10000,TimeUnit.MILLISECONDS);
}
