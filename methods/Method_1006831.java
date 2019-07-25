public String hget(String mapName,String key){
  Object obj=this.stringRedisTemplate.opsForHash().get(mapName,key);
  if (obj == null)   return null;
  return obj.toString().trim();
}
