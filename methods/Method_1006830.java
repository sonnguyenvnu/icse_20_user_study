public List<byte[]> mget(List<byte[]> keyList){
  if (keyList == null || keyList.isEmpty())   return null;
  List<byte[]> byteList=this.redisTemplate.opsForValue().multiGet(keyList);
  return byteList;
}
