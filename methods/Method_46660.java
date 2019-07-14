@Override public void saveTransactionState(String groupId,int state) throws FastStorageException {
  redisTemplate.opsForValue().set(REDIS_GROUP_STATE + groupId,String.valueOf(state));
  redisTemplate.expire(REDIS_GROUP_STATE + groupId,managerConfig.getDtxTime() + 10000,TimeUnit.MILLISECONDS);
}
