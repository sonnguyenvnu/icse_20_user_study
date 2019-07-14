@Override public void clearGroup(String groupId){
  log.debug("remove group:{} from redis.",groupId);
  redisTemplate.delete(REDIS_GROUP_PREFIX + groupId);
}
