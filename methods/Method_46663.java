@Override public void saveToken(String token){
  Objects.requireNonNull(token);
  redisTemplate.opsForList().leftPush(REDIS_TOKEN_PREFIX,token);
  redisTemplate.expire(REDIS_TOKEN_PREFIX,20,TimeUnit.MINUTES);
  Long size=redisTemplate.opsForList().size(REDIS_TOKEN_PREFIX);
  if (Objects.nonNull(size) && size > 3) {
    redisTemplate.opsForList().rightPop(REDIS_TOKEN_PREFIX);
  }
}
