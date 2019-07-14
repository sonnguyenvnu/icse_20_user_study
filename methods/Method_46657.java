@Override public boolean containsGroup(String groupId){
  return Optional.ofNullable(redisTemplate.hasKey(REDIS_GROUP_PREFIX + groupId)).orElse(false);
}
