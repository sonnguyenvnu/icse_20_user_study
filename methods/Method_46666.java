@Override public List<TMProperties> findTMProperties(){
  return stringRedisTemplate.opsForHash().entries(REDIS_TM_LIST).entrySet().stream().map(entry -> {
    String[] args=ApplicationInformation.splitAddress(entry.getKey().toString());
    TMProperties tmProperties=new TMProperties();
    tmProperties.setHost(args[0]);
    tmProperties.setTransactionPort(Integer.valueOf(args[1]));
    tmProperties.setHttpPort(Integer.parseInt(entry.getValue().toString()));
    return tmProperties;
  }
).collect(Collectors.toList());
}
