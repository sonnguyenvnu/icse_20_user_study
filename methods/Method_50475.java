@Override public int updateStatus(final String id,final Integer status){
  try {
    final String redisKey=RepositoryPathUtils.buildRedisKey(keyPrefix,id);
    byte[] contents=jedisClient.get(redisKey.getBytes());
    if (contents != null) {
      CoordinatorRepositoryAdapter adapter=objectSerializer.deSerialize(contents,CoordinatorRepositoryAdapter.class);
      adapter.setStatus(status);
      jedisClient.set(redisKey,objectSerializer.serialize(adapter));
    }
  }
 catch (  HmilyException e) {
    e.printStackTrace();
    return FAIL_ROWS;
  }
  return ROWS;
}
