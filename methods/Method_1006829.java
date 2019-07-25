public List<String> mget(List<String> keyList){
  if (keyList == null || keyList.isEmpty())   return null;
  List<String> list=this.stringRedisTemplate.opsForValue().multiGet(keyList);
  if (list == null)   return null;
  return list;
}
