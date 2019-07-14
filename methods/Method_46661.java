@Override public int getTransactionState(String groupId) throws FastStorageException {
  Object state=redisTemplate.opsForValue().get(REDIS_GROUP_STATE + groupId);
  if (Objects.isNull(state)) {
    return -1;
  }
  try {
    return Integer.valueOf(state.toString());
  }
 catch (  Exception e) {
    return -1;
  }
}
