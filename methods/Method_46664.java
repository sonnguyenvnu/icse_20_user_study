@Override public List<String> findTokens(){
  Long size=redisTemplate.opsForList().size(REDIS_TOKEN_PREFIX);
  if (Objects.isNull(size)) {
    return Collections.emptyList();
  }
  return Objects.requireNonNull(redisTemplate.opsForList().range(REDIS_TOKEN_PREFIX,0,size)).stream().map(Object::toString).collect(Collectors.toList());
}
