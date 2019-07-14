private void setSerializer(StringRedisTemplate template){
  @SuppressWarnings({"rawtypes","unchecked"}) Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
  ObjectMapper om=new ObjectMapper();
  om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
  om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
  jackson2JsonRedisSerializer.setObjectMapper(om);
  template.setValueSerializer(jackson2JsonRedisSerializer);
}
